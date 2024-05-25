package gobang.app;


import gobang.client.ClientApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientAppBootBackup {

    public static void main(String[] args) {
        ClientApp client = new ClientApp();
        log.info("{}客户端启动成功", client.getTitle());
    }

}
