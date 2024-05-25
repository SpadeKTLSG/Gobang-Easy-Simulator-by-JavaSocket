package gobang.client;

import gobang.pojo.entity.*;
import gobang.view.ClientBackground;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static gobang.pojo.entity.USERCOLOR.black;
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
@Slf4j
public class ClientApp extends ClientBackground {

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
    public ClientApp() {
        super();
        init();
        doConnect();
        bindListener();


    }

    /**
     * 初始化
     */
    public void init() {
        this.gs = new GameStatus(getLocalIp(), getLocalPort()); // 初始化游戏状态pojo字段


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO 关闭连接
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

            ClientThread clientThread = new ClientThread(this);  // 创建客户端线程并启动监听
            clientThread.start();
            return true;

        } catch (IOException ex) {
            log.error("连接服务器失败");
        }
        return false;
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
                //TODO 鼠标点击事件

                Point point = e.getPoint();
                System.out.println("x=" + point.x + "    y=" + point.y);

                int a = (point.x - DSize) / DSize, b = (point.y - DSize) / DSize;
                System.out.println("a=" + a + " b=" + b);

                //TODO test
                //画棋子
                //判断能不能启动鼠标
                //读取gs的isMouseEnabled字段
                //读取黑白双方的颜色
                paintChessPoint(a, b, black);
            }
        });


    }

    public void paintChessPoint(int xPos, int yPos, USERCOLOR userColor) {

        Chess chess = userColor == black ? new BlackChess(chessBoard) : new WhiteChess(chessBoard);
        chess.setBounds(xPos * DSize + DSize - 5, yPos * DSize + DSize - 5, DSize, DSize);
        chessBoard.add(chess);
    }

}
