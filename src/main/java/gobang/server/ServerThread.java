package gobang.server;

import com.google.gson.Gson;
import gobang.pojo.dto.R;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static gobang.pojo.entity.Function.ELSE;

@Slf4j
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


    /**
     * 处理信息
     */
    public void dealWithMsg(R r) {

        switch (r.getFunction()) {

            case DROP, START:  //转发消息到对手线程
                try {
                    opponentThread.sendMessage(r);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case WIN, EXIT:  //转发消息后 释放双方线程, 重置状态

                try {
                    opponentThread.sendMessage(r);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sa.reSet();
                break;

            case ELSE:
                //future
                break;
            default:
                log.warn("Invalid function: " + r.getFunction());

        }

    }


    /**
     * 给客户端发送信息
     */
    public void sendMessage(R r) throws IOException {
        String json = new Gson().toJson(r); // 将R对象转换为JSON格式
        os.writeUTF(json);
        os.flush();
    }


    /**
     * 监听客户端消息, 并转发到对手服务器线程处理
     */
    @Override
    public void run() {

        // 等待连接到主机的信息
        try {
            is = new DataInputStream(clientSocket.getInputStream());
            sendInitMsg("服务器到客户端的连接建立成功");
            while (true) {

                try {
                    String json = is.readUTF();
                    R r = new Gson().fromJson(json, R.class);
                    dealWithMsg(r);
                } catch (IOException es) {
                    log.warn("客户端线程异常");
                    break;
                }

            }
        } catch (IOException esx) {
            log.warn("服务器线程异常");

        }

    }

    /**
     * 服务器向连接客户端线程发送初始化消息
     */
    public void sendInitMsg(String s) throws IOException {
        R r = new R(ELSE, s);
        String json = new Gson().toJson(r); // 将R对象转换为JSON格式
        os.writeUTF(json);
        os.flush();
        log.info("服务器到客户端的连接建立成功");
    }


}
