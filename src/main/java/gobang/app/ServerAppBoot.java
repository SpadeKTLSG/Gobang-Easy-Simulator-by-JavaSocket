package gobang.app;

import gobang.server.ServerApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerAppBoot {

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp();
        log.info("{}服务器启动成功", serverApp.getTitle());
    }

}
