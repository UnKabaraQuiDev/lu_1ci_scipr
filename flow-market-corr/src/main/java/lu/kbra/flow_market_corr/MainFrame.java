package lu.kbra.flow_market_corr;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import lu.kbra.flow_market_corr.db.data.BillData;
import lu.kbra.flow_market_corr.db.data.CashierData;
import lu.kbra.flow_market_corr.db.data.ItemData;
import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.CashierTable;
import lu.kbra.flow_market_corr.db.table.ItemTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

public class MainFrame extends JFrame {

	protected DataBase database;
	protected VendorTable vendors;
	protected BillTable bills;
	protected ItemTable items;
	protected CashierTable cashiers;

	protected CashierData currentCashier;
	protected BillData currentTransaction;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField vendorCode;

	public MainFrame() throws UnknownHostException {
		database = new DataBase(new DataBaseConnector("user", "pass", "localhost", 3306), "scipr_flow_market_corr");
		database.create().run();

		vendors = new VendorTable(database);
		bills = new BillTable(database);
		items = new ItemTable(database);
		cashiers = new CashierTable(database);

		vendors.create().run();
		cashiers.create().run();
		bills.create().run();
		items.create().run();

		currentCashier = cashiers.loadIfExistsElseInsert(new CashierData(InetAddress.getLocalHost().getHostAddress())).run();

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
		btnSubmitAdd.addActionListener(a -> {
			items.insertAndReload(new ItemData(currentTransaction.getId(), vendorCode.getText(), (int) price.getValue())).thenConsume(e -> {
				vendorCode.setText("");
				price.setValue(0);
			}).run();
		});

		JButton btnSubmitSave = new JButton("Submit & Save");
		btnSubmitSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentTransaction == null) {
					System.err.println("No open transaction.");
					return;
				}

				if (!vendorCode.getText().isBlank()) {
					items
							.insertAndReload(new ItemData(currentTransaction.getId(), vendorCode.getText(), (int) price.getValue()))
							.thenConsume(e -> {
								vendorCode.setText("");
								price.setValue(0);
							})
							.run();
				}

				currentTransaction.setTimestamp(Timestamp.from(Instant.now()));
				bills.updateAndReload(currentTransaction).thenConsume(c -> currentTransaction = null).run();
			}
		});

		JButton btnNewBill = new JButton("New bill");
		btnNewBill.addActionListener(a -> {
			if (currentTransaction == null) {
				currentTransaction = bills.insertAndReload(new BillData(currentCashier.getId())).run();
			} else {
				System.err.println("close current transaction first");
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel
				.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel
								.createSequentialGroup()
								.addContainerGap(102, Short.MAX_VALUE)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addGap(12).addComponent(btnNewBill))
										.addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel
														.createSequentialGroup()
														.addComponent(btnSubmitAdd)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
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
																.addComponent(price,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(vendorCode)))))
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
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewBill)
								.addContainerGap(80, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
