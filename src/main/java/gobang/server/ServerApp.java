package gobang.server;

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
     * 创建服务器
     */
    public void doBuild() {
        try {
            createServer(getLocalPort());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void createServer(int port) {
        try {
            serverSocket = new ServerSocket(port);// 设定当前主机

            while (true) {

                try {

                    Socket clientSocket = serverSocket.accept(); //得到一个客户端套接口

                    //选择A / B服务端线程进行处理
                    if (AThreadStatus == 0) {
                        AThreadStatus = 1;

                        DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());
                        DataInputStream inputData = new DataInputStream(clientSocket.getInputStream());

                        serverThreadA = new ServerThread(this, clientSocket, outputData, inputData, serverThreadB);
                        serverThreadA.start();

                        //修改界面显示
                        log.info("已连接用户: A");
                        super.watchPanel.addConnectInfo("A");

                    } else if (BThreadStatus == 0) {
                        BThreadStatus = 1;

                        DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());
                        DataInputStream inputData = new DataInputStream(clientSocket.getInputStream());

                        serverThreadB = new ServerThread(this, clientSocket, outputData, inputData, serverThreadA);
                        serverThreadB.start();

                        //修改界面显示
                        log.info("已连接用户: B");
                        super.watchPanel.addConnectInfo("B");

                    } else {
                        log.error("错误! 服务器已满");
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


}
