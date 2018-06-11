package shootgame.panels;

import shootgame.GameFrame;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Options extends JPanel {

	/**
	 * Create the panel.
	 */
	public Options() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Options");
		lblNewLabel.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 6, 241, 85);
		add(lblNewLabel);
		
		JToggleButton toggleButton = new JToggleButton("单人/双人");
		toggleButton.setBounds(122, 200, 161, 29);
		add(toggleButton);

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
		btnReturn.setBounds(183, 576, 127, 52);
		add(btnReturn);

	}
}
