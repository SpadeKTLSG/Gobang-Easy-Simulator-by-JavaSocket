package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

/**
 * 棋子类
 *
 * @author SK
 * @date 2024/05/26
 */
public class Chess extends Canvas {

    /**
     * 持有棋盘引用
     */
    protected ChessBoard chessBoard;

    public Chess(ChessBoard cb) {
        this.chessBoard = cb;
    }
}
