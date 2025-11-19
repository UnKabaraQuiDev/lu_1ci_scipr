import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DrawPanel drawPanel;

	private JList list;

	public MainFrame() throws JsonProcessingException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(new BorderLayout());

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());

		list = new JList();

		sidePanel.add(list);

		JButton colorBtn = new JButton("Change color");

		colorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorDialog();
			}
		});

		sidePanel.add(colorBtn, BorderLayout.SOUTH);

		drawPanel = new DrawPanel(this);
		getContentPane().add(drawPanel, BorderLayout.CENTER);

		getContentPane().add(sidePanel, BorderLayout.EAST);

		setVisible(true);
	}

	protected void colorDialog() {
		drawPanel.setColor(JColorChooser.showDialog(this, "Choix d'une couleur", drawPanel.getColor()));
		drawPanel.requestFocusInWindow();
	}

	public JList getList() {
		return list;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
