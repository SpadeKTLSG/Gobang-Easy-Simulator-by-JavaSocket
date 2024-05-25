package gobang.client;

import gobang.pojo.dto.R;
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


    public ClientThread(ClientApp ca) {
        this.ca = ca;
    }


    /**
     * 处理信息
     */
    public void dealWithMsg(R r){


    }

    /**
     * 发送信息
     */
    public void sendMessage(R r) {
        //将

    }

    @Override
    public void run() {
        R r = new R();
        while (true) {
            try {
                // 读取JSON格式内容, 并存储到R对象中

                // 处理信息
            } catch (IOException es) {
                log.warn("客户端线程异常");
                break;
            }
        }
    }

}
