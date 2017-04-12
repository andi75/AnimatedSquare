import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimatedSquare extends JPanel implements Runnable {

	double t = 0;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AnimatedSquare sq = new AnimatedSquare();
		sq.setPreferredSize(new Dimension(400, 400));

		frame.add(sq);
		frame.pack();
		frame.setVisible(true);
		
		Thread thread = new Thread(sq);
		thread.run();
	}
	
	@Override
	public void paint (Graphics g)
	{
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
			t += 0.01;
			if(t > 1)
				t -= 1;
//			System.out.println(t);
			repaint();
			try { 
				Thread.sleep(10);
			}
			catch (Exception e) { /* ignore exception */ }
		}
	}
}
