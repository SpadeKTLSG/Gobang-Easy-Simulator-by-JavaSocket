package gobang.utils;

import lombok.Data;

@Data
public class Pos {

    public int x;

    public int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
