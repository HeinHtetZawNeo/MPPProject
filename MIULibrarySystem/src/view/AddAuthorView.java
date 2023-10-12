package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Address;
import model.Author;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAuthorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FirstName;
	private JTextField LastName;
	private JTextField Phone;
	private JTextField Street;
	private JTextField City;
	private JTextField State;
	private JTextField Zip;
	private AddBookView abv;

/*
	 * Create the frame.
	 */
	public AddAuthorView(AddBookView abv) {
		this.abv = abv;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -45, 624, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Add Author");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(214, 11, 223, 35);
		contentPane.add(lblNewLabel);

		FirstName = new JTextField();
		FirstName.setBounds(198, 94, 180, 20);
		contentPane.add(FirstName);
		FirstName.setColumns(10);

		LastName = new JTextField();
		LastName.setBounds(198, 138, 180, 20);
		contentPane.add(LastName);
		LastName.setColumns(10);

		JLabel First_Name = new JLabel("First Name");
		First_Name.setBounds(127, 97, 93, 14);
		contentPane.add(First_Name);

		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(127, 141, 71, 14);
		contentPane.add(lblNewLabel_1);

		Phone = new JTextField();
		Phone.setBounds(198, 181, 184, 20);
		contentPane.add(Phone);
		Phone.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Phone#");
		lblNewLabel_2.setBounds(131, 184, 49, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 24));
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setBounds(242, 212, 158, 62);
		contentPane.add(lblNewLabel_3);

		Street = new JTextField();
		Street.setBounds(198, 361, 180, 20);
		contentPane.add(Street);
		Street.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setBounds(127, 364, 49, 14);
		contentPane.add(lblNewLabel_4);

		City = new JTextField();
		City.setBounds(197, 316, 181, 20);
		contentPane.add(City);
		City.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setBounds(131, 319, 49, 14);
		contentPane.add(lblNewLabel_5);

		State = new JTextField();
		State.setBounds(198, 272, 180, 20);
		contentPane.add(State);
		State.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setBounds(131, 275, 49, 14);
		contentPane.add(lblNewLabel_6);

		Zip = new JTextField();
		Zip.setBounds(198, 392, 180, 20);
		contentPane.add(Zip);
		Zip.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Zip");
		lblNewLabel_7.setBounds(131, 395, 49, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Expert");
		lblNewLabel_8.setBounds(127, 437, 49, 14);
		contentPane.add(lblNewLabel_8);

		JCheckBox Expert = new JCheckBox("Yes");
		Expert.setBounds(198, 433, 99, 23);
		contentPane.add(Expert);

		JLabel lblNewLabel_9 = new JLabel("Short Bio");
		lblNewLabel_9.setBounds(114, 474, 106, 14);
		contentPane.add(lblNewLabel_9);

		JEditorPane ShortBio = new JEditorPane();
		ShortBio.setBounds(198, 458, 239, 72);
		contentPane.add(ShortBio);

		// String firstName, String lastName, String phoneNumber, Address address,
		// boolean credentials,
		// String shortBio
		JButton SaveAuthoerInfo = new JButton("Save");
		SaveAuthoerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean expertvalue= Expert.isSelected();
				
				Address location = new Address(Street.getText(), City.getText(), State.getText(), Zip.getText());
				abv.addAuthor(new Author(FirstName.getText(), LastName.getText(), Phone.getText(), location, expertvalue,
						ShortBio.getText()));
				abv.ShowAuthors();//calling the print author function
				dispose();
				abv.setVisible(true);
			}
		});
		SaveAuthoerInfo.setBackground(new Color(0, 255, 0));
		SaveAuthoerInfo.setBounds(208, 541, 89, 23);
		contentPane.add(SaveAuthoerInfo);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(369, 541, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_3_1 = new JLabel("Personal Info");
		lblNewLabel_3_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_3_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 24));
		lblNewLabel_3_1.setBounds(208, 40, 158, 62);
		contentPane.add(lblNewLabel_3_1);
	}
}
