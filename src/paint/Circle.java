package paint;

import java.awt.*;

public class Circle extends Painter {

	public Circle(int x)
	{ super(x); }
	
	public Circle()
	{ this(20); }
	
	@Override
	protected void display(int x, int y)
	{ foreground.fillOval(x, y, size, size); }

	@Override
	protected void draw(int x, int y) {
		background.fillOval(x, y, size, size);
		double dist = Math.sqrt( Math.pow(prevX-x, 2) + Math.pow(prevY-y, 2) );
		if(dist > size/2) {
			((Graphics2D)background).setStroke(new BasicStroke(size));
			background.drawLine(prevX+size/2, prevY+size/2, x+size/2, y+size/2);
		}
	}

	@Override
	public String toString()
	{ return "Circle"; }

}