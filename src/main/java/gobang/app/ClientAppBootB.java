package gobang.app;


import gobang.client.ClientApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientAppBootB {

    public static void main(String[] args) {
        ClientApp client = new ClientApp();
        log.info("{}: B客户端启动成功", client.getTitle());
    }

}
