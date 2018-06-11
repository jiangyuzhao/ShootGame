package shootgame.panels;

import shootgame.GameFrame;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class About extends JPanel {

	/**
	 * Create the panel.
	 */
	public About() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("About");
		lblNewLabel.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 6, 241, 85);
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 103, 467, 484);
		add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		textArea.setColumns(10);
		textArea.setEditable(false);
		textArea.setText("团队成员：\r\n何昊 潘兴禄 李天翼 秦雨轩 王乐强 缪舜");
		scrollPane.setViewportView(textArea);

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
		btnReturn.setBounds(180, 620, 127, 52);
		add(btnReturn);

	}
}
