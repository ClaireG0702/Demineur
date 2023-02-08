import javax.swing.*;
import java.awt.*;


public class Case extends JPanel {
	protected boolean visible;
	protected int posX, posY;
	protected int sizeX, sizeY;
	protected int clickCount;

	public Case(int posX, int posY, int sizeX, int sizeY) {
		this.visible = false;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.clickCount = clickCount;

		setBackground(Color.GRAY);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setBounds(posX, posY, sizeX, sizeY);
	}

	public boolean getVisible() {
		return visible;
	}

	public void putVisible() {
		this.visible = true;
	}
}