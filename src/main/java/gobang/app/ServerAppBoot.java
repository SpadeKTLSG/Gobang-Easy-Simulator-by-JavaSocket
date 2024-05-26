package gobang.app;

import gobang.server.ServerApp;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器启动类
 *
 * @author SK
 * @date 2024/05/26
 */
@Slf4j
public class ServerAppBoot {

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp();
        log.info("{} 服务器启动成功", serverApp.getTitle());
    }

}
