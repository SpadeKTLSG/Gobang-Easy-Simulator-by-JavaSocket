package gobang.app;


import gobang.client.ClientApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientAppBootA {

    public static void main(String[] args) {
        ClientApp client = new ClientApp();
        client.setTitle("GobangEZSimulator - 客户端A (Black)");
        log.info("{}: A客户端启动成功", client.getTitle());
    }

}
