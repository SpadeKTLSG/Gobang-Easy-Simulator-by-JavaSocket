package gobang.view;

import gobang.view.component.NilPanel;
import gobang.view.component.WatchPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 服务端视图层
 */
public class ServerBackground extends JFrame {

    /**
     * 中心面板
     */
    protected Panel centerPanel = new Panel();

    /**
     * 监视面板
     */
    protected WatchPanel watchPanel = new WatchPanel();

    /**
     * 上部面板
     */
    protected Panel upperPanel = new Panel();

    /**
     * 未使用面板
     */
    protected NilPanel nilPanel = new NilPanel();


    public ServerBackground() {
        super("GobangEZSimulator - 服务端");
        setLayout(new BorderLayout());

        //设置上部面板 = 监视面板
        upperPanel.add(watchPanel, BorderLayout.CENTER);
        add(upperPanel, BorderLayout.NORTH);

        //设置中心面板 = 棋盘 600*600
        centerPanel.add(nilPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);

        //设置窗口属性
        pack();//自适应大小
        setSize(400, 500);
        setVisible(true);
        setResizable(false); //设置窗口不可调整大小
        this.validate(); //验证此容器及其所有子组件。
    }
}
