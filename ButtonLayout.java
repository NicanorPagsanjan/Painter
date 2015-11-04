
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonLayout extends JPanel {
	
	private static final int DEFAULT_WIDTH = 75;
	private static final int DEFAULT_HEIGHT = 50;
	
	private Painter p;
	private JButton button;
	private Color c;
	
	public ButtonLayout(Painter p, Color c, String s) {
		this.p = p;
		this.c = c;
		
		setBackground(c);
		setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		
		button = new JButton(s);
		add(button);
		button.addActionListener(new ButtonHandler());
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			p.setColor(c);
		}
	}
}