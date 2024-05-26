package gobang.server;

import gobang.pojo.dto.R;
import gobang.pojo.entity.Function;
import gobang.view.ServerBackground;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static gobang.utils.connectUtils.getLocalPort;

/**
 * 服务端应用
 *
 * @author SK
 * @date 2024/05/25
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ServerApp extends ServerBackground {

    /**
     * 服务端套接字
     */
    public ServerSocket serverSocket;

    /**
     * 服务端线程A -> 处理客户端A
     */
    public ServerThread serverThreadA;

    /**
     * 服务端线程B -> 处理客户端B
     */
    public ServerThread serverThreadB;

    /**
     * 服务端线程A状态 : 0 = 未连接, 1 = 已连接
     */
    public int AThreadStatus = 0;

    /**
     * 服务端线程B状态 : 0 = 未连接, 1 = 已连接
     */
    public int BThreadStatus = 0;


    /**
     * 两个线程建立连接标记
     */
    public int connected = 0;


    /**
     * 服务端应用
     */
    public ServerApp() {
        super();
        init();
        doBuild();
    }


    /**
     * 初始化
     */
    public void init() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }


    /**
     * 构建
     */
    public void doBuild() {
        try {
            createServer(getLocalPort());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 创建服务器
     */
    public void createServer(int port) {
        try {
            serverSocket = new ServerSocket(port);// 设定当前主机

            while (true) {

                try {

                    Socket clientSocket = serverSocket.accept(); //得到一个客户端套接口

                    //选择A / B服务端线程进行处理 TODO
                    if (AThreadStatus == 0) {
                        AThreadStatus = 1;

                        //创建输入输出流
                        DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());
                        DataInputStream inputData = new DataInputStream(clientSocket.getInputStream());

                        //创建服务端线程
                        serverThreadA = new ServerThread(this, clientSocket, outputData, inputData, null);
                        serverThreadA.start();

                        //修改界面显示
                        log.info("已连接用户: 1号");
                        super.watchPanel.addConnectInfo("1号");

                    } else if (BThreadStatus == 0) {
                        BThreadStatus = 1;

                        //创建输入输出流
                        DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());
                        DataInputStream inputData = new DataInputStream(clientSocket.getInputStream());

                        //创建服务端线程
                        serverThreadB = new ServerThread(this, clientSocket, outputData, inputData, null);
                        serverThreadB.start();

                        //修改界面显示
                        log.info("已连接用户: 2号");
                        super.watchPanel.addConnectInfo("2号");

                    } else {
                        log.error("错误! 服务器爆炸了!");
                    }

                    //由于全部连接, 于是在两个服务端线程之间建立连接, 并启动两个客户端的线程 -> 游戏字段 gs.start = true
                    if (connected == 0 && AThreadStatus == 1 && BThreadStatus == 1) {
                        serverThreadA.opponentThread = serverThreadB;
                        serverThreadB.opponentThread = serverThreadA;
                        serverThreadA.sendMessage(new R(Function.START, "OPEN THE GAME"));
                        serverThreadB.sendMessage(new R(Function.START, "OPEN THE GAME"));
                        connected = 1;
                    }

                } catch (IOException e) {
                    log.warn("服务器线程异常");
                    break;
                }
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());

        }
    }

    /**
     * 重置服务器状态
     */
    public void reSet() {
        AThreadStatus = 0;
        BThreadStatus = 0;
        connected = 0;
        serverThreadA = null;
        serverThreadB = null;
        super.watchPanel.clearConnectInfo();
    }

}
