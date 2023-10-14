package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import exception.LibrarySystemException;
import model.Admin;
import model.LibraryMember;
import model.LoginUser;
import model.SuperUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LibraryMemberDetailsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jtbMemberHistory;
	private JLabel lblMemberID;
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
	private String memberID;
	private String[][] data;
	private LoginUser loginUser;
	private JButton btnEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryMemberDetailsView frame = new LibraryMemberDetailsView(null, "0001",
							new SuperUser("super", "super"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param loginUser
	 */
	public LibraryMemberDetailsView(JFrame previousFrame, String memberID, LoginUser loginUser) {
		this.previousFrame = previousFrame;
		this.memberID = memberID;
		this.loginUser = loginUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Member Detail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 400, 20);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Member ID");
		lblNewLabel_1.setBounds(10, 40, 150, 20);
		contentPane.add(lblNewLabel_1);

		lblMemberID = new JLabel("");
		lblMemberID.setBounds(170, 40, 150, 20);
		contentPane.add(lblMemberID);

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

		String[] column = { "Check Out Date", "Total Fine", "Total Paid", "Total Check Out Entries", "Total OverDue" };
		data = showCheckoutRecordData();
		jtbMemberHistory = new JTable(data, column);
		jtbMemberHistory.setBounds(10, 310, 400, 200);
		jtbMemberHistory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					jtableClick(target.getSelectedRow());
				}
			}
		});
		// make jtable not to editable
		jtbMemberHistory.setDefaultEditor(Object.class, null);

		JScrollPane sp = new JScrollPane(jtbMemberHistory);
		sp.setLocation(10, 310);
		sp.setSize(400, 200);
		contentPane.add(sp);

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
		btnOK.setBounds(120, 520, 150, 30);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();
			}
		});
		contentPane.add(btnOK);
		if(loginUser instanceof SuperUser || loginUser instanceof Admin) {
			btnOK.setBounds(50, 520, 150, 30);
			btnEdit = new JButton("Edit");
			btnEdit.setBounds(220, 520, 150, 30);
			btnEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					btnEdit_click();
				}
			});
			contentPane.add(btnEdit);
		}
		

		loadData();
	}

	protected void btnEdit_click() {
		ShowAllController sac = new ShowAllController();
		LibraryMember lm = sac.getMemberDetails(memberID);
		AddMemberView amv = new AddMemberView(loginUser,lm);
		amv.setVisible(true);
		this.dispose();
	}

	protected void jtableClick(int selectedRow) {
		System.out.println(selectedRow);
		ShowAllController sac = new ShowAllController();
		LibraryMember lm = sac.getMemberDetails(memberID);

		MemberCheckOutEntryView mcoe = new MemberCheckOutEntryView(this, lm.getCheckoutRecord().get(selectedRow));
		mcoe.setVisible(true);
		this.setVisible(false);
	}

	private String[][] showCheckoutRecordData() {
		ShowAllController sac = new ShowAllController();

		return sac.getMemberDetailForTable(this.memberID);
	}

	protected void btnOK_click() {
		previousFrame.setVisible(true);
		this.dispose();
	}

	private void loadData() {
		try {
			ShowAllController sac = new ShowAllController();
			LibraryMember lm = sac.getMemberDetails(memberID);
			if (lm == null)
				throw new LibrarySystemException("Something went wrong");

			lblMemberID.setText(lm.getMemberNumber());
			lblFirstName.setText(lm.getFirstName());
			lblLastName.setText(lm.getLastName());
			lblPhoneNumber.setText(lm.getPhoneNumber());
			lblStreet.setText(lm.getAddress().getStreet());
			lblCity.setText(lm.getAddress().getCity());
			lblState.setText(lm.getAddress().getState());
			lblZip.setText(lm.getAddress().getZip());

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
