package gobang.client;

import gobang.pojo.entity.GameStatus;
import gobang.view.ClientBackground;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 客户端应用
 *
 * @author SK
 * @date 2024/05/25
 */
public class ClientApp extends ClientBackground {

    /**
     * 客户端套接字
     */
    public Socket clientSocket;

    /**
     * 数据输入流
     */
    public DataInputStream is;

    /**
     * 数据输出流
     */
    public DataOutputStream os;

    /**
     * 游戏状态存储entity
     */
    public GameStatus gs;

    /**
     * 客户端应用
     */
    public ClientApp() {
        super();
        init();
    }

    /**
     * 初始化
     */
    public void init() {

    }
}
