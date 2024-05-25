package gobang.pojo.dto;

import lombok.Data;

/**
 * 自定义位置(x,y)
 * @author SK
 * @date 2024/05/25
 */
@Data
public class Pos {

    public int x;

    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
