package shootgame.panels;

import shootgame.GameFrame;
import shootgame.ResourceManager;

import java.awt.Graphics;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JPanel {
	
	public static Color onPress = new Color(204, 204, 51);
	/**
	 * Create the panel.
	 */
	public Start() {
		setLayout(null);

		JButton btnStart = new JButton("开始");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStart.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.shootPanel.setStateRunning();
				GameFrame.card.show(GameFrame.container, "ShootGame");
				GameFrame.shootPanel.start();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnStart.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStart.setForeground(Color.BLACK);
			}
		});
		
		btnStart.setForeground(Color.BLACK);
		btnStart.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnStart.setBounds(140, 376, 146, 48);
		add(btnStart);
		
		JButton btnOptions = new JButton("选项");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnOptions.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "Options");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnOptions.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnOptions.setForeground(Color.BLACK);
			}
		});
		btnOptions.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnOptions.setForeground(Color.BLACK);
		btnOptions.setBounds(140, 438, 146, 48);
		add(btnOptions);
		
		JButton btnHelp = new JButton("帮助");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHelp.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "Help");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnHelp.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnHelp.setForeground(Color.BLACK);
			}
		});
		btnHelp.setForeground(Color.BLACK);
		btnHelp.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnHelp.setBounds(140, 500, 146, 48);
		add(btnHelp);

		JButton btnExit = new JButton("退出");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
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
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnExit.setBounds(140, 562, 146, 48);
		add(btnExit);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceManager.getImage("startBackground"),
				0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(ResourceManager.getImage("logo"), 100, 100, 400, 80, null);
		//System.out.println("drew!");
	}
}
