import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AnimatedSquare extends JPanel implements Runnable, MouseListener
{
	double t = 0;
	boolean isPaused = false;

	public static void main(String[] args) {
		AnimatedSquare sq = new AnimatedSquare();
		sq.setPreferredSize(new Dimension(400, 400));
		sq.addMouseListener(sq);
		
		JFrame frame = new JFrame();
		frame.add(sq);
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
	public void mouseClicked(MouseEvent e) {
		this.isPaused = !this.isPaused;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}