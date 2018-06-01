package shootgame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

/**
 * 这个类是游戏逻辑与设备输入之间的中间层
 * 每次更新游戏状态，游戏物体类在update()方法中读取此类来确认用户是否对某个键有输入
 * 每次侦测到用户输入，则将这个类中的对应标位更新
 * 请注意将这个类设计成线程安全的
 *
 * 包装起来的优势主要在于：
 * 1. 游戏上层逻辑与用户输入无关，也就是我们要改变键位，只用修改这个类即可
 * 2. 避免了直接设置游戏类的值产生的线程安全问题
 *
 * @author hehao
 */
public class InputManager {
    /**
     * 处理的输入的类型，以后可以再拓展，例如技能按键，特殊攻击按键等等
     */
    public enum Key {
        LEFT("Left", KeyEvent.VK_LEFT),
        RIGHT("Right", KeyEvent.VK_RIGHT),
        UP("Up", KeyEvent.VK_UP),
        DOWN("Down", KeyEvent.VK_DOWN),
        ESCAPE("Escape", KeyEvent.VK_ESCAPE),
        Z("Z", KeyEvent.VK_Z),
        X("X", KeyEvent.VK_X),
        C("C", KeyEvent.VK_C),
        V("V", KeyEvent.VK_V);


        private String name;
        private int keyCode;

        Key(String name, int keyCode) {
            this.name = name;
            this.keyCode = keyCode;
        }

        public String getName() {
            return name;
        }

        public int getKeyCode() {
            return keyCode;
        }
    }

    private static final int PRESSED = 1;
    private static final int RELEASED = 2;
    private ShootGame game;
    private Map<Key, Boolean> dirMap = new EnumMap<>(Key.class);

    InputManager(ShootGame game) {
        this.game = game;
        setKeyBindings();
    }

    /**
     * 由游戏上层物体调用，在每一帧确认指定类型输入是否发生
     *
     * @param key     输入类型
     * @return 指定类型有输入返回true，否则false
     */
    public synchronized boolean getInput(Key key) {
        Boolean hasInput = dirMap.get(key);
        return hasInput != null && hasInput;
    }

    /**
     * 游戏结束时调用，清空上次游戏的所有输入数据
     */
    public void clearInput() {
        dirMap.clear();
    }

    /**
     * 将每一种类型绑定一个KeyStroke，这样实现可以快速响应键盘事件
     */
    private void setKeyBindings() {
        int condition = WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = game.getInputMap(condition);
        ActionMap actionMap = game.getActionMap();

        for (Key key : Key.values()) {
            KeyStroke keyPressed = KeyStroke.getKeyStroke(key.getKeyCode(), 0, false);
            KeyStroke keyReleased = KeyStroke.getKeyStroke(key.getKeyCode(), 0, true);

            inputMap.put(keyPressed, key.toString() + PRESSED);
            inputMap.put(keyReleased, key.toString() + RELEASED);

            actionMap.put(key.toString() + PRESSED, new DirAction(key, PRESSED));
            actionMap.put(key.toString() + RELEASED, new DirAction(key, RELEASED));
        }

    }

    private class DirAction extends AbstractAction {

        private int pressedOrReleased;
        private Key key;

        public DirAction(Key key, int pressedOrReleased) {
            this.key = key;
            this.pressedOrReleased = pressedOrReleased;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (pressedOrReleased == PRESSED) {
                dirMap.put(key, Boolean.TRUE);
            } else if (pressedOrReleased == RELEASED) {
                dirMap.put(key, Boolean.FALSE);
            }
        }

    }
}
