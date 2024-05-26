package gobang.app;


import gobang.client.ClientApp;
import gobang.pojo.entity.USERCOLOR;
import lombok.extern.slf4j.Slf4j;

/**
 * 客户端A启动类
 *
 * @author SK
 * @date 2024/05/26
 */
@Slf4j
public class ClientAppBootA {

    public static void main(String[] args) {
        ClientApp client = new ClientApp(USERCOLOR.black);
        client.setTitle("GobangEZSimulator - 客户端A (Black)");
        log.info("{}: A客户端启动成功", client.getTitle());
    }

}
