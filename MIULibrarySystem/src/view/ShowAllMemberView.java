package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controller.ShowAllController;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JTable;
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
		setBounds(100, 100, 600, 420);
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
		memberTable.setBounds(10, 30, 450, 300);

		JScrollPane sp = new JScrollPane(memberTable);
		sp.setLocation(10, 40);
		sp.setSize(580, 309);
		contentPane.add(sp);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(250, 360, 100, 20);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();

			}
		});
		contentPane.add(btnOK);
	}

	protected void jtableClick(int rowNumber) {
		this.setVisible(false);
		LibraryMemberDetailsView mhv = new LibraryMemberDetailsView(this, data[rowNumber][0]);
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
