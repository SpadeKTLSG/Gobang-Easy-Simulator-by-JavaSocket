package gobang.pojo.entity;

import gobang.view.ChessBoard;

import java.awt.*;

public class WhiteChess extends Canvas {

    /**
     * 白棋所属的棋盘
     */
    private ChessBoard chessBoard;

    public WhiteChess(ChessBoard cb) {
        this.chessBoard = cb;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 30, 30);
    }
}
