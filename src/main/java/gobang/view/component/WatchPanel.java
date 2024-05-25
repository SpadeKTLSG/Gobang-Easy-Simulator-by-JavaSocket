package gobang.view.component;

import javax.swing.*;
import java.awt.*;

public class WatchPanel extends Panel {

    /**
     * 监视文本框
     */
    public JList<String> watchPad = new JList<>(); //存储对应连接的信息


    public WatchPanel() {

        setSize(400, 150);
        watchPad.setSize(400, 150);
        watchPad.setFont(new Font("宋体", Font.BOLD, 30));
        watchPad.setBackground(new Color(102, 204, 255));
        watchPad.setListData(new String[]{"服务端已运行|连接信息:"});
        watchPad.setFixedCellHeight(30);
        watchPad.setFixedCellWidth(400);
        watchPad.setSelectionBackground(new Color(255, 255, 255));
        add(watchPad);
        setLayout(null);
        setBackground(new Color(114, 114, 114));
    }
}
