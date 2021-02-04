package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class WyswietlanieKlientow {

	private JFrame frame;
	private DefaultListModel<KlientZarejestrowany> klienci = new DefaultListModel<>();
	public static KlientZarejestrowany wybrany;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WyswietlanieKlientow window = new WyswietlanieKlientow();
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
	public WyswietlanieKlientow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Klienci w bazie");
		frame.setBounds(100, 100, 314, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);		
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		
		for (KlientZarejestrowany k : Ekstensja.getEkstensja(KlientZarejestrowany.class)) {
			 klienci.addElement(k);
		}
		
		JList<KlientZarejestrowany> list = new JList<>(klienci);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(21, 21, 263, 187);
		frame.getContentPane().add(scroll);
			

		JButton buttonWstecz = new JButton("Wstecz");
		buttonWstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);

			}
		});
		buttonWstecz.setBounds(38, 407, 219, 35);
		frame.getContentPane().add(buttonWstecz);
		
		JButton buttonNajdrozszy = new JButton("Najdro\u017Cszy klient");
		buttonNajdrozszy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				JOptionPane.showMessageDialog(null, KlientZarejestrowany.najdrozszyKlient(Ekstensja.getEkstensja(KlientZarejestrowany.class)));
			}
		});
		
		buttonNajdrozszy.setBounds(38, 229, 219, 35);
		frame.getContentPane().add(buttonNajdrozszy);
		
		JButton buttonWydal = new JButton("Ile wyda\u0142 klient");
		buttonWydal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Nie zaznaczy³eœ klienta");
					return;
				}

				JOptionPane.showMessageDialog(null, ((KlientZarejestrowany) list.getSelectedValue()).ileWydal() + " z³");

			}
		});
		buttonWydal.setBounds(38, 273, 219, 35);
		frame.getContentPane().add(buttonWydal);
		
		JButton btnNewButton = new JButton("Usu\u0144 klienta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Nie zaznaczy³eœ klinta");
					return;
				}
				int wybor = JOptionPane.showConfirmDialog(null, "Na pewno chcesz usun¹c?");
				if (wybor == 0) {
					Ekstensja.getEkstensja(KlientZarejestrowany.class).remove(list.getSelectedValue());
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
				
				JOptionPane.showMessageDialog(null, "Usuniêto klienta " + list.getSelectedValue());
				
				frame.setVisible(false);
				WyswietlanieKlientow wyswietlanie = new WyswietlanieKlientow();
				wyswietlanie.getFrame().setVisible(true);

			}
		});
		btnNewButton.setBounds(38, 363, 219, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wycieczki klienta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Nie zaznaczy³es klienta");
					return;
				}

				wybrany = list.getSelectedValue();

				WycieczkiKlienta wycieczkiKlienta = new WycieczkiKlienta();
				frame.setVisible(false);
				wycieczkiKlienta.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(38, 319, 219, 35);
		frame.getContentPane().add(btnNewButton_1);
	}

	// przycisk wstecz zaprogramowac
	// wczytanie ekstensji
	// defaultlistmodel - wczytanie ekstensji, zrobienie od nowa

	public Window getFrame() {
		return frame;
	}
}
