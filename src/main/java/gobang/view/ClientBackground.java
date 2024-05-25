package gobang.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 客户端背景
 */
public class ClientBackground extends Frame implements ActionListener, KeyListener {

    private Panel centerPanel = new Panel();

    public ClientBackground() {
        super("GobangEZSimulator - 客户端");
        setLayout(new BorderLayout());

        add(centerPanel, BorderLayout.CENTER);


        //设置窗口属性
        pack();//自适应大小
        setSize(800, 850); //设置窗口大小
        setVisible(true);
        setResizable(false); //设置窗口不可调整大小
        this.validate(); //验证此容器及其所有子组件。
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
