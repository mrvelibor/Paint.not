package paint;

import java.awt.*;
import java.awt.event.*;

public class Palette extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5704388904774586428L;
	
	private static Color[] color = { Color.white,     Color.yellow, Color.cyan,
									 Color.lightGray, Color.orange, Color.green,
									 Color.gray,      Color.pink,   Color.blue,
									 Color.darkGray,  Color.red, 	Color.magenta,
									 Color.black,	  new Color(150, 90, 60)};
	
	private ColorButton left, right;
	
	
	private class ColorButton extends Button {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1724284373554596098L;

		public ColorButton(Color c, boolean b) {
			super();
			setPreferredSize(new Dimension(24, 24));
			setBackground(c);
			if(b)
				addMouseListener(new ColorSelect(c));
			else
				setEnabled(false);
		}
	}
	
	private class ColorSelect implements MouseListener {
		
		private Color col;

		public ColorSelect(Color c)
		{ col = c; }

		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			int button = e.getButton();
			if(button == MouseEvent.BUTTON1) {
				left.setBackground(col);
				Painter.setColorA(col);
			}
			else if(button == MouseEvent.BUTTON3) {
				right.setBackground(col);
				Painter.setColorB(col);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
	
	private void fillPan() {
		setLayout(new BorderLayout());
		
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(color.length/3+1, 3));
		for(int i = 0; i < color.length; i++)
			p1.add(new ColorButton(color[i], true));
		add(p1, BorderLayout.PAGE_START);
		
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(2, 2));
		p2.add(new Label("L", Label.CENTER)); 				p2.add(new Label("R", Label.CENTER));
		p2.add(left = new ColorButton(Color.black, false)); p2.add(right = new ColorButton(Color.white, false));
		add(p2, BorderLayout.PAGE_END);
	}
	
	public Palette() {
		fillPan();
	}
}
