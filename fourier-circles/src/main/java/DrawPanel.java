
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 6257697009562039501L;
	private static final int TIMER_DELAY_MS = 16;
	private static final double BASE_RADIUS = 120.0;
	private static final int MIN_CIRCLES = 1;
	private static final int MAX_TRACE_POINTS = 20000;
	private static final double TIME_STEP = 0.03;

	private int circleCount = 4;
	private double radiusRatio = 0.60;
	private double rotationSpeed = 0.35;
	private double speedRatio = 1.2;

	private double time = 0.0;
	private int sampleCount = 1;

	private final List<Point2D.Double> tracePoints = new ArrayList<>();
	private final Timer timer;

	public DrawPanel() {
		this.setPreferredSize(new Dimension(1000, 700));
		this.setBackground(Color.WHITE);

		this.timer = new Timer(TIMER_DELAY_MS, e -> {
			final double previousTime = this.time;
			final double deltaTime = TIME_STEP * this.rotationSpeed;

			this.time += deltaTime;

			final int samples = Math.max(1, this.sampleCount);

			for (int i = 1; i <= samples; i++) {
				final double t = previousTime + (deltaTime * i / samples);
				final Point2D.Double tip = this.computeLastCenterAtTime(t);
				this.tracePoints.add(tip);
			}

			this.trimTrace();
			this.repaint();
		});
		this.timer.start();
	}

	public void addCircle() {
		this.circleCount++;
		this.repaint();
	}

	public void removeLastCircle() {
		if (this.circleCount > MIN_CIRCLES) {
			this.circleCount--;
			this.repaint();
		}
	}

	public void setRadiusRatio(final double radiusRatio) {
		this.radiusRatio = radiusRatio;
		this.repaint();
	}

	public void setRotationSpeed(final double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void setSpeedRatio(final double speedRatio) {
		this.speedRatio = speedRatio;
	}

	public void setSampleCount(final int sampleCount) {
		this.sampleCount = Math.max(1, sampleCount);
	}

	public List<Point2D.Double> getTracePoints() {
		return this.tracePoints;
	}

	private void trimTrace() {
		while (this.tracePoints.size() > MAX_TRACE_POINTS) {
			this.tracePoints.remove(0);
		}
	}

	private Point2D.Double computeLastCenterAtTime(final double currentTime) {
		final double centerX = this.getWidth() / 2.0;
		final double centerY = this.getHeight() / 2.0;

		double parentX = centerX;
		double parentY = centerY;
		double parentRadius = BASE_RADIUS;

		double currentSpeed = 1.0;

		for (int i = 0; i < this.circleCount; i++) {
			final double currentRadius = parentRadius * this.radiusRatio;

			final double angle = currentTime * currentSpeed;

			final double currentX = parentX + Math.cos(angle) * (parentRadius + currentRadius);
			final double currentY = parentY + Math.sin(angle) * (parentRadius + currentRadius);

			parentX = currentX;
			parentY = currentY;
			parentRadius = currentRadius;

			currentSpeed *= this.speedRatio;
		}

		return new Point2D.Double(parentX, parentY);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final double rootX = this.getWidth() / 2.0;
		final double rootY = this.getHeight() / 2.0;

		double parentX = rootX;
		double parentY = rootY;
		double parentRadius = BASE_RADIUS;

		g2.setColor(new Color(180, 180, 180));
		this.drawCircle(g2, parentX, parentY, parentRadius);

		double currentSpeed = 1.0;
		Point2D.Double lastCenter = null;

		for (int i = 0; i < this.circleCount; i++) {
			final double currentRadius = parentRadius * this.radiusRatio;

			final double angle = this.time * currentSpeed;

			final double currentX = parentX + Math.cos(angle) * (parentRadius + currentRadius);
			final double currentY = parentY + Math.sin(angle) * (parentRadius + currentRadius);

			g2.setColor(new Color(120, 120, 120));
			g2.setStroke(new BasicStroke(1.5f));
			g2.drawLine((int) Math.round(parentX), (int) Math.round(parentY), (int) Math.round(currentX), (int) Math.round(currentY));

			g2.setColor(new Color(80, 80, 80));
			this.drawCircle(g2, currentX, currentY, currentRadius);

			g2.setColor(Color.RED);
			g2.fill(new Ellipse2D.Double(currentX - 3, currentY - 3, 6, 6));

			parentX = currentX;
			parentY = currentY;
			parentRadius = currentRadius;

			currentSpeed *= this.speedRatio;
			lastCenter = new Point2D.Double(currentX, currentY);
		}

		g2.setColor(Color.BLUE);
		for (final Point2D.Double p : this.tracePoints) {
			g2.fillRect((int) Math.round(p.x), (int) Math.round(p.y), 2, 2);
		}

		if (lastCenter != null) {
			g2.setColor(Color.BLUE);
			g2.fill(new Ellipse2D.Double(lastCenter.x - 4, lastCenter.y - 4, 8, 8));
		}

		g2.dispose();
	}

	private void drawCircle(final Graphics2D g2, final double centerX, final double centerY, final double radius) {
		g2.draw(new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2));
	}
}