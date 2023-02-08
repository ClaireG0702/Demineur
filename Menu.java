import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel title = new JLabel("Demineur");
	private JButton newGame = new JButton("Nouvelle partie");
	private JButton loadGame = new JButton("Charger la partie");
	private JButton quit = new JButton("Quitter");

	public Menu() {
		setLocation(150, 100);
    	setSize(500, 500);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setVisible(true);
    	setResizable(false);
    	add(panel);

    	panel.setSize(500, 500);
    	panel.setLocation(0, 0);
    	panel.setLayout(null);
    	panel.setBackground(Color.DARK_GRAY);

    	panel.add(title);
    	title.setBounds(85, 60, 140, 40);
    	title.setForeground(Color.WHITE);
    	title.setFont(new Font("Verdana", Font.PLAIN, 26));

    	panel.add(newGame);
    	newGame.setBounds(85, 150, 160, 60);
    	newGame.setBackground(Color.LIGHT_GRAY);
    	newGame.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			new SetGame();
    			//setVisible(false);
    			dispose();
    		}
    	});

    	panel.add(loadGame);
    	loadGame.setBounds(85, 260, 160, 60);
    	loadGame.setBackground(Color.GRAY);
    	loadGame.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//new Load();
    		}
    	});

    	panel.add(quit);
    	quit.setBounds(85, 380, 130, 40);
    	quit.setBackground(Color.LIGHT_GRAY);
    	quit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    		}
    	});
	}
}