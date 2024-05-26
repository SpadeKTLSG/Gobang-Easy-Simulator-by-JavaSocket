package gobang.view.component;

import javax.swing.*;
import java.awt.*;

public class WatchPanel extends Panel {

    /**
     * 自制监视列表模型
     */
    public DefaultListModel<String> watchListModel = new DefaultListModel<>(); //存储对应连接的信息

    /**
     * 监视文本框
     */
    public JList<String> watchPad = new JList<>(watchListModel);

    public WatchPanel() {
        watchListModel.addElement("服务端已运行|连接信息:");


        setSize(380, 150);
        watchPad.setSize(380, 150);
        watchPad.setFont(new Font("宋体", Font.BOLD, 30));
        watchPad.setBackground(new Color(102, 204, 255));
        watchPad.setFixedCellHeight(30);
        watchPad.setFixedCellWidth(400);
        watchPad.setSelectionBackground(new Color(255, 255, 255));

        add(watchPad);
        setLayout(null);
        setBackground(new Color(114, 114, 114));
    }

    /**
     * 添加连接信息
     */
    public void addConnectInfo(String info) {
        watchListModel.addElement(info + " 准备就绪!");
    }

    /**
     * 清空连接信息
     */
    public void clearConnectInfo() {
        watchListModel.clear();
        watchListModel.addElement("服务端已运行|连接信息:");
    }
}
