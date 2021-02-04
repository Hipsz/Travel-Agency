package gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.Wycieczka;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class WycieczkiKlienta {

	private JFrame frame;
	private DefaultListModel <Wycieczka> wycieczki = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WycieczkiKlienta window = new WycieczkiKlienta();
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
	public WycieczkiKlienta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 393);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);		
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		
		for (Wycieczka element : WyswietlanieKlientow.wybrany.getWycieczki()){
			wycieczki.addElement(element);
		}
		
		JList <Wycieczka> wycieczkiKlienta = new JList<>(wycieczki);
		JScrollPane scroll = new JScrollPane(wycieczkiKlienta);
		scroll.setBounds(21, 21, 480, 238);
		frame.getContentPane().add(scroll);
		
		JButton btnNewButton = new JButton("Wstecz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WyswietlanieKlientow wyswietlanieKlientow = new WyswietlanieKlientow();
				frame.setVisible(false);
				wyswietlanieKlientow.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(21, 279, 141, 35);
		frame.getContentPane().add(btnNewButton);
	}
	
	public Window getFrame() {
		return frame;
	}
}
