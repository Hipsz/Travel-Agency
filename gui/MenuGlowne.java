package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MenuGlowne {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGlowne window = new MenuGlowne();
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
	public MenuGlowne() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Biuro podr\u00F3\u017Cy");
		frame.setBounds(100, 100, 269, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Dodaj klienta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DodawanieKlienta dodanie = new DodawanieKlienta();
				frame.setVisible(false);
				dodanie.getFrame().setVisible(true);
			
			}
		});
		btnNewButton.setBounds(21, 21, 219, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wy\u015Bwietl klient\u00F3w");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WyswietlanieKlientow wyswietlanie = new WyswietlanieKlientow();
				frame.setVisible(false);
				wyswietlanie.getFrame().setVisible(true);;
			}
		});
		btnNewButton_1.setBounds(21, 66, 219, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Dodaj przewodnika");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DodawaniePrzewodnika dodawaniePrzewodnika = new DodawaniePrzewodnika();
				frame.setVisible(false);
				dodawaniePrzewodnika.getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(21, 110, 219, 35);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Dodaj wycieczk\u0119");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DodawanieWycieczek dodawanieWycieczek = new DodawanieWycieczek();
				frame.setVisible(false);
				dodawanieWycieczek.getFrame().setVisible(true);

			}
		});
		btnNewButton_3.setBounds(21, 154, 219, 35);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Dodaj kart\u0119 inf.");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DodawanieKarty dodawanieKarty = new DodawanieKarty();
				frame.setVisible(false);
				dodawanieKarty.getFrame().setVisible(true);
			}
		});
		btnNewButton_4.setBounds(21, 287, 219, 35);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Wy\u015Bwietl wycieczki");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlanieWycieczek wyswietlanie = new WyswietlanieWycieczek();
				frame.setVisible(false);
				wyswietlanie.getFrame().setVisible(true);
			}
		});
		btnNewButton_5.setBounds(21, 198, 219, 35);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Przypisz wycieczk\u0119");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrzypiszWycieczke przypisz = new PrzypiszWycieczke();
				frame.setVisible(false);
				przypisz.getFrame().setVisible(true);
	
			}
		});
		btnNewButton_6.setBounds(21, 242, 219, 35);
		frame.getContentPane().add(btnNewButton_6);
	}

	public Window getFrame() {
		return frame;
	}
}
