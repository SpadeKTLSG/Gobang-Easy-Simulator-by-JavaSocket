package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

public class BlackChess extends Canvas {

    /**
     * 黑棋所属的棋盘
     */
    private ChessBoard chessBoard;

    public BlackChess(ChessBoard cb) {
        this.chessBoard = cb;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, 30, 30);
    }
}
