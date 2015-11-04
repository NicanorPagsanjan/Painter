
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class PenSlider extends JSlider implements ChangeListener {
	private Painter p;
	
	public PenSlider(Painter p) {
		super(JSlider.VERTICAL,0,128, 4);
		this.p = p;
		
		setMajorTickSpacing(16);
		setMinorTickSpacing(4);
		setPaintTicks(true);
		setPaintLabels(true);
		setSnapToTicks(true);
		
		addChangeListener(this);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		p.setPenSize(getValue());
	} 
}

