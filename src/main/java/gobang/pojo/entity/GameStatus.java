package gobang.pojo.entity;

import lombok.Data;

/**
 * 游戏状态
 *
 * @author SK
 * @date 2024/05/25
 */
@Data
public class GameStatus {

    /**
     * 用户颜色
     */
    public USERCOLOR userColor;

    /**
     * 用户名
     */
    public String userName;

    /**
     * 主机
     */
    public String host;

    /**
     * 端口
     */
    public int port;

    /**
     * 鼠标是否启用
     */
    public boolean mouseEnabled;

    /**
     * 是否胜利
     */
    public boolean won;

    /**
     * 是否连接
     */
    public boolean connected;

    public GameStatus() {
    }


    public GameStatus(USERCOLOR userColor, String userName, String host, int port) {
        this.userColor = userColor;
        this.userName = userName;
        this.host = host;
        this.port = port;
        this.mouseEnabled = false;
        this.won = false;
    }

    public GameStatus(String host, int port) {
        this.host = host;
        this.port = port;
        this.mouseEnabled = false;
        this.won = false;
    }

    public GameStatus(String s, String localIp, int localPort) {
        this.userName = s;
        this.host = localIp;
        this.port = localPort;
        this.mouseEnabled = false;
        this.won = false;
    }
}
