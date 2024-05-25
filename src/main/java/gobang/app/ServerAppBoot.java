package gobang.app;

import gobang.view.ServerBackground;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerAppBoot {

    public static void main(String[] args) {
        ServerBackground serverBackground = new ServerBackground();
        log.info("{}服务器启动成功", "gobang小水管本地only");
    }

}
