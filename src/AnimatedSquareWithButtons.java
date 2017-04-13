
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AnimatedSquareWithButtons extends JPanel implements Runnable, ActionListener
{
	double t = 0;
	boolean isPaused = false;

	public static void main(String[] args) {
		AnimatedSquareWithButtons sq = new AnimatedSquareWithButtons();
		sq.setPreferredSize(new Dimension(400, 400));
		
		JButton button = new JButton("Pause");
		button.addActionListener(sq);
		
		JFrame frame = new JFrame();
		
		// The default layout for a JFrame is a BorderLayout
		// I'm using the BorderLayout to place the button below the JPanel
		// More information about BorderLayout:
		// https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		frame.add(sq, BorderLayout.CENTER);
		frame.add(button, BorderLayout.PAGE_END);
		
		frame.pack();
		frame.setVisible(true);
		
		Thread thread = new Thread(sq);
		thread.run();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int w = this.getWidth();
		int h = this.getHeight();
		
		g.drawLine( (int) (t * w), 0, w, (int) (t * h) );
		g.drawLine( w, (int) (t * h), (int) ( (1-t) * w), h );
		g.drawLine( (int) ( (1-t) * w), h, 0, (int) ( (1-t) * h) );
		g.drawLine( 0, (int) ( (1-t) * h), (int) (t * w), 0 );
	}

	@Override
	public void run() {
		while(true)
		{
			if(!isPaused)
			{
				t += 0.01;
				if(t > 1)
					t -= 1;
				repaint();
			}
			try { 
				Thread.sleep(10);
			}
			catch (Exception e) { /* ignore exception */ }
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.isPaused = !this.isPaused;
		if(e.getSource() instanceof JButton)
		{
			JButton button = (JButton) e.getSource();
			button.setText( this.isPaused ? "Resume" : "Pause" );
		}
	}
}
