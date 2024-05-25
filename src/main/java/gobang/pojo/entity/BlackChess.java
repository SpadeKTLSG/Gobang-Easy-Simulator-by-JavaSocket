package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

public class BlackChess extends Chess {

    public BlackChess(ChessBoard cb) {
        super(cb);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0, 0, 30, 30);
    }
}
