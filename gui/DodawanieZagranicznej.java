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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Motyw;
import zadanie.projektowe.Ocena;
import zadanie.projektowe.SrodekTransportu;
import zadanie.projektowe.Wycieczka;
import zadanie.projektowe.WycieczkaZagraniczna;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodawanieZagranicznej {

	private JFrame frame;
	private JTextField fieldID;
	private JTextField fieldNazwa;
	private JTextField fieldCena;
	private JTextField fieldKraj;
	private WycieczkaZagraniczna wz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawanieZagranicznej window = new DodawanieZagranicznej();
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
	public DodawanieZagranicznej() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 415, 471);
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

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(21, 21, 92, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwa");
		lblNewLabel_1.setBounds(21, 68, 92, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ocena");
		lblNewLabel_2.setBounds(21, 209, 92, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cena");
		lblNewLabel_3.setBounds(21, 115, 92, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Motyw");
		lblNewLabel_4.setBounds(21, 162, 92, 26);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Kraj");
		lblNewLabel_5.setBounds(21, 256, 92, 26);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u015Aodek transportu");
		lblNewLabel_6.setBounds(21, 315, 168, 26);
		frame.getContentPane().add(lblNewLabel_6);

		fieldID = new JTextField();
		try {
			fieldID.setText(Ekstensja.getEkstensja(WycieczkaZagraniczna.class).size() + 1 + "");
		} catch (NullPointerException e) {
			fieldID.setText(String.valueOf(1));
		}
		fieldID.setBounds(210, 18, 50, 32);
		frame.getContentPane().add(fieldID);
		fieldID.setColumns(10);
	
		
		fieldNazwa = new JTextField();
		fieldNazwa.setBounds(210, 65, 186, 32);
		frame.getContentPane().add(fieldNazwa);
		fieldNazwa.setColumns(10);
		
		fieldCena = new JTextField();
		fieldCena.setBounds(210, 112, 186, 32);
		frame.getContentPane().add(fieldCena);
		fieldCena.setColumns(10);
		
		fieldKraj = new JTextField();
		fieldKraj.setBounds(210, 253, 186, 32);
		frame.getContentPane().add(fieldKraj);
		fieldKraj.setColumns(10);
		
		JComboBox<Ocena> comboOcena = new JComboBox<>();
		comboOcena.setBounds(210, 206, 186, 32);
		frame.getContentPane().add(comboOcena);
		
		for(Ocena element:Ocena.values()) {
			comboOcena.addItem(element);			
		}		
		
		JComboBox<Motyw> comboMotyw = new JComboBox<>();
		comboMotyw.setBounds(210, 159, 186, 32);
		frame.getContentPane().add(comboMotyw);
		
		for(Motyw element : Motyw.values()) {
			comboMotyw.addItem(element);
		}
		
		JComboBox<SrodekTransportu> comboTransport = new JComboBox<>();
		comboTransport.setBounds(210, 312, 186, 32);
		frame.getContentPane().add(comboTransport);
		
		for(SrodekTransportu element : SrodekTransportu.values()) {
			comboTransport.addItem(element);
		}
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fieldNazwa.getText().isEmpty() || fieldCena.getText().isEmpty() || fieldKraj.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "zostawiles puste pole");
					return;
				}
				Pattern patternNazwa = Pattern.compile("[A-Z][a-z]{2,}");
				if(!patternNazwa.matcher(fieldNazwa.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "Zle wpisana Nazwa");
					return;
				}
				Pattern patternCena = Pattern.compile("\\d+\\.\\d+");
				
				if(!patternCena.matcher(fieldCena.getText()).matches()){
					JOptionPane.showMessageDialog(null, "z³y format Ceny");
					return;
				}
				Pattern patternKraj = Pattern.compile("[A-Z][a-z]+( ?[A-Z]?[a-z]*?)*");
				
				if (!patternKraj.matcher(fieldKraj.getText()).matches()) {
					JOptionPane.showMessageDialog(null, "z³y format kraju");
					return;
				}
				
				try {
					wz = new WycieczkaZagraniczna(fieldNazwa.getText(), (Ocena)comboOcena.getSelectedItem(), new BigDecimal(fieldCena.getText()),
							(Motyw)comboMotyw.getSelectedItem(), fieldKraj.getText(), (SrodekTransportu)comboTransport.getSelectedItem());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				
				
				
				try {
					// zapis do ekstensji
	 				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
					Ekstensja.save(oos);
					oos.close();
				} catch (Exception e1) {
				
				}
				JOptionPane.showMessageDialog(null, "Dodano wycieczkê zagraniczn¹");
				frame.setVisible(false);
				DodawanieWycieczek dodawanie = new DodawanieWycieczek();
				dodawanie.getFrame().setVisible(true);
			}
			
		});
		btnNewButton.setBounds(255, 365, 141, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wstecz");
		btnNewButton_1.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		DodawanieWycieczek dodawanie = new DodawanieWycieczek();
		frame.setVisible(false);
		dodawanie.getFrame().setVisible(true);
	}

	});btnNewButton_1.setBounds(21,365,141,35);frame.getContentPane().add(btnNewButton_1);

	}

	public Window getFrame() {
		return frame;
	}
}
