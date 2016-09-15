package paint;

import java.awt.*;
import java.awt.event.*;

public abstract class Painter implements MouseMotionListener, MouseListener {

	protected static PaintCanvas canvas;
	protected static Color colorA = Color.black, colorB = Color.white;
	protected static Image picture;
	protected static Graphics foreground, background;
	
	protected boolean pressed;
	
	protected int size, prevX, prevY;
	
	
	public static void setCanvas(PaintCanvas c) {
		canvas = c;
		picture = canvas.getBackgroundPicture();
		background = picture.getGraphics();
		foreground = canvas.getForegroundPicture().getGraphics();
		background.setColor(colorA);
		foreground.setColor(colorA);
	}
	
	public Painter(int x)
	{ setSize(x); }
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e)
	{ paint(null); }
	
	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1)
			pressed = !pressed;
		else if(button == MouseEvent.BUTTON3) {
			pressed = !pressed;
			erase(true);
		}
		paint(e);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{ pressed = false; erase(false); }

	@Override
	public void mouseDragged(MouseEvent e)
	{ mouseMoved(e); }

	@Override
	public void mouseMoved(MouseEvent e)
	{ paint(e); }
	
	
	private void paint(MouseEvent e) {
		if(e != null) {
			int x = e.getX() - size/2,
				y = e.getY() - size/2;
			if(pressed)
				draw(x, y);
			foreground.drawImage(picture, 0, 0, canvas);
			if(!pressed)
				display(x, y);
			prevX = x; prevY = y;
		}
		else foreground.drawImage(picture, 0, 0, canvas);
		canvas.repaint();
	}
	
	protected abstract void display(int x, int y);
	
	protected abstract void draw(int x, int y);	
	
	protected void erase(Boolean b) {
		background.setColor(b ? colorB : colorA);
		foreground.setColor(b ? colorB : colorA);
	}

	
	public int getSize()
	{ return size; }
	
	public void setSize(int x) {
		size = x;
		if(size < 1)
			size = 1;
	}
	
	public void setSize()
	{ setSize(size); }
	
	public static void setColorA(Color c) {
		colorA = c;
		background.setColor(c);
		foreground.setColor(c);
	}
	
	public static void setColorB(Color c)
	{ colorB = c; }
	
	public abstract String toString();
	
}
