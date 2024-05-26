package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

/**
 * 白棋子类
 *
 * @author SK
 * @date 2024/05/26
 */
public class WhiteChess extends Chess {

    public WhiteChess(ChessBoard cb) {
        super(cb);
    }

    /**
     * 画白棋子
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 30, 30);
    }
}
