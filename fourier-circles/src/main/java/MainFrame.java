import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	private final DrawPanel drawPanel;
	private final JLabel ratioValueLabel;
	private final JLabel speedValueLabel;
	private final JLabel speedRatioValueLabel;

	public MainFrame() {
		super("Fourier Circles");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1100, 800);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		drawPanel = new DrawPanel();
		add(drawPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
		controlPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		JButton addButton = new JButton("Add Circle");
		JButton removeButton = new JButton("Remove Last");
		JButton clearButton = new JButton("Clear");

		clearButton.addActionListener(e -> drawPanel.getTracePoints().clear());

		// Samples
		JSlider sampleSlider = new JSlider(1, 50, 1);
		sampleSlider.setMajorTickSpacing(10);
		sampleSlider.setMinorTickSpacing(1);
		sampleSlider.setPaintTicks(true);

		JLabel sampleValueLabel = new JLabel("Samples: 1");

		sampleSlider.addChangeListener(e -> {
			int samples = sampleSlider.getValue();
			drawPanel.setSampleCount(samples);
			sampleValueLabel.setText("Samples: " + samples);
		});

		// Radius ratio
		JSlider ratioSlider = new JSlider(10, 95, 60);
		ratioSlider.setMajorTickSpacing(17);
		ratioSlider.setMinorTickSpacing(5);
		ratioSlider.setPaintTicks(true);

		ratioValueLabel = new JLabel(String.format("Ratio: %.2f", ratioSlider.getValue() / 100.0));

		ratioSlider.addChangeListener(e -> {
			double ratio = ratioSlider.getValue() / 100.0;
			drawPanel.setRadiusRatio(ratio);
			ratioValueLabel.setText(String.format("Ratio: %.2f", ratio));
		});

		// Global speed [-2, 2]
		JSlider speedSlider = new JSlider(-200, 200, 35);
		speedSlider.setMajorTickSpacing(50);
		speedSlider.setMinorTickSpacing(10);
		speedSlider.setPaintTicks(true);

		speedValueLabel = new JLabel(String.format("Speed: %.2f", speedSlider.getValue() / 100.0));

		speedSlider.addChangeListener(e -> {
			double speed = speedSlider.getValue() / 100.0;
			drawPanel.setRotationSpeed(speed);
			speedValueLabel.setText(String.format("Speed: %.2f", speed));
		});

		// Speed ratio between circles
		JSlider speedRatioSlider = new JSlider(50, 200, 120); // 0.5 to 2.0
		speedRatioSlider.setMajorTickSpacing(25);
		speedRatioSlider.setMinorTickSpacing(5);
		speedRatioSlider.setPaintTicks(true);

		speedRatioValueLabel = new JLabel(String.format("Speed Ratio: %.2f", speedRatioSlider.getValue() / 100.0));

		speedRatioSlider.addChangeListener(e -> {
			double ratio = speedRatioSlider.getValue() / 100.0;
			drawPanel.setSpeedRatio(ratio);
			speedRatioValueLabel.setText(String.format("Speed Ratio: %.2f", ratio));
		});

		addButton.addActionListener(e -> drawPanel.addCircle());
		removeButton.addActionListener(e -> drawPanel.removeLastCircle());

		controlPanel.add(addButton);
		controlPanel.add(removeButton);
		controlPanel.add(clearButton);

		controlPanel.add(new JLabel("Circle Ratio"));
		controlPanel.add(ratioSlider);
		controlPanel.add(ratioValueLabel);

		controlPanel.add(new JLabel("Rotation Speed"));
		controlPanel.add(speedSlider);
		controlPanel.add(speedValueLabel);

		controlPanel.add(new JLabel("Speed Ratio"));
		controlPanel.add(speedRatioSlider);
		controlPanel.add(speedRatioValueLabel);

		controlPanel.add(sampleSlider);
		controlPanel.add(sampleValueLabel);

		add(controlPanel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
}