package gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodawanieWycieczek {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawanieWycieczek window = new DodawanieWycieczek();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodawanieWycieczek() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 245, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Wybierz typ wycieczki:");
		lblNewLabel.setBounds(21, 21, 218, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Krajowa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodawanieKrajowej dodawanieKrajowej = new DodawanieKrajowej();
				frame.setVisible(false);
				dodawanieKrajowej.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(31, 68, 185, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Zagraniczna");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodawanieZagranicznej dodawanieZagranicznej = new DodawanieZagranicznej();
				frame.setVisible(false);
				dodawanieZagranicznej.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(31, 129, 185, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Wstecz");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(31, 182, 185, 35);
		frame.getContentPane().add(btnNewButton_2);
	}
	public Window getFrame() {
		return frame;
	}
}
