import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Game extends Grid implements MouseListener {
	private int hauteur = jeu.getSize().height;
    private int largeur = jeu.getSize().width;
    protected int posX = 0, posY = 0;
    protected int h, w, m;
    private int[] posMinesX, posMinesY;

    private JLabel sus = new JLabel("?");
    private JLabel star = new JLabel("*");
    private int clickCount = 0;

	public Game(int h, int w, int m) {
    	this.h = h;
    	this.w = w;
    	this.m = m;

    	jeu.setLayout(null);
    
    	genInfo();
        genVoidGrid();
        genMines();
	}

	public void genInfo() {
		JLabel textMines = new JLabel("Mines restantes : "+m);
		info.add(textMines);
		textMines.setFont(new Font("Verdana", Font.PLAIN, 18));
	}

	public void genVoidGrid() {
		int sizeX = largeur/w;
        int sizeY = hauteur/h;
        posX = 0;
        posY = 0;

        for(int i = 1; i <= h; i++) { // Génération grille avec les cases cachees
            posX = 0;
            for(int j = 1; j <= w; j++) {
                Case cVoid = new Case(posX, posY, sizeX, sizeY);
                //cVoid.visible = false;

                jeu.add(cVoid);
                cVoid.setBackground(Color.DARK_GRAY);
                cVoid.addMouseListener(this);

                posX += sizeX; 
            }
            posY += sizeY;
        }
	}

	public void genMines() {
		int maxX = w, maxY = h;
    	int min = 1;
    	int rangeX = maxX - min + 1, rangeY = maxY - min + 1;
    	int[] posMinesX = new int[m], posMinesY = new int[m];

    	for(int i = 0; i < m; i++) { // Generation des positions des mines
    		posMinesX[i] =(int) (Math.random() * rangeX) + min;
    		posMinesY[i] =(int) (Math.random() * rangeY) + min;
    		
    		for(int v = 0; v < i; v++) {
    			if(posMinesX[i] == posMinesX[v] && posMinesY[i] == posMinesY[v]) {
    				i--;
    			}
    		}
    		this.posMinesX = posMinesX;
    		this.posMinesY = posMinesY;
    	}
	}

	public void genGame(Case c) {
		int sizeX = largeur/w;
    	int sizeY = hauteur/h;
    	int counter = 0;
        posX = 0;
        posY = 0;

		for(int i = 1; i <= h; i++) { // Génération grille avec mines
    		posX = 0;

    		for(int j = 1; j <= w; j++) {   				
    			counter = 0;

                if(c.posX==posX && c.posY==posY) {
                c.setBackground(Color.GRAY);

    			for(int k = 0; k < m; k++) { // Generation des chiffres autour des mines
    				if(j == posMinesX[k] && i == posMinesY[k]) {
    					c.setBackground(Color.RED);
                        defeat();
    				}

   					if(j-1 == posMinesX[k] && i-1 == posMinesY[k]) {
   						counter++;
   					}
   					if(j == posMinesX[k] && i-1 == posMinesY[k]) {
   						counter++;
   					}
   					if(j+1 == posMinesX[k] && i-1 == posMinesY[k]) {
   						counter++;
   					}
   					if(j-1 == posMinesX[k] && i == posMinesY[k]) {
   						counter++;
   					}
   					if(j+1 == posMinesX[k] && i == posMinesY[k]) {
   						counter++;
   					}
   					if(j-1 == posMinesX[k] && i+1 == posMinesY[k]) {
   						counter++;
   					}
   					if(j == posMinesX[k] && i+1 == posMinesY[k]) {
   						counter++;
   					}
   					if(j+1 == posMinesX[k] && i+1 == posMinesY[k]) {
   						counter++;
   					}
   				}
                if(counter > 0 && c.getBackground()!=Color.RED) {
                        String s = Integer.toString(counter);
                        JLabel number = new JLabel(s);
                        c.add(number);
                        number.setForeground(Color.WHITE);
                    }
                }
                /*if(counter == 0) {
                    reveal(c);
                }*/
                posX += sizeX;			
    		}
    		posY += sizeY;
    	}
	}

    public void defeat() {
        JFrame frame = new JFrame();
        frame.setSize(200, 90);
        frame.setLocation(largeur/2, hauteur/2);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel defeatMessage = new JLabel("Défaite !");
        frame.add(defeatMessage);
    }

    /*public void reveal(Case c) {
        Case leftUp = new Case(c.posX-c.sizeX, c.posY-c.sizeY, c.sizeX, c.sizeY);
        Case up = new Case(c.posX, c.posY-c.sizeY, c.sizeX, c.sizeY);
        Case rightUp = new Case(c.posX+c.sizeX, c.posY-c.sizeY, c.sizeX, c.sizeY);
        Case left = new Case(c.posX-c.sizeX, c.posY, c.sizeX, c.sizeY);
        Case right = new Case(c.posX+c.sizeX, c.posY, c.sizeX, c.sizeY);
        Case leftDown = new Case(c.posX-c.sizeX, c.posY+c.sizeY, c.sizeX, c.sizeY);
        Case down = new Case(c.posX, c.posY+c.sizeY, c.sizeX, c.sizeY);
        Case rightDown = new Case(c.posX+c.sizeX, c.posY+c.sizeY, c.sizeX, c.sizeY);

        if(leftUp.getBackground()==Color.DARK_GRAY && leftUp.clickCount==0 && !leftUp.visible) {
            genGame(leftUp);
            jeu.updateUI();
            leftUp.putVisible();
        }
        if(up.getBackground()==Color.DARK_GRAY && up.clickCount==0 && !up.visible) {
            genGame(up);
            jeu.updateUI();
            up.putVisible();
        }
        if(rightUp.getBackground()==Color.DARK_GRAY && rightUp.clickCount==0 && !rightUp.visible) {
            genGame(rightUp);
            jeu.updateUI();
            rightUp.putVisible();
        }
        if(left.getBackground()==Color.DARK_GRAY && left.clickCount==0 && !left.visible) {
            genGame(left);
            jeu.updateUI();
            left.putVisible();
        }
        if(right.getBackground()==Color.DARK_GRAY && right.clickCount==0 && !right.visible) {
            genGame(right);
            jeu.updateUI();
            right.putVisible();
        }
        if(leftDown.getBackground()==Color.DARK_GRAY && leftDown.clickCount==0 && !leftDown.visible) {
            genGame(leftDown);
            jeu.updateUI();
            leftDown.putVisible();
        }
        if(down.getBackground()==Color.DARK_GRAY && down.clickCount==0 && !down.visible) {
            genGame(down);
            jeu.updateUI();
            down.putVisible();
        }
        if(rightDown.getBackground()==Color.DARK_GRAY && rightDown.clickCount==0 && !rightDown.visible) {
            genGame(rightDown);
            jeu.updateUI();
            rightDown.putVisible();
        }    
    }*/

    public void suspect(Case c) { // Affiche un ? sur la case
        c.add(sus, BorderLayout.CENTER);
        sus.setForeground(Color.PINK);
        sus.setFont(new Font("Verdana", Font.PLAIN, 18));
        jeu.updateUI();
    }

    public void thereMine(Case c) { // Affiche une * sur la case
        c.add(star);
        star.setForeground(Color.PINK);
        star.setFont(new Font("Verdana", Font.PLAIN, 18));
        jeu.updateUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Case panel =(Case) e.getSource();
        if(e.getButton()==MouseEvent.BUTTON1) {
            if(panel.getBackground()==Color.DARK_GRAY && panel.clickCount==0 && !panel.visible) {
                genGame(panel);
                jeu.updateUI();
                panel.putVisible();
            }
        } else if(e.getButton()==MouseEvent.BUTTON3) {
            if(!panel.visible) {
                if(panel.clickCount==0){ 
                    suspect(panel);
                    panel.clickCount++;
                } else if(panel.clickCount==1) {
                    panel.remove(sus);
                    thereMine(panel);
                    panel.clickCount++;
                } else {
                    panel.remove(star);
                    jeu.updateUI();
                    panel.clickCount = 0;
                }
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }   	
}