package gobang.pojo.entity;

import gobang.view.component.ChessBoard;

import java.awt.*;

public class Chess extends Canvas {

    protected ChessBoard chessBoard;

    public Chess(ChessBoard cb) {
        this.chessBoard = cb;
    }
}
