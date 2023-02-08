import javax.swing.*;
import java.awt.*;


public abstract class Grid extends JFrame {
	protected JPanel jeu = new JPanel();
	protected JPanel info = new JPanel();

	public Grid() {
		setLocation(70, 70);
    	setSize(700, 800);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setVisible(true);
        setResizable(false);
    	setLayout(null);

    	add(info);
    	info.setBounds(0, 0, 700, 80);
    	info.setBackground(Color.LIGHT_GRAY);

    	add(jeu);
    	jeu.setBounds(0, 80, 700, 720);
    	jeu.setBackground(Color.LIGHT_GRAY);
	} 
}