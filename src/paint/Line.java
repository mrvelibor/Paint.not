package paint;

public class Line extends Ruler {

	public Line(int x)
	{ super(x); }
	
	public Line()
	{ this(6); }

	@Override
	protected void display(int x, int y) {
		if(prevX == prevY && prevX == 0)
			foreground.fillRect(x, y, size, size);
		else
			foreground.drawLine(prevX, prevY, x, y);
	}

	@Override
	protected void draw(int x, int y) {
		background.drawLine(prevX, prevY, x, y);
	}

	@Override
	public String toString()
	{ return "Line"; }

}
