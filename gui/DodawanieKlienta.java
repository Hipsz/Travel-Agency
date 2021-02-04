package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;

public class DodawanieKlienta {

	private JFrame frame;
	private JTextField fieldImie;
	private JTextField fieldNazwisko;
	private JTextField fieldTelefon;
	private JTextField fieldId;
	private JLabel lblNewLabel_4;
	private KlientZarejestrowany k1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawanieKlienta window = new DodawanieKlienta();
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
	public DodawanieKlienta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Dodanie klienta");
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 24));
		frame.setBounds(100, 100, 387, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		// wczytanie ekstensji z pliku
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
			Ekstensja.load(ois);
			// wyjatek co gdy plik jest pusty
		} catch (Exception e) {
			System.out.println("Brak ekstensji");
		}
		
		JLabel lblNewLabel = new JLabel("Imie");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel.setBounds(21, 194, 92, 26);
		frame.getContentPane().add(lblNewLabel);
		
		fieldImie = new JTextField();
		fieldImie.setBounds(160, 192, 186, 32);
		frame.getContentPane().add(fieldImie);
		fieldImie.setColumns(10);
		
		fieldNazwisko = new JTextField();
		fieldNazwisko.setBounds(160, 84, 186, 32);
		frame.getContentPane().add(fieldNazwisko);
		fieldNazwisko.setColumns(10);
		
		fieldTelefon = new JTextField();
		fieldTelefon.setBounds(160, 137, 186, 32);
		frame.getContentPane().add(fieldTelefon);
		fieldTelefon.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(21, 84, 195, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nr telefonu");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(21, 137, 195, 26);
		frame.getContentPane().add(lblNewLabel_2);
	
		JLabel lblNewLabel_3 = new JLabel("e - mail");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(21, 252, 92, 26);
		frame.getContentPane().add(lblNewLabel_3);
			
		JTextArea areaMaile = new JTextArea();
		JScrollPane scroll = new JScrollPane(areaMaile);
		scroll.setBounds(160, 253, 186, 66);
		frame.getContentPane().add(scroll);
		
		fieldId = new JTextField();
		fieldId.setText(Ekstensja.getEkstensja(KlientZarejestrowany.class).size() + 1 + "");
		fieldId.setBounds(160, 31, 186, 32);
		fieldId.setEditable(false);
		frame.getContentPane().add(fieldId);
		fieldId.setColumns(10);
		
		lblNewLabel_4 = new JLabel("ID klienta");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(21, 37, 141, 26);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//czy wszystkie pola sa wypelnione
				if (fieldImie.getText().isEmpty() || fieldNazwisko.getText().isEmpty() || fieldTelefon.getText().isEmpty() || fieldId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Zostawi≥es puste pola");
					return;
				}
				//matchera do telefonu
				if(!fieldTelefon.getText().matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "niepoprawny numer telefonu");
					return;
				}
				
	 
				//imie i nazwisko musza miec przynajmniej dlugosc 3
				
				if(!fieldImie.getText().matches("\\w{3,}") || !fieldNazwisko.getText().matches("\\w{3,}")) {
					JOptionPane.showMessageDialog(null, "èle wpisane imie lub naziwsko");
					return;
				}
				//tworzymy obiekt z danych podanych w textfieldach
				try {
					k1 = new KlientZarejestrowany(fieldImie.getText(), fieldNazwisko.getText(), fieldTelefon.getText());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				
			
				//wpisywanie maili - walidacja maila i na koncu przypisanie metoda maila do klienta ktorego rejestrujemy
				
				String[] maile = areaMaile.getText().split("\n");
				Pattern mail = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,6}");
				Matcher matcher;
				
				int dlugosc = 0;
				
				// obiekt klasy Highlighter do podswietlenia niepoprawnego maila
				Highlighter h = areaMaile.getHighlighter();
				// usuniecie poprzedniego podswietlenia
				h.removeAllHighlights();
				
				for(String  element : maile) {
					
					matcher = mail.matcher(element);
					
					if(matcher.matches()){
						dlugosc += element.length() + 1;
						k1.dodajEmail(element);
					}else {
						// jesli nie to wyrzucamy blad ze adres jest niepoprawny
						JOptionPane.showMessageDialog(null, "Adres mailowy " + element + " jest niepoprawny");
						try {
							// podswietlamy dany mail ktory byl niepoprawny, podswietlenie zacyzna sie od
							// dlugosci, do dlugosci +
							// to co do tej pory pominelismy
							h.addHighlight(dlugosc, dlugosc + element.length(), DefaultHighlighter.DefaultPainter);

						} catch (Exception e1) {

							e1.printStackTrace();
						}
						return;
					}
				} 
				
				//zapis do ekstensji	
				try {
					// zapis do ekstensji
	 				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
					Ekstensja.save(oos);
					oos.close();
				} catch (Exception e1) {

				}

				// powrÛt
				JOptionPane.showMessageDialog(null, "Dodano klienta do bazy");

				frame.setVisible(false);

				DodawanieKlienta dodanie = new DodawanieKlienta();
				dodanie.getFrame().setVisible(true);

			}
		});
		btnNewButton.setBounds(214, 337, 132, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wstecz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(21, 337, 132, 35);
		frame.getContentPane().add(btnNewButton_1);
	}

	public Window getFrame() {
		return frame;
	}
}
