package paint;

import java.awt.*;
import java.awt.event.*;

public class Brushes extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4684093668062126014L;
	
	private static Painter[] brush = { new Circle(), new Square() },
							 figure = { new Line(), new Rectangle(), new Rectangle(true), new Oval(), new Oval(true) },
							 other = { new Fill() };
	
	private PaintCanvas canvas;
	private BrushButton selected;
	private TextField thickness;
	
	
	private class BrushButton extends Button {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6508587624978064127L;
		
		private Painter paint;
		
		public BrushButton(Painter p) {
			super(p.toString());
			paint = p;
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{ ((BrushButton) e.getSource()).select(); }
			});
		}
		
		public void select() {
			selected.setEnabled(true);
			canvas.setPainter(paint);
			selected = this;
			setEnabled(false);
			thickness.setText(getThickness());
		}
		
		public void setThickness(int t)
		{ paint.setSize(t); }
		
		public String getThickness()
		{ return paint.getSize() + ""; }
		
	}
	
	
	private void fillPan() {
		setLayout(new GridLayout(brush.length+figure.length+other.length+8, 1));
		
		add(new Label("Brushes"));
		add(selected = new BrushButton(brush[0]));
		for(int i = 1; i < brush.length; i++)
			add(new BrushButton(brush[i]));
		
		add(new Label("Figures"));
		for(int i = 0; i < figure.length; i++)
			add(new BrushButton(figure[i]));
		
		add(new Label("Other"));
		for(int i = 0; i < other.length; i++)
			add(new BrushButton(other[i]));
		
		add(new Label());
		add(new Label("Thickness:"));
		thickness = new TextField();
		thickness.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				String s = ((TextField)e.getSource()).getText();
				try {
					int t = Integer.parseInt(s);
					selected.setThickness(t);
				} catch(NumberFormatException ex) {
					thickness.setText(selected.getThickness());
				}
			}
		});
		add(thickness);
		
		add(new Label());
		Button b = new Button("Clear");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{ canvas.clear(); }
		});
		add(b);
		
		selected.select();
		canvas.setPainter(brush[0]);
		
	}

	public Brushes(PaintCanvas c) {
		super();
		canvas = c;
		fillPan();
	}

}
