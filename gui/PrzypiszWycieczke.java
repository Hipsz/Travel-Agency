package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Wycieczka;
import zadanie.projektowe.WycieczkaKrajowa;
import zadanie.projektowe.WycieczkaZagraniczna;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PrzypiszWycieczke {
	private DefaultListModel <KlientZarejestrowany> klienci = new DefaultListModel<>();
	private DefaultListModel <Wycieczka> wycieczki = new DefaultListModel<>();
	private KlientZarejestrowany klientZarejestrowany;
	private Wycieczka wycieczka;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrzypiszWycieczke window = new PrzypiszWycieczke();
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
	public PrzypiszWycieczke() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 965, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);		
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		for(KlientZarejestrowany k : Ekstensja.getEkstensja(KlientZarejestrowany.class)) {
			klienci.addElement(k);
		}

		JList<KlientZarejestrowany> klienci1  = new JList<>(klienci);
		klienci1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(klienci1);
		scroll.setBounds(31, 39, 420, 374);
		frame.getContentPane().add(scroll);
		
		
		try {
			for (Wycieczka element : Ekstensja.getEkstensja(WycieczkaZagraniczna.class)) {
				wycieczki.addElement(element);
			}
			for (Wycieczka element : Ekstensja.getEkstensja(WycieczkaKrajowa.class)) {
				wycieczki.addElement(element);
			}
		} catch (NullPointerException e) {

		}

		JList <Wycieczka> wycieczki1 = new JList(wycieczki);
		JScrollPane scroll2 = new JScrollPane(wycieczki1);
		scroll2.setBounds(462, 39, 456, 374);
		frame.getContentPane().add(scroll2);
		
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Klienci");
		lblNewLabel.setBounds(31, 10, 92, 26);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Wycieczki");
		lblNewLabel_1.setBounds(462, 10, 92, 26);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Wstecz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);

			}
		});
		btnNewButton.setBounds(21, 454, 141, 35);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Przypisz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (klienci1.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "Nie zaznaczyles klienta");
					return;
				}
				if (wycieczki1.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, "nie zaznaczyles wycieczki");
					return;
				}
				
				
				//List <Wycieczka> zanzaczeni = new ArrayList<>();
				
				
				
	
//				wycieczki1.getSelectedValuesList();
//				klienci1.getSelectedValue();
				
				for( Wycieczka element : wycieczki1.getSelectedValuesList()) {
					klienci1.getSelectedValue().dodajWycieczke(element);
				}
				
				
				JOptionPane.showMessageDialog(null, "Przypisa³eœ wycieczkê do klienta");
				
				try {
					// zapis do ekstensji
	 				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
					Ekstensja.save(oos);
					oos.close();
				} catch (Exception e1) {

				}
				

			}
		});
		btnNewButton_1.setBounds(777, 454, 141, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		
	}
	
	//dokonczyc
	//wyswietalnie wyceiczek klienta
	
	public Window getFrame() {
		return frame;
	}
}
