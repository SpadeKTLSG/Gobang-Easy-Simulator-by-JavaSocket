package gobang.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 客户端线程
 *
 * @author SK
 * @date 2024/05/25
 */
@Slf4j
public class ClientThread extends Thread {

    /**
     * 与其交互的客户端应用
     */
    private ClientApp ca;


    /**
     * 处理信息
     */
    public void dealWithMsg(String msg) {

    }

    /**
     * 发送信息
     */
    public void sendMessage(String msg) {

    }

    @Override
    public void run() {
        String msgReceived;
        while (true) {
            try {
                msgReceived = ca.is.readUTF(); // 读取信息
                dealWithMsg(msgReceived);
            } catch (IOException es) {
                log.warn("客户端线程异常");
                break;
            }
        }
    }

}
