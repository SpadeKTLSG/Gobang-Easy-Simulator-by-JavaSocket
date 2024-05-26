package gobang.client;

import gobang.pojo.dto.R;
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

import static gobang.pojo.entity.Function.*;
import static gobang.utils.connectUtils.getLocalIp;
import static gobang.utils.connectUtils.getLocalPort;
import static gobang.utils.viewUtils.DSize;
import static gobang.utils.viewUtils.bindKeyToAction;
import static java.lang.Thread.sleep;

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

    }

    /**
     * 初始化
     */
    public void init(USERCOLOR userColor) {

        if (userColor == USERCOLOR.black) // 初始化游戏状态字段
            this.gs = new GameStatus(USERCOLOR.black, USERCOLOR.black + " Player", getLocalIp(), getLocalPort());
        else
            this.gs = new GameStatus(USERCOLOR.white, USERCOLOR.white + " Player", getLocalIp(), getLocalPort());

        //窗口控件初始化
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gs.start) { //已开局, 则视为失败, 发出退出消息
                    gs.start = false;
                    try {
                        clientThread.sendMessage(new R(EXIT, null));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (gs.connected) { //已连接, 需要关闭连接
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
     * <p>成功连接到主机时，设置客户端相应的界面状态</p>
     */
    public void doConnect() {
        try {
            if (connect2Server(this.gs.getHost(), this.gs.getPort())) {// 连接服务器
                log.info("连接服务器成功");
                this.gs.mouseEnabled = false; //设置鼠标状态 Silence
                this.gs.connected = true; //设置连接状态 OK
                this.statusPanel.noticePad.setText("连接成功，请等待对手");
            }
        } catch (Exception ei) {
            this.statusPanel.noticePad.setText("网络走丢了...");
        }
    }


    /**
     * 连接服务器
     */
    boolean connect2Server(String ip, int port) {
        try {
            //连接服务器, 开启客户端套接字
            clientSocket = new Socket(ip, port);
            is = new DataInputStream(clientSocket.getInputStream());
            os = new DataOutputStream(clientSocket.getOutputStream());

            // 创建客户端线程并启动监听
            this.clientThread = new ClientThread(this);
            clientThread.start();
            return true;

        } catch (IOException ex) {
            log.error("连接服务器失败");
        }
        return false;
    }


    /**
     * 主方法
     */
    public void func() {
        this.gs.start = true;
        this.statusPanel.noticePad.setText("对手已连接, 游戏开始");
        login();
        bindListener();

    }


    /**
     * 用户注册
     */
    public void login() {
        if (!gs.start) {
            log.error("游戏未开始");
        }

        if (gs.getUserColor() == USERCOLOR.black) { //黑棋先行, 主动唤醒自己
            gs.mouseEnabled = true;
            statusPanel.noticePad.setText("已开始|请下黑棋!");

        } else {
            gs.mouseEnabled = false;
            statusPanel.noticePad.setText("已开始|白棋请等待!");
        }
    }


    /**
     * 绑定各类监听器
     */
    public void bindListener() {

        if (!gs.start) {
            log.error("游戏未开始");
        }

        //空格键监听事件 -
        bindKeyToAction(this, "SPACE", "doSpaceAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //future
            }
        });

        //回车键监听事件 -
        bindKeyToAction(this, "ENTER", "doEnterAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //future
            }
        });

        //鼠标单击监听事件 - 下棋
        chessBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!gs.start) //游戏未开始
                    return;

                if (!gs.mouseEnabled)//判断是否能启动鼠标, 这样就规避了多线程问题, 无需使用synchronized
                    return;

                //获取鼠标点击位置
                Point point = e.getPoint();
                int a = (point.x - DSize) / DSize, b = (point.y - DSize) / DSize;

                USERCOLOR nowColor = gs.getUserColor(); //读取当前的颜色

                chessBoard.paintChess(a, b, nowColor); //绘制本地棋盘
                chessBoard.storeChess(a, b, nowColor); //存储本地棋盘

                //判断胜利状态
                if (chessBoard.checkVicStatus(nowColor)) {
                    normalWon();
                    sendULose();

                } else {//发送新棋子位置消息

                    try {
                        drawOppoChess(a, b);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    gs.mouseEnabled = false; //关闭鼠标
                    statusPanel.noticePad.setText("等待对手下棋");
                }

            }
        });


    }


    /**
     * 提示自己回合
     */
    public void myTurn() {
        statusPanel.noticePad.setText("请下棋!");
    }


    /**
     * 绘制对手棋子
     * <p>需要传送到对应服务端, 然后对应服务端唤醒另一个服务端执行其客户端操作</p>
     */
    public void drawOppoChess(int a, int b) throws IOException {
        R r = new R();
        r.setFunction(DROP);
        r.setData(new int[]{a, b}); //封装数据到int[]
        this.clientThread.sendMessage(r);
    }


    /**
     * 正常获胜方法
     */
    public void normalWon() {
        gs.start = false;
        gs.won = true;
        gs.mouseEnabled = false;
        statusPanel.noticePad.setText("你赢了, 请回罢");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 敌人逃跑获胜方法
     */
    public void escapeWon() {
        gs.won = true;
        gs.start = false;
        gs.mouseEnabled = false;
        statusPanel.noticePad.setText("敌军大败而归~");
    }

    /**
     * 正常败北方法
     */
    public void normalLost() {
        gs.start = false;
        gs.mouseEnabled = false;
        statusPanel.noticePad.setText("你输了");
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    /**
     * 给对手发送你输了
     */
    public void sendULose() {
        R r = new R();
        r.setFunction(WIN);
        try {
            this.clientThread.sendMessage(r);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
