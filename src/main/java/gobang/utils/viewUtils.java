package gobang.utils;

import gobang.client.ClientApp;

import javax.swing.*;

/**
 * 视图工具类
 *
 * @author SK
 * @date 2024/05/25
 */
public class viewUtils {

    /**
     * 通用分隔基础单元大小
     * <p> == divisionSize in ChessBoard</p>
     */
    public static final int DSize = 30;

    /**
     * 绑定按键到动作
     */
    public static void bindKeyToAction(ClientApp jpanel, String key, String actionName, Action action) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
        jpanel.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionName);
        jpanel.getRootPane().getActionMap().put(actionName, action);
    }

}
