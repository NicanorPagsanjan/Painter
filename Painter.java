

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.EmptyStackException;

public class Painter extends JPanel implements MouseMotionListener, MouseListener {
	
	private static final int FRAME_SIZE = 600;
	private static final int REPAINT_REFRESH = 64;
	private static final int DEFAULT_PENSIZE = 12;
	private static final Color DEFAULT_COLOR = Color.red;
	private static final Color DEFAULT_BG = Color.white;
	
	private ArrayList<ColorPoint> points;
	private Stack<ArrayList<ColorPoint>> stack;
	JSlider penSlider;
	private Color currentColor;
	private boolean clicked;
	private int repaintCounter;
	private int penSize;
	
	public Painter() {
		super();
		
		points = new ArrayList<ColorPoint>();
		stack = new Stack<ArrayList<ColorPoint>>();
	
		currentColor = DEFAULT_COLOR;
		setBackground(DEFAULT_BG);
		clicked = false;
		repaintCounter = 0;
		penSize = DEFAULT_PENSIZE;
		setPreferredSize(new Dimension(FRAME_SIZE,FRAME_SIZE));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaintCounter++;
		
		for (ColorPoint c : points) {
			g.setColor(c.getColor());
			
			if(c.getColor().equals(Color.white)){
				// eraser
				g.fillOval((int)c.getPoint().getX(),(int)c.getPoint().getY(),c.getSize()*2,c.getSize()*2);
			} else {
				// other color
				g.fillOval((int)c.getPoint().getX(),(int)c.getPoint().getY(),c.getSize(),c.getSize());
			}
		}
		
		// back up arrayList every few repaints
		if (repaintCounter > REPAINT_REFRESH){
			backup();
			repaintCounter = 0;
		}
	}
	
	public void setColor(Color c) {
		// backup before changing colors
		backup();
		currentColor = c;
	}
	
	public void setPenSize(int size) {
		penSize = size;
		repaint();
	}
	
	public void clear() {
		// backup before clearing, allows for undo clear
		backup();
		points.clear();
		repaint();
	}
	
	public void backup() {
		// create new arrayList with the same elements as points, then add to stack
		ArrayList<ColorPoint> arrList = new ArrayList<ColorPoint>();
	
		for (ColorPoint c : points) {
			arrList.add(new ColorPoint(c.getColor(),c.getPoint(),c.getSize()));
		}
		stack.push(arrList);
	}
	
	public void undo() {
		try {
			// most recently backed up ArrayList gets repainted
			points = new ArrayList<ColorPoint>(stack.pop());
			repaint();
		} catch (EmptyStackException e) {
			// do nothing if stack is empty
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent m) {
		Point p = new Point(m.getX(), m.getY());
		ColorPoint c = new ColorPoint(currentColor, p, penSize);
		
		if(clicked) {
			points.add(c);
		}
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		clicked = !clicked;
	}
	
	public static void main(String[] args) {
		Painter paint = new Painter();
		JFrame frame = new JFrame("Painter");
		JPanel buttons = new JPanel();
		ButtonLayout red = new ButtonLayout(paint, Color.red, "Red");
		ButtonLayout blue = new ButtonLayout(paint, Color.blue, "Blue");
		ButtonLayout green = new ButtonLayout(paint, Color.green, "Green");
		ButtonLayout erase = new ButtonLayout(paint, Color.white, "Eraser");
		ClearButton delete = new ClearButton(paint);
		UndoButton undoButton = new UndoButton(paint);
		PenSlider slider = new PenSlider(paint);
		
		buttons.add(red, BorderLayout.CENTER);
		buttons.add(blue, BorderLayout.CENTER);
		buttons.add(green, BorderLayout.CENTER);
		buttons.add(erase, BorderLayout.CENTER);
		buttons.add(delete, BorderLayout.CENTER);
		buttons.add(undoButton, BorderLayout.CENTER);
		
		frame.addMouseListener(paint);
		frame.addMouseMotionListener(paint);
		frame.getContentPane().add(paint,BorderLayout.CENTER);
		frame.getContentPane().add(buttons,BorderLayout.SOUTH);
		frame.getContentPane().add(slider,BorderLayout.EAST);
		frame.setSize(FRAME_SIZE,FRAME_SIZE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// stuff that has to be overriden
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent m) {}
}