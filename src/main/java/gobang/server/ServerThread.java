package gobang.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    /**
     * 对应的服务端应用
     */
    private ServerApp sa;

    /**
     * 服务的客户端套接字
     */
    public Socket clientSocket;

    /**
     * 客户端数据输出流
     */
    public DataOutputStream os;

    /**
     * 客户端数据输入流
     */
    public DataInputStream is;


    /**
     * 对手线程引用
     */
    public ServerThread opponentThread;



    public ServerThread(ServerApp sa, Socket socket, DataOutputStream os, DataInputStream is, ServerThread opponentThread) {
        this.sa = sa;
        this.clientSocket = socket;
        this.os = os;
        this.is = is;
        this.opponentThread = opponentThread;
    }

    public void run() {
    }
}
