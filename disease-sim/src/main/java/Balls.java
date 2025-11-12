
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Balls {

	private static final float SPEED = 1.5f;

	private ArrayList<Ball> balls = new ArrayList<Ball>();

	private JPanel panel;

	public Balls(JPanel panel) {
		this.panel = panel;
	}

	public void draw(Graphics2D g2d) {
		balls.forEach(b -> b.draw(g2d));
	}

	public void add(Ball ball) {
		balls.add(ball);
	}

	public Ball getSelectedBall(int x, int y) {
		return balls.parallelStream().filter(b -> b.isInside(x, y)).findFirst().orElse(null);
	}

	public void doPhysics() {
		for (int i = 0; i < balls.size(); i++) {
			final Ball a = balls.get(i);
			for (int j = i + 1; j < balls.size(); j++) {
				final Ball b = balls.get(j);

				a.bounce(b);
			}
		}

		balls.forEach(b -> b.doPhysics(SPEED, panel.getWidth(), panel.getHeight()));
	}

	public int size() {
		return balls.size();
	}

	public void recap() {
		System.out.println(" ======= ");
		System.out.println(" Sick: " + balls.stream().filter(p -> p.getState() == DiseaseState.SICK).count());
		System.out.println(" Dead: " + balls.stream().filter(p -> p.getState() == DiseaseState.DEAD).count());
		System.out.println(" ======= ");
	}

}