package gobang.view.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

/**
 * 棋盘视图组件
 *
 * @author SK
 * @date 2024/05/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChessBoard extends Panel {

    /**
     * 分割线大小标记
     */
    public int divisionSize = 30;

    /**
     * 棋盘格子大小标记
     */
    public int cellSize = 40;

    /**
     * 棋盘右边界
     */
    public int rightEnd = 580;

    /**
     * 棋盘上的大黑点大小
     */
    public int ovalSize = 8;

    /**
     * 棋盘上的大黑点坐标对齐参考点
     */
    public int[] ovalPoints = {126, 306, 486};

    public ChessBoard() {
        setSize(600, 600);
        setLayout(null);
        setBackground(new Color(200, 135, 60));

    }

    /**
     * 画棋盘
     * <p> 声明: 参考gobang_game项目绘制 </p>
     * <p> 18*18个下棋点, 对应棋盘和存储序列 </p>
     */
    @Override
    public void paint(Graphics g) {
        // 画横线和竖线
        for (int i = cellSize; i <= rightEnd; i = i + divisionSize) {
            g.drawLine(cellSize, i, rightEnd, i);
            g.drawLine(i, cellSize, i, rightEnd);
        }

        // 画9个黑点
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                g.fillOval(ovalPoints[i], ovalPoints[j], ovalSize, ovalSize);

    }


}
