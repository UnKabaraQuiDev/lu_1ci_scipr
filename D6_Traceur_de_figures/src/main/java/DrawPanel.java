import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	private MainFrame mainFrame;

	private Shapes shapes;

	private int pressedBtn;
	private CustomShape currentShape;
	private Color color = Color.BLACK;

	public DrawPanel(MainFrame mainFrame) {
		this(new Shapes(), mainFrame);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public DrawPanel(Shapes lines, MainFrame mainFrame) {
		this.shapes = lines;
		this.mainFrame = mainFrame;

		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		shapes.draw((Graphics2D) g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawPanelMouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		drawPanelMousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawPanelMouseReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void drawPanelMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			currentShape = new Line(color, e.getPoint(), e.getPoint());
			shapes.add(currentShape);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			currentShape = new Rectangle(color, e.getPoint(), e.getPoint());
			shapes.add(currentShape);
		}

		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseReleased(MouseEvent e) {
		currentShape.setEndpoint(e.getPoint());

		this.repaint();
		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseDragged(MouseEvent e) {
		currentShape.setEndpoint(e.getPoint());

		this.repaint();
		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
			final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
			if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			final File file = fileChooser.getSelectedFile();
			try {
				if (file.exists()) {
					file.delete();
				}
				file.getParentFile().mkdirs();
				file.createNewFile();
				Files.write(Paths.get(file.getPath()), shapes.asJSON().getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()) {
			final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
			if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			final File file = fileChooser.getSelectedFile();
			try {
				final String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
				shapes.fromJSON(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if (e.getKeyCode() == KeyEvent.VK_I && e.isControlDown()) {
			final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
			if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
				return;
			}

			final File file = fileChooser.getSelectedFile();
			try {
				final String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
				shapes.appendJSON(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		mainFrame.getList().setListData(shapes.getShapes().toArray());
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public Shapes getShapes() {
		return shapes;
	}

	public void setShapes(Shapes lines) {
		this.shapes = lines;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
