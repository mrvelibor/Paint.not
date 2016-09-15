package paint;

import java.awt.image.BufferedImage;

public class Fill extends Painter {
	
	public Fill()
	{ super(1); }
	
	@Override
	protected void display(int x, int y)
	{ foreground.fillOval(x, y, size, size); }

	@Override
	protected void draw(int x, int y) {
		background.fillOval(x, y, size, size);
		//fill(((BufferedImage) picture).getRGB(x, y), x, y);
	}
	
	private void fill(int rgb, int x, int y) {
		BufferedImage img = (BufferedImage) picture;
		img.setRGB(x, y, colorA.getRGB());
		
		if(img.getRGB(x-1, y-1) == rgb)
			fill(rgb, x-1, y-1);
		if(img.getRGB(x-1, y) == rgb)
			fill(rgb, x-1, y);
		if(img.getRGB(x-1, y+1) == rgb)
			fill(rgb, x-1, y+1);
		
		if(img.getRGB(x, y-1) == rgb)
			fill(rgb, x, y-1);
		if(img.getRGB(x, y+1) == rgb)
			fill(rgb, x, y+1);
		
		if(img.getRGB(x+1, y-1) == rgb)
			fill(rgb, x+1, y-1);
		if(img.getRGB(x+1, y) == rgb)
			fill(rgb, x+1, y);
		if(img.getRGB(x+1, y+1) == rgb)
			fill(rgb, x+1, y+1);
	}

	@Override
	public String toString()
	{ return "Fill"; }

}