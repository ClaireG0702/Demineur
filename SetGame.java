import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SetGame extends JFrame {
	private JPanel panel = new JPanel();
	private JLabel title = new JLabel("Demineur");
	private JLabel sizeY = new JLabel("Choisissez la hauteur :");
	private JTextField height = new JTextField("8");
	private JLabel sizeX = new JLabel("Choisissez la longueur :");
	private JTextField width = new JTextField("10");
	private JLabel minesNbr = new JLabel("Choisissez le nombre de mines :");
	private JTextField mines = new JTextField("10");
	private JButton submit = new JButton("Valider");

	protected int w, h, m;

	public SetGame() {
		// Parametre d'affichage de la fenetre
		setLocation(150, 100); // Definition position par rapport à l'ecran
    	setSize(500, 500); // Definition taille
    	setDefaultCloseOperation(EXIT_ON_CLOSE); // Operation de fermeture
    	setVisible(true); // Fenetre visible
    	setResizable(false);
    	add(panel);
    	
    	
    	panel.setSize(500, 500); // Le panel prend la taille de la fenetre
    	panel.setLocation(0, 0);
    	BoxLayout blayout = new BoxLayout(panel, BoxLayout.Y_AXIS); // Alignement des elements sur l'axe vertical
    	panel.setBorder(new EmptyBorder(new Insets(50, 75, 50, 75))); // Rebords
    	panel.setLayout(blayout);
    	panel.setBackground(Color.DARK_GRAY);

    	// Parametre du titre du jeu
    	panel.add(title);
    	title.setForeground(Color.WHITE); // Definition couleur
    	title.setHorizontalAlignment(JLabel.CENTER); // Position dans le panel
    	title.setFont(new Font("Verdana", Font.PLAIN, 26)); // Police et taille
    	panel.add(Box.createRigidArea(new Dimension(0, 5))); // Ecart entre titre et prochain element
    	// Parametre choix hauteur
    	panel.add(sizeY);
    	sizeY.setForeground(Color.WHITE);
    	sizeY.setHorizontalAlignment(JLabel.CENTER);
    	sizeY.setFont(new Font("Verdana", Font.PLAIN, 16));
    	panel.add(height);
    	panel.add(Box.createRigidArea(new Dimension(0, 10)));

    	// Parametre choix longueur
    	panel.add(sizeX);
    	sizeX.setForeground(Color.WHITE);
    	sizeX.setHorizontalAlignment(JLabel.CENTER);
    	sizeX.setFont(new Font("Verdana", Font.PLAIN, 16));
    	panel.add(width);
    	panel.add(Box.createRigidArea(new Dimension(0, 10)));

    	// Parametre choix nbr mines
    	panel.add(minesNbr);
    	minesNbr.setForeground(Color.WHITE);
    	minesNbr.setHorizontalAlignment(JLabel.CENTER);
    	minesNbr.setFont(new Font("Verdana", Font.PLAIN, 16));
    	panel.add(mines);
    	panel.add(Box.createRigidArea(new Dimension(0, 10)));

    	panel.add(submit);
    	submit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			w = Integer.parseInt(width.getText());
    			h = Integer.parseInt(height.getText());
    			m = Integer.parseInt(mines.getText());

    			if(w > 30) {
    				w = 30;
    			} else if(w < 4) {
    				w = 4;
    			}
    			if(h > 30) {
    				h = 30;
    			} else if(h < 4) {
    				h = 4;
    			}
    			if(m > w*h-1 || m == 0) {
    				m = (w*h)/4;
    			}

    			new Game(h, w, m);
    			dispose();
    		}
    	});

    }
}