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
	private LoginUser user;

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
	public AddMemberView(LoginUser user) {
		this.user = user;

		AddMemberController addMembController = new AddMemberController();
		String membId = addMembController.generateMemberId();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		txtStreet.setBounds(180, 160, 200, 30);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Street");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(20, 160, 150, 30);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("City");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(20, 200, 150, 30);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("State");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(20, 240, 150, 30);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Zip");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(20, 280, 150, 30);
		contentPane.add(lblNewLabel_7);

		txtCity = new JTextField();
		txtCity.setBounds(180, 200, 200, 30);
		contentPane.add(txtCity);
		txtCity.setColumns(10);

		txtState = new JTextField();
		txtState.setBounds(180, 240, 200, 30);
		contentPane.add(txtState);
		txtState.setColumns(10);

		txtZip = new JTextField();
		txtZip.setBounds(180, 280, 200, 30);
		contentPane.add(txtZip);
		txtZip.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.setBounds(20, 320, 150, 30);
		contentPane.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Click();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(263, 320, 117, 30);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
	}

	public void cancel_Click() {
		MainMenuView mm = new MainMenuView(this.user);
		mm.displayMenu();
		this.dispose();
	}

	public void save_Click() {
		try {
			if (txtFirstName.getText().isBlank())
				throw new LibrarySystemException("First Name can't be null!");
			if (txtLastName.getText().isBlank())
				throw new LibrarySystemException("Last Name can't be null!");
			if (txtStreet.getText().isBlank())
				throw new LibrarySystemException("Street field can't be empty!");
			if (txtCity.getText().isEmpty())
				throw new LibrarySystemException("City field can't be empty");
			if (txtState.getText().isBlank())
				throw new LibrarySystemException("State field can't be empty");
			if (txtZip.getText().isBlank())
				throw new LibrarySystemException("Zip field can't be empty");

			AddMemberController addMemberController = new AddMemberController();
			Address add = new Address(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
			LibraryMember member = new LibraryMember(txtFirstName.getText(), txtLastName.getText(), "", 
					add, txtMemberID.getText());
					addMemberController.addMember(member);
					JOptionPane.showMessageDialog(this, "MEMBER ADDED SUCCESSFULLY");
					dispose();
					MainMenuView mmv= new MainMenuView(null);
					mmv.displayMenu();
					mmv.setVisible(true);
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
