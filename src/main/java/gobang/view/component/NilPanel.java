package gobang.view.component;

import javax.swing.*;
import java.awt.*;

public class NilPanel extends Panel {

    public NilPanel() {
        //4 future use
        JTextField contentInputted = new JTextField("", 0);
        contentInputted.setSize(0, 0);
        contentInputted.setFont(new Font("宋体", Font.BOLD, 30));
        contentInputted.setEditable(false);
        contentInputted.setText("欢迎来到五子棋游戏");
        add(contentInputted);
    }

}
