package gui;

import java.awt.EventQueue;
import java.awt.Window;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Motyw;
import zadanie.projektowe.Ocena;
import zadanie.projektowe.WycieczkaKrajowa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodawanieKrajowej {

	private JFrame frame;
	private JTextField fieldCena;
	private JTextField fieldMiasto;
	private JTextField fieldID;
	private WycieczkaKrajowa wk;
	private JTextField fieldNazwa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawanieKrajowej window = new DodawanieKrajowej();
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
	public DodawanieKrajowej() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 397, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		// wczytanie ekstensji z pliku
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);
			// wyjatek co gdy plik jest pusty
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(21, 38, 92, 26);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cena:");
		lblNewLabel_1.setBounds(21, 132, 92, 26);
		frame.getContentPane().add(lblNewLabel_1);

		JComboBox<Motyw> comboMotyw = new JComboBox<>();
		comboMotyw.setBounds(122, 176, 239, 32);
		frame.getContentPane().add(comboMotyw);

		for (Motyw element : Motyw.values()) {
			comboMotyw.addItem(element);
		}

		JLabel lblNewLabel_2 = new JLabel("Motyw:");
		lblNewLabel_2.setBounds(21, 179, 92, 26);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ocena:");
		lblNewLabel_3.setBounds(21, 232, 92, 26);
		frame.getContentPane().add(lblNewLabel_3);

		JComboBox<Ocena> comboOcena = new JComboBox<>();
		comboOcena.setBounds(121, 229, 240, 32);
		frame.getContentPane().add(comboOcena);
		for (Ocena element : Ocena.values()) {
			comboOcena.addItem(element);
		}
		JLabel lblNewLabel_5 = new JLabel("Nazwa:");
		lblNewLabel_5.setBounds(21, 85, 92, 26);
		frame.getContentPane().add(lblNewLabel_5);
		
		fieldNazwa = new JTextField();
		fieldNazwa.setBounds(122, 82, 235, 32);
		frame.getContentPane().add(fieldNazwa);
		fieldNazwa.setColumns(10);

		fieldCena = new JTextField();
		fieldCena.setBounds(122, 129, 239, 32);
		frame.getContentPane().add(fieldCena);
		fieldCena.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Miasto:");
		lblNewLabel_4.setBounds(21, 285, 92, 26);
		frame.getContentPane().add(lblNewLabel_4);

		fieldMiasto = new JTextField();
		fieldMiasto.setBounds(122, 282, 239, 32);
		frame.getContentPane().add(fieldMiasto);
		fieldMiasto.setColumns(10);

		fieldID = new JTextField();
		try {
			fieldID.setText(Ekstensja.getEkstensja(WycieczkaKrajowa.class).size() + 1 + "");
		} catch (NullPointerException e) {
			fieldID.setText(1 + "");
		}
		fieldID.setBounds(122, 35, 44, 32);
		frame.getContentPane().add(fieldID);
		fieldID.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fieldCena.getText().isEmpty() || fieldMiasto.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "zostawiles puste pole");
					
					return;
				}
					//Zrobiæ matchery!!
					
					if (fieldCena.getText().isEmpty() || fieldMiasto.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Zostawi³es puste pole");
						return;
					}
					
					Pattern patterMiasto = Pattern.compile("[A-Z][a-z]{2,}");
					if(!patterMiasto.matcher(fieldMiasto.getText()).matches()) {
						JOptionPane.showMessageDialog(null, "Zle wpisane Miasto");
						return;
					}
					
					Pattern patternCena = Pattern.compile("\\d+\\.\\d+");
					
					if(!patternCena.matcher(fieldCena.getText()).matches()){
						JOptionPane.showMessageDialog(null, "z³y format Ceny");
						return;
					}
					//String nazwa, Ocena ocena, BigDecimal cena, Motyw motyw, String miasto Dokonczyc dodawanie wycieczek
					try {
						wk = new WycieczkaKrajowa(fieldNazwa.getText(), (Ocena)comboOcena.getSelectedItem(), new BigDecimal(fieldCena.getText()),(Motyw)comboMotyw.getSelectedItem(), fieldMiasto.getText()); 
					} catch (Exception e1) 	{		
						e1.printStackTrace();
					}
					
				
			try {
				// zapis do ekstensji
 				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
				Ekstensja.save(oos);
				oos.close();
			} catch (Exception e1) {
				
			}
			JOptionPane.showMessageDialog(null, "Dodano wycieczkê krajow¹");
			frame.setVisible(false);
			DodawanieWycieczek dodawanie = new DodawanieWycieczek();
			dodawanie.getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(220, 335, 141, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wstecz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DodawanieWycieczek dodawanie = new DodawanieWycieczek();
				frame.setVisible(false);
				dodawanie.getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(21, 335, 141, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		

	}

	public Window getFrame() {
		return frame;
	}
}
