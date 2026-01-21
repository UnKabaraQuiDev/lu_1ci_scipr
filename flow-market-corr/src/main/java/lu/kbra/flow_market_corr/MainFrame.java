package lu.kbra.flow_market_corr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField vendorCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		vendorCode = new JTextField();
		vendorCode.setColumns(10);

		JLabel lblNewLabel = new JLabel("Vendor Code");

		JLabel lblPrice = new JLabel("Price");

		JSpinner price = new JSpinner();

		JButton btnSubmitAdd = new JButton("Submit & Add");

		JButton btnSubmitSave = new JButton("Submit & Save");
		btnSubmitSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel
				.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel
								.createSequentialGroup()
								.addContainerGap(105, Short.MAX_VALUE)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel
												.createSequentialGroup()
												.addComponent(btnSubmitAdd)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnSubmitSave))
										.addGroup(gl_panel
												.createSequentialGroup()
												.addGroup(gl_panel
														.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(lblPrice))
												.addGap(51)
												.addGroup(gl_panel
														.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(price)
														.addComponent(vendorCode))))
								.addGap(94)));
		gl_panel
				.setVerticalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel
								.createSequentialGroup()
								.addGap(39)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(vendorCode,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addGap(18)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrice)
										.addComponent(price,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSubmitAdd)
										.addComponent(btnSubmitSave))
								.addContainerGap(113, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

	}
}
