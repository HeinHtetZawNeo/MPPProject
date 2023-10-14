package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CheckOutBookController;
import exception.LibrarySystemException;
import helper.Helper;
import model.Author;
import model.Book;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckOutBook_AddBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIsbnNumber;
	private JTextField txtTitle;
	private CheckOutBookView cobv;
	private JLabel lblBookTitleValue;
	private JLabel lblBookISBNNumberValue;
	private JLabel lblBookAuthorValue;
	private Book b;
	/**
	 * Create the frame.
	 */
	public CheckOutBook_AddBookView(CheckOutBookView cobv) {
		this.cobv = cobv;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddBook = new JLabel("Add Book");
		lblAddBook.setBounds(188, 6, 61, 16);
		contentPane.add(lblAddBook);

		JLabel lblISBN = new JLabel("ISBN Number");
		lblISBN.setBounds(24, 43, 109, 16);
		contentPane.add(lblISBN);

		txtIsbnNumber = new JTextField();
		txtIsbnNumber.setBounds(145, 38, 235, 26);
		contentPane.add(txtIsbnNumber);
		txtIsbnNumber.setColumns(10);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(24, 71, 99, 16);
		contentPane.add(lblTitle);

		txtTitle = new JTextField();
		txtTitle.setBounds(145, 66, 235, 26);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblBookTitle = new JLabel("Title");
		lblBookTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookTitle.setBounds(72, 148, 61, 16);
		contentPane.add(lblBookTitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 101, 438, 1);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 131, 356, 12);
		contentPane.add(separator_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch_Click();
			}
		});
		btnSearch.setBounds(263, 95, 117, 29);
		contentPane.add(btnSearch);

		lblBookTitleValue = new JLabel("");
		lblBookTitleValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookTitleValue.setBounds(145, 148, 61, 16);
		contentPane.add(lblBookTitleValue);

		JLabel lblbookISBN = new JLabel("ISBN Number");
		lblbookISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbookISBN.setBounds(24, 171, 109, 16);
		contentPane.add(lblbookISBN);

		lblBookISBNNumberValue = new JLabel("");
		lblBookISBNNumberValue.setHorizontalAlignment(SwingConstants.LEFT);
		lblBookISBNNumberValue.setBounds(145, 171, 61, 16);
		contentPane.add(lblBookISBNNumberValue);

		JLabel lblBookAuthor = new JLabel("Author");
		lblBookAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookAuthor.setBounds(72, 193, 61, 16);
		contentPane.add(lblBookAuthor);

		lblBookAuthorValue = new JLabel("");
		lblBookAuthorValue.setBounds(145, 193, 299, 16);
		contentPane.add(lblBookAuthorValue);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd_Click();
			}
		});
		btnAdd.setBounds(24, 221, 117, 29);
		contentPane.add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancel_Click();
			}
		});
		btnCancel.setBounds(263, 221, 117, 29);
		contentPane.add(btnCancel);
	}

	protected void btnCancel_Click() {
		this.dispose();
		cobv.setVisible(true);
	}

	protected void btnAdd_Click() {
		try {
			if(b==null) throw new LibrarySystemException("Select a valid Book");
			
			cobv.addBook(b);
			cobv.setVisible(true);
			this.dispose();
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	protected void btnSearch_Click() {
		try {
			if (txtIsbnNumber.getText().trim().equals("") && txtTitle.getText().trim().equals("")) {
				throw new LibrarySystemException("Add ISBN number or Title to search");
			}
			if((!txtIsbnNumber.getText().isBlank())&&!Helper.isISBNNumber(txtIsbnNumber.getText()))
				throw new LibrarySystemException("Enter Valid ISBN number");
			CheckOutBookController cob = new CheckOutBookController();
			if (!txtIsbnNumber.getText().trim().equals(""))
				b = cob.searchBookByIsbn(txtIsbnNumber.getText());
			else
				b = cob.searchBookByTitle(txtTitle.getText());

			if(cob.isBookAvailable(b)==null) {
				b=null;
				throw new LibrarySystemException("This Book is not Available");
			}
			lblBookTitleValue.setText(b.getTitle());
			lblBookISBNNumberValue.setText(b.getIsbnNumber());

			String author = "";
			for (Author a : b.getAuthorList()) {
				author += a.getFirstName() + " " + a.getLastName() + ",";
			}
			author = author.substring(0, author.length() - 1);
			lblBookAuthorValue.setText(author);

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

}
