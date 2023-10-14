package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AddMemberController;
import exception.LibrarySystemException;
import helper.Helper;
import model.LibraryMember;
import model.LoginUser;
import model.Address;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class AddMemberView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMemberID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	private JButton btnCancel;
	private JButton btnSave;
	private LoginUser loginUser;
	private JTextField txtPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberView frame = new AddMemberView(null);
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
	public AddMemberView(LoginUser loginUser) {
		this.loginUser = loginUser;

		AddMemberController addMembController = new AddMemberController();
		String membId = addMembController.generateMemberId();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Member");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(170, 10, 88, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Member ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 40, 150, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(20, 80, 150, 30);
		contentPane.add(lblNewLabel_2);

		txtMemberID = new JTextField();
		txtMemberID.setText(membId);
		txtMemberID.setEnabled(false);
		txtMemberID.setEditable(false);
		txtMemberID.setBounds(180, 40, 200, 30);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(180, 80, 200, 30);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(180, 120, 200, 30);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(20, 120, 150, 30);
		contentPane.add(lblNewLabel_3);

		txtStreet = new JTextField();
		txtStreet.setBounds(180, 200, 200, 30);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(20, 200, 150, 30);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(20, 240, 150, 30);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(20, 280, 150, 30);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Zip");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(20, 320, 150, 30);
		contentPane.add(lblNewLabel_7);

		txtCity = new JTextField();
		txtCity.setBounds(180, 240, 200, 30);
		contentPane.add(txtCity);
		txtCity.setColumns(10);

		txtState = new JTextField();
		txtState.setBounds(180, 280, 200, 30);
		contentPane.add(txtState);
		txtState.setColumns(10);

		txtZip = new JTextField();
		txtZip.setBounds(180, 320, 200, 30);
		contentPane.add(txtZip);
		txtZip.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.setBounds(20, 360, 150, 30);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Click();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(263, 360, 117, 30);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_8 = new JLabel("Phone Number");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(20, 160, 150, 30);
		contentPane.add(lblNewLabel_8);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBounds(180, 160, 200, 30);
		contentPane.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
	}

	public void cancel_Click() {
		MainMenuView mm = new MainMenuView(this.loginUser);
		this.dispose();
	}

	public void save_Click() {
		try {
			if (txtFirstName.getText().isBlank())
				throw new LibrarySystemException("Enter First Name");
			if (txtLastName.getText().isBlank())
				throw new LibrarySystemException("Enter Last Name");
			if (txtPhoneNumber.getText().isBlank())
				throw new LibrarySystemException("Enter Phone Number");
			if (!Helper.isPhoneNumber(txtPhoneNumber.getText()))
				throw new LibrarySystemException("Enter Valid Phone Number");
			if (txtStreet.getText().isBlank())
				throw new LibrarySystemException("Enter Street");
			if (txtCity.getText().isEmpty())
				throw new LibrarySystemException("Enter City");
			if (txtState.getText().isBlank())
				throw new LibrarySystemException("Enter State");
			if (txtZip.getText().isBlank())
				throw new LibrarySystemException("Enter Zip");
			if(!Helper.isNumber(txtZip.getText()))
				throw new LibrarySystemException("Enter Valid Zip");

			AddMemberController addMemberController = new AddMemberController();
			Address add = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
			LibraryMember member = new LibraryMember(txtFirstName.getText(), txtLastName.getText(), txtPhoneNumber.getText(), add,
					txtMemberID.getText());
			addMemberController.addMember(member);
			JOptionPane.showMessageDialog(this, "MEMBER ADDED SUCCESSFULLY");
			dispose();
			MainMenuView mmv = new MainMenuView(this.loginUser);
			mmv.setVisible(true);
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
