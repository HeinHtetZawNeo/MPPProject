package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import exception.LibrarySystemException;
import helper.Helper;
import model.Book;
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

public class ShowAllBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable bookTable;
	private LoginUser loginUser;
	private String[][] data;
	private JTextField txtIsbnNumber;
	private JTextField txtTitle;

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
		setBounds(100, 100, 900, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Books");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 900, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel2 = new JLabel("ISBN Number");
		lblNewLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel2.setBounds(10, 40, 150, 20);
		contentPane.add(lblNewLabel2);

		JLabel lblNewLabel3 = new JLabel("Book Title");
		lblNewLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel3.setBounds(10, 70, 150, 20);
		contentPane.add(lblNewLabel3);

		txtIsbnNumber = new JTextField();
		txtIsbnNumber.setBounds(170, 40, 200, 20);
		contentPane.add(txtIsbnNumber);

		txtTitle = new JTextField();
		txtTitle.setBounds(170, 70, 200, 20);
		contentPane.add(txtTitle);

		JButton btnSearch = new JButton("Search Book");
		btnSearch.setBounds(400, 70, 150, 20);
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSearch_click();

			}
		});
		contentPane.add(btnSearch);

		String[] columnNames = { "ISBN", "Title", "No Of Copy", "Available", "In Circulation" };
		data = showBookData();
		bookTable = new JTable(data, columnNames);
		// make jtable not to editable
		bookTable.setDefaultEditor(Object.class, null);
		bookTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					jtableClick(target.getSelectedRow());
				}
			}
		});
		bookTable.setBounds(10, 30, 450, 300);

		JScrollPane sp = new JScrollPane(bookTable);
		sp.setLocation(10, 100);
		sp.setSize(880, 309);
		contentPane.add(sp);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(400, 420, 100, 20);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();

			}
		});
		contentPane.add(btnOK);
	}

	protected void btnSearch_click() {
		try {
			if (txtIsbnNumber.getText().isBlank() && txtTitle.getText().isBlank())
				throw new LibrarySystemException("Enter ISBN Number or Title to search");
			if (!txtIsbnNumber.getText().isBlank() && !Helper.isISBNNumber(txtIsbnNumber.getText()))
				throw new LibrarySystemException("Enter valid ISBN");
			ShowAllController sac = new ShowAllController();
			Book b = null;
			if (!txtIsbnNumber.getText().isBlank()) {
				b = sac.getBookByISBN(txtIsbnNumber.getText());
			} else {
				b = sac.getBookByTitle(txtTitle.getText());
			}
			if (b == null)
				throw new LibrarySystemException("Enter valid ISBN or valid Title");

			this.setVisible(false);
			BookDetailsView bdv = new BookDetailsView(this, b.getIsbnNumber(), this.loginUser);
			bdv.setVisible(true);

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} finally {
			txtIsbnNumber.setText("");
			txtTitle.setText("");
		}
	}

	protected void jtableClick(int selectedRow) {
		this.setVisible(false);
		BookDetailsView bdv = new BookDetailsView(this, data[selectedRow][0], this.loginUser);
		bdv.setVisible(true);
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
