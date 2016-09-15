package paint;

public class Rectangle extends Ruler {
	
	public boolean fill;
	
	private Rectangle(int x, boolean f)
	{ super(x); fill = f; }
	
	public Rectangle(int x)
	{ this(x, false); }
	
	public Rectangle(boolean f)
	{ this(1, f); }
	
	public Rectangle()
	{ this(6, false); }

	@Override
	protected void display(int x, int y) {
		if(prevX == prevY && prevX == 0)
			foreground.fillRect(x, y, size, size);
		else {
			int stX, stY;
			stX = (prevX > x ? x : prevX);
			stY = (prevY > y ? y : prevY);
			if(fill)
				foreground.fillRect(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
			else
				foreground.drawRect(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
		}
	}

	@Override
	protected void draw(int x, int y) {
		int stX, stY;
		stX = (prevX > x ? x : prevX);
		stY = (prevY > y ? y : prevY);
		if(fill)
			background.fillRect(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
		else
			background.drawRect(stX, stY, Math.abs(prevX-x), Math.abs(prevY-y));
	}

	@Override
	public String toString()
	{ return "Rectangle " + (fill ? 'F' : 'E'); }

}
