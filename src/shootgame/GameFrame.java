package shootgame;

import shootgame.panels.*;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {
	//暴露这两个组件供切换Panel时使用
	public static CardLayout card = new CardLayout();
	public static JPanel container = new JPanel(card);
	public static ShootGame shootPanel = new ShootGame();
	public static Pause pausePanel = new Pause();
	public static GameFrame frame;
	public static ScoreBoard scoreboard = new ScoreBoard();
	/**
	 * Create the frame.
	 * 在构建函数中进行Frame的设置操作
	 */
	public GameFrame() {
		super("Come on! Avangers!");
		frame = this;
		setSize(ShootGame.WIDTH, ShootGame.HEIGHT); // 设置大小
        requestFocus(); // 让键盘事件有效
        setAlwaysOnTop(true); // 设置其总在最上
        setContentPane(container);
        System.out.println("container set!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 默认关闭操作
        setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
        container.add(shootPanel, "ShootGame");
        container.add(new Start(), "Start");
        container.add(pausePanel, "Pause");
        container.add(new Over(), "Over");
        container.add(new Options(), "Options");
        container.add(new Help(), "Help");
        container.add(scoreboard, "Scoreboard");
        
        card.show(container, "Start");
	}

}
