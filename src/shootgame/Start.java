package shootgame;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JPanel {
	
	public static Color onPress = new Color(204, 204, 51);
	/**
	 * Create the panel.
	 */
	public Start() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblStart = new JLabel("开始");
		lblStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblStart.setForeground(onPress);
				GameFrame.shootPanel.setStateStart();
				GameFrame.card.show(GameFrame.container, "ShootGame");
				GameFrame.shootPanel.start();
			}
		});
		
		lblStart.setForeground(new Color(0, 0, 0));
		lblStart.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblStart.setBounds(158, 376, 146, 48);
		add(lblStart);
		
		JLabel lblOptions = new JLabel("选项");
		lblOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblOptions.setForeground(onPress);
				GameFrame.card.show(GameFrame.container, "Options");
			}
		});
		lblOptions.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblOptions.setForeground(new Color(0, 0, 0));
		lblOptions.setBounds(158, 438, 106, 45);
		add(lblOptions);
		
		JLabel lblHelp = new JLabel("帮助");
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblHelp.setForeground(onPress);
				GameFrame.card.show(GameFrame.container, "Help");
			}
		});
		lblHelp.setForeground(Color.BLACK);
		lblHelp.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblHelp.setBounds(158, 500, 106, 45);
		add(lblHelp);
		
		JLabel lblExit = new JLabel("退出");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblExit.setForeground(onPress);
				GameFrame.frame.dispose();
			}
		});
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblExit.setBounds(158, 562, 106, 45);
		add(lblExit);
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(ShootGame.start, 0, 0, null);
		super.paint(g);
	}
}
