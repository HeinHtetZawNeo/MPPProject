package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.Author;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class AuthorDetailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblPhoneNumber;
	private JLabel lblStreet;
	private JLabel lblCity;
	private JLabel lblState;
	private JLabel lblZip;
	private JButton btnOK;
	private JFrame previousFrame;
	private Author author;
	private JLabel lblNewLabel_1;
	private JLabel lblCredentialExpert;
	private JLabel lblNewLabel_11;
	private JTextPane tpShortBio;

	/**
	 * Create the frame.
	 */
	public AuthorDetailView(JFrame previousFrame, Author author) {
		this.previousFrame = previousFrame;
		this.author = author;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Author Detail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 400, 20);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setBounds(10, 70, 150, 20);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setBounds(10, 100, 150, 20);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Phone Number");
		lblNewLabel_4.setBounds(10, 130, 150, 20);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setBounds(10, 160, 150, 20);
		contentPane.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Street");
		lblNewLabel_6.setBounds(10, 190, 150, 20);
		contentPane.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("City");
		lblNewLabel_7.setBounds(10, 220, 150, 20);
		contentPane.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("State");
		lblNewLabel_8.setBounds(10, 250, 150, 20);
		contentPane.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Zip");
		lblNewLabel_9.setBounds(10, 280, 150, 20);
		contentPane.add(lblNewLabel_9);

		lblFirstName = new JLabel("");
		lblFirstName.setBounds(170, 70, 150, 20);
		contentPane.add(lblFirstName);

		lblLastName = new JLabel("");
		lblLastName.setBounds(170, 100, 150, 20);
		contentPane.add(lblLastName);

		lblPhoneNumber = new JLabel("");
		lblPhoneNumber.setBounds(170, 130, 150, 20);
		contentPane.add(lblPhoneNumber);

		lblStreet = new JLabel("");
		lblStreet.setBounds(170, 190, 150, 20);
		contentPane.add(lblStreet);

		lblCity = new JLabel("");
		lblCity.setBounds(170, 220, 150, 20);
		contentPane.add(lblCity);

		lblState = new JLabel("");
		lblState.setBounds(172, 250, 150, 20);
		contentPane.add(lblState);

		lblZip = new JLabel("");
		lblZip.setBounds(170, 280, 150, 20);
		contentPane.add(lblZip);

		btnOK = new JButton("OK");
		btnOK.setBounds(120, 550, 150, 30);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();
			}
		});
		contentPane.add(btnOK);

		lblNewLabel_1 = new JLabel("Credential Expert");
		lblNewLabel_1.setBounds(10, 310, 150, 20);
		contentPane.add(lblNewLabel_1);

		lblCredentialExpert = new JLabel("");
		lblCredentialExpert.setBounds(170, 310, 150, 20);
		contentPane.add(lblCredentialExpert);

		lblNewLabel_11 = new JLabel("Short Bio");
		lblNewLabel_11.setBounds(10, 340, 150, 20);
		contentPane.add(lblNewLabel_11);

		tpShortBio = new JTextPane();
		tpShortBio.setEnabled(false);
		tpShortBio.setEditable(false);
		tpShortBio.setBounds(170, 340, 240, 200);
		contentPane.add(tpShortBio);

		JScrollPane sp = new JScrollPane(tpShortBio);
		sp.setLocation(170, 340);
		sp.setSize(240, 200);
		contentPane.add(sp);
		loadData();
	}

	protected void btnOK_click() {
		previousFrame.setVisible(true);
		this.dispose();
		;
	}

	private void loadData() {
		lblFirstName.setText(author.getFirstName());
		lblLastName.setText(author.getLastName());
		lblPhoneNumber.setText(author.getPhoneNumber());
		lblStreet.setText(author.getAddress().getStreet());
		lblCity.setText(author.getAddress().getCity());
		lblState.setText(author.getAddress().getState());
		lblZip.setText(author.getAddress().getZip());
		if (author.isCredentials())
			lblCredentialExpert.setText("Yes");
		else
			lblCredentialExpert.setText("No");
		tpShortBio.setText(author.getShortBio());
	}
}
