package gobang.view;

import java.awt.*;

/**
 * 客户端视图层
 */
public class ClientBackground extends Frame  {

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


}
