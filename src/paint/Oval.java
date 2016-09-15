package paint;

public class Oval extends Ruler {
	
	public boolean fill;
	
	private Oval(int x, boolean f)
	{ super(x); fill = f; }
	
	public Oval(int x)
	{ this(x, false); }
	
	public Oval(boolean f)
	{ this(1, f); }
	
	public Oval()
	{ this(6, false); }

	@Override
	protected void display(int x, int y) {
		if(prevX == prevY && prevX == 0)
			foreground.fillOval(x, y, size, size);
		else {
			int stX, stY;
			stX = (prevX > x ? x : prevX);
			stY = (prevY > y ? y : prevY);
			if(fill)
				foreground.fillOval(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
			else
				foreground.drawOval(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
		}
	}

	@Override
	protected void draw(int x, int y) {
		int stX, stY;
		stX = (prevX > x ? x : prevX);
		stY = (prevY > y ? y : prevY);
		if(fill)
			background.fillOval(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
		else
			background.drawOval(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
	}

	@Override
	public String toString()
	{ return "Oval " + (fill ? 'F' : 'E'); }

}