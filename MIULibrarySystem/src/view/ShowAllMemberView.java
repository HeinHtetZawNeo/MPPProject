package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controller.ShowAllController;
import exception.LibrarySystemException;
import helper.Helper;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowAllMemberView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable memberTable;
	private LoginUser loginUser;
	private String[][] data;
	private JTextField txtMemberNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllMemberView frame = new ShowAllMemberView(null);
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
	public ShowAllMemberView(LoginUser loginUser) {
		this.loginUser = loginUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Library Members");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 580, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel2 = new JLabel("Member ID");
		lblNewLabel2.setBounds(10, 40, 150, 20);
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel2);

		txtMemberNumber = new JTextField();
		txtMemberNumber.setBounds(170, 40, 200, 20);
		contentPane.add(txtMemberNumber);

		JButton btnSearchMemberID = new JButton("Search");
		btnSearchMemberID.setBounds(380, 40, 100, 20);
		btnSearchMemberID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearchMemberID_click();
			}
		});
		contentPane.add(btnSearchMemberID);

		String[] columnNames = { "Member ID", "Name", "Phone No", "Street", "City", "State", "Zip" };
		data = showMemberData();
		memberTable = new JTable(data, columnNames);
		memberTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					jtableClick(target.getSelectedRow());
				}
			}
		});
		// make jtable not to editable
		memberTable.setDefaultEditor(Object.class, null);
		memberTable.setAutoCreateRowSorter(true);
		// memberTable.setBounds(10, 30, 450, 300);

		JScrollPane sp = new JScrollPane(memberTable);
		sp.setLocation(10, 70);
		sp.setSize(580, 309);
		contentPane.add(sp);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(250, 390, 100, 20);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();

			}
		});
		contentPane.add(btnOK);
	}

	protected void btnSearchMemberID_click() {
		try {
			if(txtMemberNumber.getText().isBlank())
				throw new LibrarySystemException("Enter Member ID");
			if(!Helper.isNumber(txtMemberNumber.getText()))
				throw new LibrarySystemException("Enter valid Member ID");
			ShowAllController sac = new ShowAllController();
			if(sac.getMemberDetails(txtMemberNumber.getText()) != null) {
				this.setVisible(false);
				LibraryMemberDetailsView mhv = new LibraryMemberDetailsView(this, txtMemberNumber.getText(), loginUser);
				txtMemberNumber.setText("");
				mhv.setVisible(true);
			}else {
				throw new LibrarySystemException("Enter valid Member ID");
			}
				
		}catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberNumber.setText("");
		}
		
			
	}

	protected void jtableClick(int rowNumber) {
		this.setVisible(false);
		LibraryMemberDetailsView mhv = new LibraryMemberDetailsView(this, data[rowNumber][0], loginUser);
		mhv.setVisible(true);
	}

	protected void btnOK_click() {
		MainMenuView mmv = new MainMenuView(loginUser);
		this.dispose();
	}

	private String[][] showMemberData() {
		ShowAllController sac = new ShowAllController();
		return sac.getAllshowMemberDataForTable();
	}
}
