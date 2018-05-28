package shootgame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScoreBoard extends JPanel {
	private JTextArea textArea;
	static public final String scoreboardPath = "./scoreboard";
	/**
	 * Create the panel.
	 */
	public ScoreBoard() {
		setLayout(null);
		
		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreboard.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblScoreboard.setBounds(133, 6, 326, 85);
		add(lblScoreboard);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 103, 496, 526);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Baoli TC", Font.BOLD, 32));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setColumns(10);

	}
	public void onShow() {
		String content = "";
		File file = new File(scoreboardPath);
        synchronized (file) {
        	try {  
                FileInputStream fileInputStream = new FileInputStream(file);  
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");  
                BufferedReader reader = new BufferedReader(inputStreamReader);  
                String lineContent = "";  
                int i = 0;
                while ((lineContent = reader.readLine()) != null && i++ < 10) {
                	if(Double.parseDouble(lineContent) == 0) {
                		break;
                	}
                	
                	content += "#"+Integer.toString(i)+"	";
                	content += lineContent;
                	if(Double.parseDouble(lineContent) == Over.getThisScore()) {
                		content += "    (this score)";
                	}
                	content += "\r\n";
                }
                textArea.setText(content);
                fileInputStream.close();
                inputStreamReader.close();
                reader.close();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
	}
}
