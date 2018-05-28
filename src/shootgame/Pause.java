package shootgame;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author teddy
 * 通过Pause.pause()函数可以使程序进入到pause界面，
 * 不过pause界面中的返回等功能在目前还没有作用。
 */
public class Pause extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pause() {
		setLayout(null);
		
		JLabel lblReturn = new JLabel("返回游戏");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblReturn.setForeground(Start.onPress);
				GameFrame.card.show(GameFrame.container, "ShootGame");
				GameFrame.shootPanel.setStateRunning();
			}
		});
		lblReturn.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblReturn.setBounds(72, 90, 164, 45);
		add(lblReturn);
		
		JLabel lblSaveExit = new JLabel("保存并退出");
		lblSaveExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSaveExit.setForeground(Start.onPress);
			}
		});
		lblSaveExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblSaveExit.setBounds(72, 160, 180, 45);
		add(lblSaveExit);
		
		JLabel lblExit = new JLabel("清除进度");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblExit.setForeground(Start.onPress);
				GameFrame.shootPanel.setStateOver();
				GameFrame.card.show(GameFrame.container, "Over");
			}
		});
		lblExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblExit.setBounds(72, 230, 180, 45);
		add(lblExit);
		
		JLabel lblPause = new JLabel("PAUSE");
		lblPause.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblPause.setHorizontalAlignment(SwingConstants.CENTER);
		lblPause.setBounds(133, 6, 209, 67);
		add(lblPause);

	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(ShootGame.pause, 0, 0, null);
		super.paint(g);
	}
	//
	public static void pause() {
		//需要set shootgame state为pause
		GameFrame.card.show(GameFrame.container, "Pause");
	}
}
