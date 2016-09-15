package paint;

import java.awt.*;

public class PaintCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 695794362911069077L;

	private Panel cont;
	private Image background, foreground;
	private Painter painter;
	
	
	public PaintCanvas(Dimension d) {
		super();
		cont = new Panel();
		cont.add(this);
		setSize(d);
		setFocusable(true);
	}
	
	public PaintCanvas(int width, int height)
	{ this(new Dimension(width, height)); }
	
	@Override
	public void setSize(Dimension d) {
		cont.setPreferredSize(d); cont.setMinimumSize(d); cont.setMaximumSize(d); cont.setSize(d);
		setPreferredSize(d); setMinimumSize(d); setMaximumSize(d); super.setSize(d);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice(); 
		GraphicsConfiguration gc = gs.getDefaultConfiguration();
		background = gc.createCompatibleImage(d.width, d.height, Transparency.OPAQUE);
		foreground = gc.createCompatibleImage(d.width, d.height, Transparency.OPAQUE);
		Painter.setCanvas(this);
		clear();
	}
	
	public void clear() {
		Dimension d = getSize();
		background.getGraphics().setColor(Color.white);
		background.getGraphics().fillRect(0, 0, d.width, d.height);
		foreground.getGraphics().drawImage(background, 0, 0, this);
		repaint();
	}
	
	
	public Panel getPanel()
	{ return cont; }
	
	public Image getBackgroundPicture()
	{ return background; }
	
	public Image getForegroundPicture()
	{ return foreground; }
	
	public void setPainter(Painter p) {
		if(painter != null) {
			removeMouseListener(painter);
			removeMouseMotionListener(painter);
		}
		painter = p;
		addMouseMotionListener(painter);
		addMouseListener(painter);
		p.setSize();
	}
	
	
	@Override
	public void paint(Graphics g)
	{ g.drawImage(foreground, 0, 0, this); }
	
	@Override
	public void update(Graphics g)
	{ paint(g); }
	
}
