package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

/**
 * 黑棋子类
 *
 * @author SK
 * @date 2024/05/26
 */
public class BlackChess extends Chess {

    public BlackChess(ChessBoard cb) {
        super(cb);
    }

    /**
     * 画黑棋子
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, 30, 30);
    }
}
