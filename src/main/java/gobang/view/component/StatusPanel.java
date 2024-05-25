package gobang.view.component;

import java.awt.*;

/**
 * 用户界面状态面板组件
 *
 * @author SK
 * @date 2024/05/25
 */
public class StatusPanel extends Panel {


    /**
     * 通知面文本框
     */
    public TextField noticePad = new TextField();


    public StatusPanel() {

        setSize(600, 80);
        noticePad.setSize(300, 80);
        noticePad.setFont(new Font("宋体", Font.BOLD, 30));
        noticePad.setEditable(false);
        noticePad.setText("欢迎来到五子棋游戏");
        add(noticePad);

        setLayout(null);
        setBackground(new Color(114, 114, 114));


    }


}
