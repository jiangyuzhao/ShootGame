package shootgame.panels;

import shootgame.GameFrame;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Help extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Help() {
		setLayout(null);
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblHelp.setBounds(217, -25, 177, 110);
		add(lblHelp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 103, 467, 484);
		add(scrollPane);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		scrollPane.setViewportView(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.setText("按↑↓←→键移动\r\n使用Z和X释放技能");
		
		JButton btnReturn = new JButton("返回");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReturn.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.card.show(GameFrame.container, "Start");
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
		btnReturn.setBounds(414, 610, 127, 52);
		btnReturn.setForeground(Color.BLACK);
		add(btnReturn);
		
	}
}
