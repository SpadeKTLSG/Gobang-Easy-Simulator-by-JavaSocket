package gobang.view.component;

import gobang.pojo.entity.BlackChess;
import gobang.pojo.entity.Chess;
import gobang.pojo.entity.USERCOLOR;
import gobang.pojo.entity.WhiteChess;
import gobang.pojo.dto.Pos;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.util.ArrayList;

import static gobang.pojo.entity.USERCOLOR.black;
import static gobang.utils.viewUtils.DSize;

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

    /**
     * 黑棋位置ArrayList序列, 存放Position对象: int x, int y
     */
    public ArrayList<Pos> blackLoc = new ArrayList<>();

    /**
     * 白棋位置ArrayList序列, 存放Position对象: int x, int y
     */
    public ArrayList<Pos> whiteLoc = new ArrayList<>();


    public ChessBoard() {
        setSize(600, 600);
        setLayout(null);
        setBackground(new Color(200, 135, 60));

    }

    /**
     * 画棋盘 (本地)
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

    /**
     * 画棋子(本地)
     */
    public void paintChess(int xPos, int yPos, USERCOLOR userColor) {
        Chess chess = userColor == black ? new BlackChess(this) : new WhiteChess(this);
        chess.setBounds(xPos * DSize + DSize - 5, yPos * DSize + DSize - 5, DSize, DSize);
        add(chess);
    }

    /**
     * 存储棋子位置(本地)
     */
    public void storeChess(int xPos, int yPos, USERCOLOR userColor) {
        if (userColor == black) {
            blackLoc.add(new Pos(xPos, yPos));
        } else {
            whiteLoc.add(new Pos(xPos, yPos));
        }

        //print all TODO
        System.out.println("blackLoc: ");
        for (Pos pos : blackLoc) {
            System.out.println(pos.getX() + " " + pos.getY());
        }
        System.out.println("whiteLoc: ");
        for (Pos pos : whiteLoc) {
            System.out.println(pos.getX() + " " + pos.getY());
        }
    }

    /**
     * 本地检查当前对应颜色棋手胜利状态
     */
    public boolean checkVicStatus(USERCOLOR userColor) {

        //refer to blackLoc, whiteLoc, make a algorithm to check the victory status: 5 in a row, column, diagonal
        //if 5 in a row, column, diagonal, return true, else return false
        ArrayList<Pos> positions = userColor == black ? blackLoc : whiteLoc;

        for (Pos pos : positions) {
            if (checkDirection(pos, 1, 0, positions) || // Horizontal
                    checkDirection(pos, 0, 1, positions) || // Vertical
                    checkDirection(pos, 1, 1, positions) || // Diagonal from top-left to bottom-right
                    checkDirection(pos, 1, -1, positions)) { // Diagonal from top-right to bottom-left
                return true;
            }
        }




        return false;
    }

    private boolean checkDirection(Pos start, int dx, int dy, ArrayList<Pos> positions) {
        for (int i = 1; i < 5; i++) {
            Pos next = new Pos(start.getX() + i * dx, start.getY() + i * dy);
            if (!positions.contains(next)) {
                return false;
            }
        }
        return true;
    }
}
