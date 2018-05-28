package shootgame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToggleButton;
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
		
		JLabel lblReturn = new JLabel("返回");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblReturn.setForeground(Start.onPress);
				GameFrame.card.show(GameFrame.container, "Start");
			}
		});
		lblReturn.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		lblReturn.setBounds(183, 576, 127, 52);
		add(lblReturn);

	}
}
