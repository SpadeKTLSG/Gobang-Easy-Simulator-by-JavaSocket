package gobang.utils;

/**
 * 连接工具类
 *
 * @author SK
 * @date 2024/05/25
 */
public class connectUtils {

    /**
     * 获取本地IP地址
     *
     * @return {@link String}
     */
    public static String getLocalIp() {
        return "127.0.0.1";
    }

    /**
     * 13000 - 14000之间随机生成一个本地端口号
     *
     * @return int
     */
    public static int getLocalPort() {
        return (int) (Math.random() * 1000 + 13000);
    }
}
