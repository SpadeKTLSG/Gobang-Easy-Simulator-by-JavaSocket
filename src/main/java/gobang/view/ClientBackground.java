package gobang.view;

import gobang.view.component.ChessBoard;
import gobang.view.component.StatusPanel;

import java.awt.*;

/**
 * 客户端视图层
 */
public class ClientBackground extends Frame {

    /**
     * 中心面板
     */
    private Panel centerPanel = new Panel();

    /**
     * 上部面板
     */
    private Panel upperPanel = new Panel();


    private ChessBoard chessBoard = new ChessBoard();

    private StatusPanel statusPanel = new StatusPanel();

    public ClientBackground() {
        super("GobangEZSimulator - 客户端");
        setLayout(new BorderLayout());

        //设置上部面板 : 状态面板 600*80
        upperPanel.add(statusPanel, BorderLayout.CENTER);
        add(upperPanel, BorderLayout.NORTH);

        //设置中心面板 : 棋盘 600*600
        centerPanel.add(chessBoard, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);


        //设置窗口属性
        pack();//自适应大小
        setSize(600, 700); //设置窗口大小
        setVisible(true);
        setResizable(false); //设置窗口不可调整大小
        this.validate(); //验证此容器及其所有子组件。
    }


}
