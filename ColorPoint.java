

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorPoint {
	
	private Color color;
	private Point point;
	private int size;
	
	public ColorPoint(Color c, Point p, int s) {
		color = c;
		point = p;
		size = s;
	}
	
	public ColorPoint(ColorPoint c) {
		this(c.getColor(), c.getPoint(), c.getSize());
	}

	public Color getColor() {
		return color;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void setPoint(Point p) {
		point = p;
	}
	
	public void setSize(int s) {
		size =s;
	}

}