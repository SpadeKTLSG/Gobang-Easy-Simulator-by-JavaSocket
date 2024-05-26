package gobang.pojo.entity;

/**
 * 功能
 *
 * @author SK
 * @date 2024/05/26
 */
public enum Function {
    /**
     * 其他
     */
    ELSE,
    /**
     * 我确认开始游戏
     */
    START,
    /**
     * 我落子了
     */
    DROP,
    /**
     * 我不打扰, 我走了蛤
     */
    EXIT,
    /**
     * 我赢了
     */
    WIN
}
