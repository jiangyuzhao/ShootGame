package shootgame.panels;

import shootgame.GameFrame;
import shootgame.ResourceManager;
import shootgame.ScoreBoard;

import java.awt.Graphics;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Over extends JPanel {
	/**
	 * Create the panel.
	 */
	private static double thisScore = 0;
	public Over() {
		setLayout(null);
		
		JLabel lblOver = new JLabel("OVER");
		lblOver.setFont(new Font("Baoli SC", Font.BOLD | Font.ITALIC, 60));
		lblOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblOver.setBounds(127, 6, 181, 96);
		lblOver.setForeground(new Color(255,255,255));
		add(lblOver);
		
		JButton btnReTry = new JButton("从头再来");
		btnReTry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReTry.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.shootPanel.reInit();
				GameFrame.shootPanel.start();
				GameFrame.card.show(GameFrame.container, "Start");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnReTry.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReTry.setForeground(Color.BLACK);
			}
		});
		btnReTry.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnReTry.setBounds(90, 143, 180, 45);
		add(btnReTry);
		
		JButton btnBoard = new JButton("查看积分榜");
		btnBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBoard.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				GameFrame.scoreboard.onShow();
				GameFrame.card.show(GameFrame.container, "Scoreboard");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnBoard.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBoard.setForeground(Color.BLACK);
			}
		});
		btnBoard.setFont(new Font("Baoli TC", Font.BOLD | Font.ITALIC, 32));
		btnBoard.setBounds(90, 249, 180, 45);
		add(btnBoard);

		JButton btnExit = new JButton("退出");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setForeground(Start.onPress);
			}
			@Override
			public void mouseClicked(MouseEvent e) { System.exit(0); }
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
		btnExit.setBounds(90, 353, 180, 45);
		add(btnExit);
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public static double getThisScore() {
		return thisScore;
	}
	
	/**
	 * 将本次对局信息加入到积分榜中
	 * @author teddy
	 * @param score
	 */
	public static void update(double score) {
		thisScore = score;
		File file = new File(ScoreBoard.scoreboardPath);
		if(!file.exists()) {
			try {  
                file.createNewFile();// 如果文件不存在创建文件  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		double []scoreboard = new double[20];
        synchronized (file) {
        	try {  
                FileInputStream fileInputStream = new FileInputStream(file);  
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");  
                BufferedReader reader = new BufferedReader(inputStreamReader);  
                String lineContent = "";  
                int i = 0;
                while ((lineContent = reader.readLine()) != null && i < 10) {  
                    scoreboard[i++] = Double.parseDouble(lineContent);  
                }
                fileInputStream.close();  
                inputStreamReader.close();  
                reader.close();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        	
        	try {
            	FileWriter fw = new FileWriter(ScoreBoard.scoreboardPath);  
				fw.write(Double.toString(score));
				fw.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	insertIntoScoreboard(scoreboard, score);
            try {
            	FileWriter fw = new FileWriter(ScoreBoard.scoreboardPath); 
            	for(int i = 0; i < 10; i++) {
            		fw.write(Double.toString(scoreboard[i])+"\n");
            	}
				fw.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        } 
	}
	private static void insertIntoScoreboard(double []scoreboard, double record) {
		for(int i = 0; i < 10; i++) {
			if(record > scoreboard[i]) {
				for(int j = 8;j > i; j--) {
					scoreboard[j] = scoreboard[j-1];
				}
				scoreboard[i] = record;
				break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceManager.getImage("overBackground"),
				0, 0, this.getWidth(), this.getHeight(), null);
	}
}
