package gobang.view.component;

import java.awt.*;

/**
 * 棋盘视图组件
 *
 * @author SK
 * @date 2024/05/25
 */
public class ChessBoard extends Panel {


    public ChessBoard() {
        setSize(600, 600);

        setLayout(null);
        setBackground(new Color(200, 135, 60));

    }

    /**
     * 画棋盘
     * <p> 画棋盘线, 画棋盘上的五个点, 参考gobang_game绘制</p>
     */
    @Override
    public void paint(Graphics g) {
        int cellSize = 40;
        int rightEnd = 580;
        int ovalSize = 8;
        int[] ovalPoints = {126, 306, 486};


        for (int i = cellSize; i <= rightEnd; i = i + 30) {
            g.drawLine(cellSize, i, rightEnd, i);
            g.drawLine(i, cellSize, i, rightEnd);
        }


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                g.fillOval(ovalPoints[i], ovalPoints[j], ovalSize, ovalSize);

    }
}
