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
    USERCOLOR userColor;

    /**
     * 用户名
     */
    String userName;

    /**
     * 主机
     */
    String host;

    /**
     * 端口
     */
    int port;

    /**
     * 鼠标是否启用
     */
    boolean isMouseEnabled = false;

    /**
     * 是否胜利
     */
    boolean isWon = false;


    public GameStatus(USERCOLOR userColor, String userName, String host, int port) {
        this.userColor = userColor;
        this.userName = userName;
        this.host = host;
        this.port = port;
    }

    public GameStatus(String host, int port) {
        this.host = host;
        this.port = port;
    }

}
