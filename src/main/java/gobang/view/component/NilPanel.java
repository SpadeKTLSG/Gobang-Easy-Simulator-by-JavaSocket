package gobang.view.component;

import javax.swing.*;
import java.awt.*;

/**
 * 空白面板
 * @author SK
 * @date 2024/05/26
 */
public class NilPanel extends Panel {

    public NilPanel() {//4 future use
        JTextField contentInputted = new JTextField("", 0);
        contentInputted.setSize(0, 0);
        contentInputted.setFont(new Font("宋体", Font.BOLD, 30));
        contentInputted.setEditable(false);
        contentInputted.setText("欢迎来到SK的五子棋游戏");
        add(contentInputted);
    }

}
