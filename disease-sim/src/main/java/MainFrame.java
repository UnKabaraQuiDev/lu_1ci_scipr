
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.joml.Vector2f;

import lu.pcy113.pclib.PCUtils;

public class MainFrame extends javax.swing.JFrame {

	private Balls balls;

	public MainFrame() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		balls = new Balls(drawPanel1);
		drawPanel1.setBalls(balls);
		drawPanel1.setBackground(Color.WHITE);

		new Timer(1000 / 60, (e) -> updateView()).start();
		new Timer(1000, (e) -> {
			balls.recap();
		}).start();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		drawPanel1 = new DrawPanel();

		javax.swing.GroupLayout drawPanel1Layout = new javax.swing.GroupLayout(drawPanel1);
		drawPanel1.setLayout(drawPanel1Layout);
		drawPanel1Layout
				.setHorizontalGroup(drawPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								drawPanel1Layout.createSequentialGroup().addContainerGap(475, Short.MAX_VALUE).addGap(44, 44, 44)));
		drawPanel1Layout
				.setVerticalGroup(drawPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(drawPanel1Layout.createSequentialGroup().addGap(27, 27, 27).addContainerGap(366, Short.MAX_VALUE)));

		getContentPane().add(drawPanel1, java.awt.BorderLayout.CENTER);

		super.setMinimumSize(new Dimension(1200, 1000));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void updateView() {
		if (balls.size() < 100) {
			balls
					.add(new Ball(
							new Vector2f(PCUtils.randomIntRange(0, drawPanel1.getWidth()),
									PCUtils.randomIntRange(0, drawPanel1.getHeight())),
							new Vector2f((float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1).normalize(), 15));
		}
		balls.doPhysics();
		drawPanel1.repaint();
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For
		 * details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private DrawPanel drawPanel1;
	// End of variables declaration//GEN-END:variables
}