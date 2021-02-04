package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextField;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Wycieczka;
import zadanie.projektowe.WycieczkaKrajowa;
import zadanie.projektowe.WycieczkaZagraniczna;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WyswietlanieWycieczek {

	private JFrame frame;
	private DefaultListModel<Wycieczka> wycieczka = new DefaultListModel<>();
	public static Wycieczka wybrana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WyswietlanieWycieczek window = new WyswietlanieWycieczek();
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
	public WyswietlanieWycieczek() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 421, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);
			// wyjatek co gdy plik jest pusty
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}

		JButton btnNewButton = new JButton("Wstecz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(87, 530, 249, 35);
		frame.getContentPane().add(btnNewButton);
		try {
			for (Wycieczka element : Ekstensja.getEkstensja(WycieczkaZagraniczna.class)) {
				wycieczka.addElement(element);
			}
			for (Wycieczka element : Ekstensja.getEkstensja(WycieczkaKrajowa.class)) {
				wycieczka.addElement(element);
			}
		} catch (NullPointerException e) {

		}
		

		JList<Wycieczka> list = new JList<>(wycieczka);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(21, 21, 370, 312);
		frame.getContentPane().add(scroll);

		JButton btnNewButton_1 = new JButton("Znajdz Najdro\u017Csz\u0105");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "nie zanzaczyles wycieczki");
					return;
				}
				JOptionPane.showMessageDialog(null,
						Wycieczka.najdorzszaWycieczka(Ekstensja.getEkstensja(Wycieczka.class)));
			}
		});
		btnNewButton_1.setBounds(87, 354, 249, 35);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Najpopularniejszy kraj");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, WycieczkaZagraniczna.najpopularniejszyKraj(Ekstensja.getEkstensja(WycieczkaZagraniczna.class)));
			}
		});
		btnNewButton_2.setBounds(87, 398, 249, 35);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Usu\u0144 wycieczk\u0119");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Wycieczka w = list.getSelectedValue();
	
				
				if (w == null) {
					JOptionPane.showMessageDialog(null, "Nie zaznaczy³eœ wycieczki");
					return;
				}
				int wybor = JOptionPane.showConfirmDialog(null, "Na pewno chcesz usun¹c?");
				if (wybor == 0) {

					if (w.getClass().getSimpleName().equals("WycieczkaZagraniczna")) {
						Ekstensja.getEkstensja(WycieczkaZagraniczna.class).remove(list.getSelectedValue());
					} else {
						Ekstensja.getEkstensja(WycieczkaKrajowa.class).remove(list.getSelectedValue());
					}

				} else {
					JOptionPane.showMessageDialog(null, "Zrezygnowa³eœ z usuwania");
				}
				try {
					// zapis do ekstensji
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
					Ekstensja.save(oos);
					oos.close();
				} catch (Exception e1) {

				}

				JOptionPane.showMessageDialog(null, "Usuniêto wycieczkê " + list.getSelectedValue());

				frame.setVisible(false);
				WyswietlanieWycieczek wyswietlanie = new WyswietlanieWycieczek();
				wyswietlanie.getFrame().setVisible(true);

			}
		});
		btnNewButton_3.setBounds(87, 442, 249, 35);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Poka\u017C klient\u00F3w");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "nie zanzaczyles wycieczki");
					return;
				}
				
				
				wybrana = list.getSelectedValue();
				//System.out.println(wybrana);
				
				PrzypisaniKlienci przypisanie = new PrzypisaniKlienci();
				
				frame.setVisible(false);
				przypisanie.getFrame().setVisible(true);
			}
		});
		btnNewButton_4.setBounds(87, 487, 249, 35);
		frame.getContentPane().add(btnNewButton_4);
		
		
	}

	public Window getFrame() {
		return frame;
	}
}
