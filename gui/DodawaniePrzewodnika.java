package gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import zadanie.projektowe.Ekstensja;
import zadanie.projektowe.KlientZarejestrowany;
import zadanie.projektowe.Przewodnik;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class DodawaniePrzewodnika {

	private JFrame frame;
	private JTextField fieldImie;
	private JTextField fieldNazwisko;
	private JTextField fieldTelefon;
	private JTextField fieldEmail;
	private Przewodnik p1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodawaniePrzewodnika window = new DodawaniePrzewodnika();
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
	public DodawaniePrzewodnika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 348, 546);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Imi\u0119");
		lblNewLabel.setBounds(21, 53, 92, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko");
		lblNewLabel_1.setBounds(21, 100, 92, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Telefon");
		lblNewLabel_2.setBounds(21, 148, 92, 26);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(21, 195, 92, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		fieldImie = new JTextField();
		fieldImie.setBounds(134, 50, 186, 32);
		frame.getContentPane().add(fieldImie);
		fieldImie.setColumns(10);
		
		fieldNazwisko = new JTextField();
		fieldNazwisko.setBounds(134, 97, 186, 32);
		frame.getContentPane().add(fieldNazwisko);
		fieldNazwisko.setColumns(10);
		
		fieldTelefon = new JTextField();
		fieldTelefon.setBounds(134, 145, 186, 32);
		frame.getContentPane().add(fieldTelefon);
		fieldTelefon.setColumns(10);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(134, 184, 186, 94);
		frame.getContentPane().add(scroll);
		
		JTextArea areaMaile = new JTextArea();
		scroll.setViewportView(areaMaile);
		
		JRadioButton angielski = new JRadioButton("Angielski");
		angielski.setBounds(134, 295, 206, 30);
		frame.getContentPane().add(angielski);
		

		
		JRadioButton wloski = new JRadioButton("W\u0142oski");
		wloski.setBounds(134, 338, 201, 35);
		frame.getContentPane().add(wloski);
		
		JRadioButton francuski = new JRadioButton("Francuski");
		francuski.setBounds(134, 376, 201, 35);
		frame.getContentPane().add(francuski);
		
		JLabel lblNewLabel_4 = new JLabel("J\u0119zyki");
		lblNewLabel_4.setBounds(21, 297, 92, 26);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton wstecz = new JButton("Wstecz");
		wstecz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MenuGlowne menu = new MenuGlowne();
				frame.setVisible(false);
				menu.getFrame().setVisible(true);
				
			}
		

			
			
//			public void actionPerformed(ActionEvent arg0) {
//				System.out.println(angielski.isSelected());
//				System.out.println(angielski.getLabel());
//			}
		});
		wstecz.setBounds(21, 428, 141, 35);
		frame.getContentPane().add(wstecz);
		
		JButton btnNewButton_1 = new JButton("Dodaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//czy wszystkie pola sa wypelnione
				if (fieldImie.getText().isEmpty() || fieldNazwisko.getText().isEmpty() || fieldTelefon.getText().isEmpty() ) {
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
					p1 = new Przewodnik(fieldImie.getText(), fieldNazwisko.getText(), fieldTelefon.getText());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				
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
						p1.dodajEmail(element);
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
				if(angielski.isSelected()) {
					p1.dodajJezyk("W≥oski");
				}
				if(wloski.isSelected()) {
					p1.dodajJezyk("W≥oski");
				}
				if(francuski.isSelected()) {
					p1.dodajJezyk("Angielski");
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
				JOptionPane.showMessageDialog(null, "Dodano przewodnika do bazy");

				frame.setVisible(false);

				DodawaniePrzewodnika dodaniePrzewodnika = new DodawaniePrzewodnika();
				dodaniePrzewodnika.getFrame().setVisible(true);
				
				//wyswietalnie przewodnikow- homework
				
				
			}
		});
			
		
		btnNewButton_1.setBounds(183, 428, 141, 35);
		frame.getContentPane().add(btnNewButton_1);
	}

	public Window getFrame() {
		return frame;
	}
}
