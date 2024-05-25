package gobang.client;

import com.google.gson.Gson;
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
    public void dealWithMsg(R r) {
        //TODO
        //暂时还原出来, 分别打印
        System.out.println(r.getFunction().toString() + " : " + r.getData().toString());

    }

    /**
     * 发送信息
     */
    public void sendMessage(R r) throws IOException {
        String json = new Gson().toJson(r); // 将R对象转换为JSON格式
        System.out.println("发送信息: " + json);
        ca.os.writeUTF(json);
        ca.os.flush();
    }

    /**
     * 线程运行
     */
    @Override
    public void run() {
        while (true) { //监听
            try {
                String json = ca.is.readUTF();// 读取JSON格式内容, 并存储到R对象中

                if (json.trim().startsWith("{")) {
                    R r = new Gson().fromJson(json, R.class);
                    dealWithMsg(r);
                } else { // 处理异常JSON
                    log.warn("Invalid JSON string: " + json);
                }

            } catch (IOException es) {
                log.warn("客户端线程异常");
                break;
            }
        }
    }

}
