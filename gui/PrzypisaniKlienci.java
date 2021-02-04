package gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Wycieczka;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;


public class PrzypisaniKlienci {

	private DefaultListModel<KlientZarejestrowany> klienci = new DefaultListModel<>();
	private JFrame frame;
	private Wycieczka wycieczka = WyswietlanieWycieczek.wybrana;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrzypisaniKlienci window = new PrzypisaniKlienci();
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
	public PrzypisaniKlienci() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 438, 662);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);		
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		
		System.out.println(wycieczka);
		
		try {
			for(KlientZarejestrowany k : wycieczka.getKlienci()) {
				klienci.addElement(k);
			}
		}catch(NullPointerException e) {
			
		}	
		
		JList<KlientZarejestrowany> list = new JList(klienci);
	
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(21, 21, 390, 493);
		frame.getContentPane().add(scroll);
		
		JButton btnNewButton = new JButton("Wstecz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlanieWycieczek wyswietlanie = new WyswietlanieWycieczek();
				frame.setVisible(false);
				wyswietlanie.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(21, 555, 141, 35);
		frame.getContentPane().add(btnNewButton);
	}

	public Window getFrame() {
		return frame;
	}
}
