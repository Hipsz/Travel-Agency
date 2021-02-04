package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KartaInformacyjna;
import zadanie.projektowe.Wycieczka;
import zadanie.projektowe.WycieczkaKrajowa;
import zadanie.projektowe.WycieczkaZagraniczna;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodawanieKarty {

	private JFrame frame;
	private JTextField fieldInformacje;
	private JTextField fieldOgraniczenia;
	private JTextField fieldUwagi;
	private KartaInformacyjna Ki;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawanieKarty window = new DodawanieKarty();
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
	public DodawanieKarty() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 644, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);
			// wyjatek co gdy plik jest pusty
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		
		JLabel lblNewLabel = new JLabel("Wybierz wycieczk\u0119");
		lblNewLabel.setBounds(21, 21, 182, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Informacje:");
		lblNewLabel_1.setBounds(21, 93, 182, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ograniczenia:");
		lblNewLabel_2.setBounds(21, 193, 182, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Uwagi:");
		lblNewLabel_3.setBounds(21, 303, 92, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		fieldInformacje = new JTextField();
		fieldInformacje.setBounds(278, 90, 319, 82);
		frame.getContentPane().add(fieldInformacje);
		fieldInformacje.setColumns(10);
		
		fieldOgraniczenia = new JTextField();
		fieldOgraniczenia.setBounds(278, 193, 319, 87);
		frame.getContentPane().add(fieldOgraniczenia);
		fieldOgraniczenia.setColumns(10);
		
		fieldUwagi = new JTextField();
		fieldUwagi.setBounds(278, 296, 319, 95);
		frame.getContentPane().add(fieldUwagi);
		fieldUwagi.setColumns(10);
		
		JComboBox <Wycieczka> comboWycieczka = new JComboBox<>();
		comboWycieczka.setBounds(278, 18, 319, 32);
		frame.getContentPane().add(comboWycieczka);
		try {
			for (WycieczkaZagraniczna element : Ekstensja.getEkstensja(WycieczkaZagraniczna.class)) {

				comboWycieczka.addItem(element);

			}
		} catch (NullPointerException e) {

		}
		
		try {
			for (WycieczkaKrajowa element : Ekstensja.getEkstensja(WycieczkaKrajowa.class)) {
				comboWycieczka.addItem(element);
			}
		} catch (NullPointerException e) {

		}
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(456, 412, 141, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wstecz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(21, 412, 141, 35);
		frame.getContentPane().add(btnNewButton_1);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
}
