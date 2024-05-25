package gobang.view.component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 棋盘视图组件
 *
 * @author SK
 * @date 2024/05/25
 */
public class ChessBoard extends Panel implements ActionListener, KeyListener {


    public ChessBoard() {
        setSize(600, 600);

        setLayout(null);
        setBackground(new Color(200, 135, 60));

    }

    /**
     * 画棋盘
     * <p> 声明: 参考gobang_game项目绘制 </p>
     */
    @Override
    public void paint(Graphics g) {
        int cellSize = 40; // 棋盘格子大小
        int rightEnd = 580; // 棋盘右边界
        int ovalSize = 8; // 棋盘上的大黑点大小
        int[] ovalPoints = {126, 306, 486};  // 棋盘上的大黑点坐标


        for (int i = cellSize; i <= rightEnd; i = i + 30) {
            g.drawLine(cellSize, i, rightEnd, i);
            g.drawLine(i, cellSize, i, rightEnd);
        }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                g.fillOval(ovalPoints[i], ovalPoints[j], ovalSize, ovalSize);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
