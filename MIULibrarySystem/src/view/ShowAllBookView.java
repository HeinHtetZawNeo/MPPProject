package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowAllBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable bookTable;
	private LoginUser loginUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllBookView frame = new ShowAllBookView(null);
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
	public ShowAllBookView(LoginUser loginUser) {
		this.loginUser = loginUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Books");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 453, 30);
		contentPane.add(lblNewLabel);

		String[] columnNames = { "ISBN", "Title", "No Of Copy", "Available" };
		String[][] data = showBookData();
		bookTable = new JTable(data, columnNames);
		bookTable.setBounds(10, 30, 450, 300);

		JScrollPane sp = new JScrollPane(bookTable);
		sp.setLocation(10, 40);
		sp.setSize(453, 309);
		contentPane.add(sp);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(172, 360, 100, 20);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();
				
			}
		});
		contentPane.add(btnOK);
	}

	protected void btnOK_click() {
		MainMenuView mmv = new MainMenuView(loginUser);
		this.dispose();
	}

	private String[][] showBookData() {
		ShowAllController sac = new ShowAllController();
		return sac.getAllBookForTable();
	}

}