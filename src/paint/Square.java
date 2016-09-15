package paint;

import java.awt.*;

public class Square extends Painter {

	public Square(int x)
	{ super(x); }
	
	public Square()
	{ this(20); }

	
	@Override
	protected void display(int x, int y)
	{ foreground.fillRect(x, y, size, size); }

	@Override
	protected void draw(int x, int y) {
		background.fillRect(x, y, size, size);
		if(prevX < x && prevY < y)
			for(int i = 0; i < size; i++) {
				background.drawLine(prevX+i, prevY+size, x, y+size-i);
				background.drawLine(prevX+size, prevY+i, x+size-i, y);
			}
		else if(prevX < x && prevY > y)
			for(int i = 0; i < size; i++) {
				background.drawLine(prevX+i, prevY, x, y+i);
				background.drawLine(prevX+size, prevY+i, x+i, y+size);
			}
		else if(prevX > x && prevY < y)
			for(int i = 0; i < size; i++) {
				background.drawLine(x+i, y, prevX, prevY+i);
				background.drawLine(x+size, y+i, prevX+i, prevY+size);
			}
		else if(prevX > x && prevY > y)
			for(int i = 0; i < size; i++) {
				background.drawLine(x+i, y+size, prevX, prevY+size-i);
				background.drawLine(x+size, y+i, prevX+size-i, prevY);
			}
		else {
			((Graphics2D)background).setStroke(new BasicStroke(size));
			background.drawLine(prevX+size/2, prevY+size/2, x+size/2, y+size/2);
			((Graphics2D)background).setStroke(new BasicStroke(0));
		}
	}

	@Override
	public String toString()
	{ return("Square"); }
	
}
