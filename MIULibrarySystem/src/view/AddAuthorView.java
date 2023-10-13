package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.LibrarySystemException;
import helper.Helper;
import model.Address;
import model.Author;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class AddAuthorView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhoneNumber;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	private JCheckBox chckExpert;
	private AddBookView addBookView;
	private JTextArea txtShortBio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAuthorView frame = new AddAuthorView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAuthorView(AddBookView addBookView) {
		this.addBookView = addBookView;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);setResizable(false);

		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Add Author");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 10, 438, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 40, 180, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 70, 180, 20);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone Number");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(10, 100, 180, 20);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(10, 130, 150, 20);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Street");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(10, 160, 180, 20);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("City");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(10, 190, 180, 20);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("State");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(10, 220, 180, 20);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Zip");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(10, 250, 180, 20);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Credential");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(10, 280, 180, 20);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Short Bio");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(10, 310, 180, 20);
		contentPane.add(lblNewLabel_10);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(210, 40, 200, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(210, 70, 200, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(210, 100, 200, 20);
		contentPane.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);

		txtStreet = new JTextField();
		txtStreet.setBounds(210, 160, 200, 20);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);

		txtCity = new JTextField();
		txtCity.setBounds(210, 190, 200, 20);
		contentPane.add(txtCity);
		txtCity.setColumns(10);

		txtState = new JTextField();
		txtState.setBounds(210, 220, 200, 20);
		contentPane.add(txtState);
		txtState.setColumns(10);

		txtZip = new JTextField();
		txtZip.setBounds(210, 250, 200, 20);
		contentPane.add(txtZip);
		txtZip.setColumns(10);

		chckExpert = new JCheckBox("Expert");
		chckExpert.setBounds(210, 280, 200, 20);
		contentPane.add(chckExpert);

		txtShortBio = new JTextArea();
		txtShortBio.setBounds(210, 310, 200, 150);
		contentPane.add(txtShortBio);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave_click();
			}
		});
		btnSave.setBounds(30, 470, 117, 29);
		contentPane.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 470, 117, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_click();
			}
		});
		contentPane.add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(172, 135, 241, 12);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 135, 90, 12);
		contentPane.add(separator_1);
	}

	protected void btnCancel_click() {
		addBookView.setVisible(true);
		this.dispose();
	}

	protected void btnSave_click() {
		try {
			if (txtFirstName.getText().isBlank())
				throw new LibrarySystemException("Enter First Name");
			if (txtLastName.getText().isBlank())
				throw new LibrarySystemException("Enter Last Name");
			if (txtPhoneNumber.getText().isBlank())
				throw new LibrarySystemException("Enter Phone Number");
			if(!Helper.isPhoneNumber(txtPhoneNumber.getText()))
				throw new LibrarySystemException("Enter Valid Phone Number");
			if (txtStreet.getText().isBlank())
				throw new LibrarySystemException("Enter Street");
			if (txtCity.getText().isBlank())
				throw new LibrarySystemException("Enter City");
			if (txtState.getText().isBlank())
				throw new LibrarySystemException("Enter State");
			if (txtZip.getText().isBlank())
				throw new LibrarySystemException("Enter Zip");

			Address address = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());

			this.addBookView.addAuthor(new Author(txtFirstName.getText(), txtLastName.getText(),
					txtPhoneNumber.getText(), address, chckExpert.isSelected(), txtShortBio.getText()));
			addBookView.ShowAuthors();
			addBookView.setVisible(true);
			this.dispose();
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
