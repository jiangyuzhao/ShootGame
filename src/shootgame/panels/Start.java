package shootgame.panels;

import shootgame.GameFrame;
import shootgame.ResourceManager;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
		
		JLabel lblStart = new JLabel("开始");
		lblStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStart.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.shootPanel.setStateStart();
				GameFrame.card.show(GameFrame.container, "ShootGame");
				GameFrame.shootPanel.start();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblStart.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblStart.setForeground(Color.BLACK);
			}
		});
		
		lblStart.setForeground(Color.BLACK);
		lblStart.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblStart.setBounds(140, 376, 146, 48);
		add(lblStart);
		
		JLabel lblOptions = new JLabel("选项");
		lblOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblOptions.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "Options");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblOptions.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblOptions.setForeground(Color.BLACK);
			}
		});
		lblOptions.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblOptions.setForeground(Color.BLACK);
		lblOptions.setBounds(140, 438, 106, 45);
		add(lblOptions);
		
		JLabel lblHelp = new JLabel("帮助");
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHelp.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "Help");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblHelp.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblHelp.setForeground(Color.BLACK);
			}
		});
		lblHelp.setForeground(Color.BLACK);
		lblHelp.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblHelp.setBounds(140, 500, 106, 45);
		add(lblHelp);
		
		JLabel lblExit = new JLabel("退出");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblExit.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.BLACK);
			}
		});
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblExit.setBounds(140, 562, 106, 45);
		add(lblExit);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceManager.getImage("startBackground"),
				0, 0, this.getWidth(), this.getHeight(), null);
		//System.out.println("drew!");
	}
}
