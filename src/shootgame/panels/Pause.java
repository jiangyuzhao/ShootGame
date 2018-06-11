package shootgame.panels;

import shootgame.GameFrame;
import shootgame.ResourceManager;

import java.awt.Graphics;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
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
		
		JButton btnReturn = new JButton("返回游戏");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturn.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "ShootGame");
				GameFrame.shootPanel.setStateRunning();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnReturn.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReturn.setForeground(Color.BLACK);
			}
		});
		btnReturn.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnReturn.setBounds(72, 90, 180, 45);
		add(btnReturn);

		JButton btnSaveExit = new JButton("保存并退出");
		btnSaveExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSaveExit.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnSaveExit.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSaveExit.setForeground(Color.BLACK);
			}
		});
		btnSaveExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnSaveExit.setBounds(72, 160, 180, 45);
		add(btnSaveExit);

		JButton btnExit = new JButton("清除进度");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.shootPanel.setStateOver();
				GameFrame.card.show(GameFrame.container, "Over");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnExit.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setForeground(Color.BLACK);
			}
		});
		btnExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnExit.setBounds(72, 230, 180, 45);
		add(btnExit);
		
		JLabel lblPause = new JLabel("PAUSE");
		lblPause.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblPause.setHorizontalAlignment(SwingConstants.CENTER);
		lblPause.setBounds(133, 6, 209, 67);
		add(lblPause);

	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceManager.getImage("pauseBackground"),
				0, 0, this.getWidth(), this.getHeight(), null);
	}
	//
	public static void pause() {
		//需要set shootgame state为pause
		GameFrame.card.show(GameFrame.container, "Pause");
	}
}
