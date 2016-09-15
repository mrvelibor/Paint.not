package paint;

import java.awt.*;
import java.awt.event.*;

public class Paint extends Frame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4342658467941780820L;

	private PaintCanvas canvas;
	
	
	private void fillWindow() {
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);
		
		canvas = new PaintCanvas(1280, 720);
		add(canvas.getPanel(), BorderLayout.CENTER);
		
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		p.add(new Brushes(canvas), BorderLayout.PAGE_START);
		p.add(new Palette(), BorderLayout.PAGE_END);
		add(p, BorderLayout.LINE_START);
	}
	
	public Paint() {
		super("Paint");
		setBounds(100, 100, 1500, 900);
		setMinimumSize(new Dimension(640, 480));
		fillWindow();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{ dispose(); }
		});
		setVisible(true);
	}
	
	
	public static void main(String[] argc)
	{ new Paint(); }
}
