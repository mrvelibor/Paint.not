package paint;

import java.awt.*;
import java.awt.event.*;

public abstract class Ruler extends Painter {
	
	protected boolean left, right;
	
	
	public Ruler(int x)
	{ super(x); }
	
	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton(),
			x = e.getX() - size/2,
			y = e.getY() - size/2;
		if(button == MouseEvent.BUTTON1) {
			if(left) {
				draw(x, y);
				prevX = x; prevY = y;
			}
			else if(right) {
				right = false;
				erase(false);
				prevX = prevY = 0;
			}
			else {
				left = true;
				erase(false);
				prevX = x; prevY = y;
			}
		}
		else if(button == MouseEvent.BUTTON3) {
			if(right) {
				draw(x, y);
				prevX = x; prevY = y;
			}
			else if(left) {
				left = false;
				prevX = prevY = 0;
			}
			else {
				right = true;
				erase(true);
				prevX = x; prevY = y;
			}
		}
		paint(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(left || right) {
			int x = e.getX() - size/2,
				y = e.getY() - size/2;
			if(!(prevX == x && prevY == y)) {
				draw(x, y);
				prevX = prevY = 0;
				left = right = false;
				erase(false);
			}
		}
		mouseMoved(e);
	}

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
			foreground.drawImage(picture, 0, 0, canvas);
			display(x, y);
		}
		else foreground.drawImage(picture, 0, 0, canvas);
		canvas.repaint();
	}
	
	protected abstract void display(int x, int y);
	
	protected abstract void draw(int x, int y);

	
	public void setSize(int x) {
		super.setSize(x);
		((Graphics2D)background).setStroke(new BasicStroke(size));
		((Graphics2D)foreground).setStroke(new BasicStroke(size));
	}
	
}
