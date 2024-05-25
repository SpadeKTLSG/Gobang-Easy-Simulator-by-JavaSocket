package gobang.app;


import gobang.client.ClientApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientAppBoot {

    public static void main(String[] args) {
        ClientApp client = new ClientApp();
        client.init();
        log.info("{}客户端启动成功", "gobang本地");
    }

}
