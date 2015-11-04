
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClearButton extends JPanel {
	
	private static final int DEFAULT_WIDTH = 75;
	private static final int DEFAULT_HEIGHT = 50;
	
	private Painter p;
	private JButton button;
	
	public ClearButton(Painter p) {
		this.p = p;
		setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		
		button = new JButton("Clear All");
		add(button);
		button.addActionListener(new ButtonHandler());
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			p.clear();
		}
	}
}