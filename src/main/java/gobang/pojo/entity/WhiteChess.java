package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

public class WhiteChess extends Chess {

    public WhiteChess(ChessBoard cb) {
        super(cb);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 30, 30);
    }
}
