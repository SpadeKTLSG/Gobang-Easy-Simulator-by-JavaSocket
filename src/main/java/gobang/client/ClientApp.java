package gobang.client;

import gobang.pojo.entity.GameStatus;
import gobang.view.ClientBackground;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static gobang.utils.connectUtils.getLocalIp;
import static gobang.utils.connectUtils.getLocalPort;

/**
 * 客户端应用
 *
 * @author SK
 * @date 2024/05/25
 */
@Slf4j
public class ClientApp extends ClientBackground implements ActionListener, KeyListener {

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



    public void bindListener() {
        // 绑定监听器: 需在键盘组件上添加监听器
        this.addKeyListener(this.chessBoard);
/*        this.setFocusable(true);
        this.requestFocusInWindow();*/
    }


    /**
     * 组件:点击
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }


    /**
     * 键盘:按下
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //按下空格键执行连接
        //
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("按下空格键");
        }

    }


    /**
     * 键盘:输入
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 键盘:释放
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
