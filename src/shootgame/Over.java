package shootgame;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Over extends JPanel {

	/**
	 * Create the panel.
	 */
	public Over() {
		setLayout(null);
		
		JLabel lblOver = new JLabel("OVER");
		lblOver.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblOver.setBounds(127, 6, 181, 96);
		add(lblOver);
		
		JLabel lblReTry = new JLabel("从头再来");
		lblReTry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblReTry.setForeground(Start.onPress);
				GameFrame.shootPanel.reInit();
				GameFrame.shootPanel.start();
				GameFrame.card.show(GameFrame.container, "Start");
			}
		});
		lblReTry.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblReTry.setBounds(78, 143, 164, 45);
		add(lblReTry);
		
		JLabel lblBoard = new JLabel("查看积分榜");
		lblBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblBoard.setForeground(Start.onPress);
				
			}
		});
		lblBoard.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblBoard.setBounds(78, 249, 164, 45);
		add(lblBoard);
		
		JLabel lblExit = new JLabel("退出");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblExit.setForeground(Start.onPress);
				GameFrame.frame.dispose();
			}
		});
		lblExit.setForeground(Color.BLACK);
		lblExit.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblExit.setBounds(78, 353, 164, 45);
		add(lblExit);
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(ShootGame.gameover, 0, 0, null);
		super.paint(g);
	}
}
