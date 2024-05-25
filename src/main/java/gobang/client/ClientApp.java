package gobang.client;

import gobang.pojo.entity.GameStatus;
import gobang.pojo.entity.USERCOLOR;
import gobang.view.ClientBackground;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static gobang.utils.connectUtils.getLocalIp;
import static gobang.utils.connectUtils.getLocalPort;
import static gobang.utils.viewUtils.DSize;
import static gobang.utils.viewUtils.bindKeyToAction;

/**
 * 客户端应用
 *
 * @author SK
 * @date 2024/05/25
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ClientApp extends ClientBackground {

    /**
     * 客户端线程
     */
    public ClientThread clientThread;

    /**
     * 客户端套接字
     */
    public Socket clientSocket;

    /**
     * 数据输入流
     */
    public DataInputStream is;

    /**
     * 数据输出流
     */
    public DataOutputStream os;

    /**
     * 游戏状态存储entity
     */
    public GameStatus gs;

    /**
     * 客户端应用
     */
    public ClientApp(USERCOLOR userColor) {
        super();
        init(userColor);
        doConnect();
        login();
        bindListener();
    }

    /**
     * 初始化
     */
    public void init(USERCOLOR userColor) {
        // 初始化游戏状态字段

        if (userColor == USERCOLOR.black)
            this.gs = new GameStatus(USERCOLOR.black,USERCOLOR.black + " Player", getLocalIp(), getLocalPort());
        else
            this.gs = new GameStatus(USERCOLOR.white,USERCOLOR.white + " Player", getLocalIp(), getLocalPort());


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gs.connected) {
                    try {
                        os.close();
                        is.close();
                        clientSocket.close();
                    } catch (IOException ie) {
                        log.warn(ie.getMessage());
                    }
                }
                System.exit(0);
            }
        });

    }


    /**
     * 连接
     */
    public void doConnect() {
        try {
            // 成功连接到主机时，设置客户端相应的界面状态
            if (connect2Server(this.gs.getHost(), this.gs.getPort())) {// 连接服务器
                log.info("连接服务器成功");
                this.gs.connected = true; //设置连接状态 OK
                this.gs.mouseEnabled = false; //设置鼠标状态 Silence

                this.statusPanel.noticePad.setText("连接成功，请等待!!!");
                this.statusPanel.repaint();
            }
        } catch (Exception ei) {
            this.statusPanel.noticePad.setText("网络走丢了...");
            this.statusPanel.repaint();
        }
    }

    /**
     * 连接服务器
     *
     * @param ip   ip地址
     * @param port 端口
     * @return boolean  是否连接成功
     */
    boolean connect2Server(String ip, int port) {
        try {
            //连接服务器, 开启客户端套接字
            clientSocket = new Socket(ip, port);
            is = new DataInputStream(clientSocket.getInputStream());
            os = new DataOutputStream(clientSocket.getOutputStream());

            this.clientThread = new ClientThread(this);  // 创建客户端线程并启动监听
            clientThread.start();
            return true;

        } catch (IOException ex) {
            log.error("连接服务器失败");
        }
        return false;
    }


    /**
     * 用户注册
     */
    public void login() {
        if (gs.getUserColor() == USERCOLOR.black) { //黑棋先行, 主动唤醒自己
            gs.mouseEnabled = true;
            statusPanel.noticePad.setText("请下黑棋!");
            statusPanel.repaint();
        } else {
            gs.mouseEnabled = false;
            statusPanel.noticePad.setText("白棋请等待!");
            statusPanel.repaint();
        }
    }


    /**
     * 绑定各类监听器
     */
    public void bindListener() {

        //空格键监听事件 -
        bindKeyToAction(this, "SPACE", "doSpaceAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Space key pressed");
            }
        });

        //回车键监听事件 -
        bindKeyToAction(this, "ENTER", "doEnterAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter key pressed");
            }
        });

        //鼠标单击监听事件 - 下棋
        chessBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (!gs.mouseEnabled)//判断是否能启动鼠标, 这样就规避了多线程问题, 无需使用synchronized
                    return;


                Point point = e.getPoint(); //获取鼠标点击位置
//                System.out.println("x=" + point.x + "    y=" + point.y);
                int a = (point.x - DSize) / DSize, b = (point.y - DSize) / DSize;
//                System.out.println("a=" + a + " b=" + b);

                USERCOLOR nowColor = gs.getUserColor(); //读取当前的颜色

                chessBoard.paintChess(a, b, nowColor); //绘制本地棋盘
                chessBoard.storeChess(a, b, nowColor); //存储本地棋盘
                //TODO 判断是否胜利
                chessBoard.checkVicStatus(nowColor);
                //TODO 绘制对方棋盘
                //发送消息

                gs.mouseEnabled = false; //关闭鼠标

            }
        });


    }


}
