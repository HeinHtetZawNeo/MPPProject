package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CheckOutBookController;
import exception.LibrarySystemException;
import model.LibraryMember;
import model.LoginUser;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import model.Book;
import model.BookCopy;
import model.CheckoutEntry;
import model.CheckoutRecord;

public class CheckOutBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMemberID;
	private JList jlstBooklist;
	private JButton btnSearchMember;
	private JLabel lblMemberName;
	private HashMap<String, BookCopy> bookCopyList;
	private LibraryMember member;
	private LoginUser loginuser;

	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOutBookView frame = new CheckOutBookView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckOutBookView(LoginUser loginuser) {

		this.loginuser = loginuser;
		this.bookCopyList = new HashMap<String, BookCopy>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);

		JLabel lblCheckOutBook = new JLabel("Check Out");
		lblCheckOutBook.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblCheckOutBook.setBounds(164, 6, 121, 45);
		contentPane.add(lblCheckOutBook);

		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(33, 50, 119, 16);
		contentPane.add(lblMemberId);

		txtMemberID = new JTextField();
		txtMemberID.setBounds(120, 45, 165, 26);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);

		btnSearchMember = new JButton("Search Member");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkMember();
			}
		});
		btnSearchMember.setBounds(290, 45, 139, 29);
		contentPane.add(btnSearchMember);

		jlstBooklist = new JList(new DefaultListModel<String>());
		jlstBooklist.setBounds(33, 104, 252, 119);
		contentPane.add(jlstBooklist);

		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddBook_click();
			}
		});
		btnAddBook.setBounds(290, 99, 139, 29);
		contentPane.add(btnAddBook);

		JButton btnCheckOut = new JButton("CheckOut");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkout();
			}
		});
		btnCheckOut.setBounds(33, 237, 134, 29);
		contentPane.add(btnCheckOut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
		btnCancel.setBounds(295, 237, 134, 29);
		contentPane.add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 228, 456, 12);
		contentPane.add(separator);

		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setEnabled(false);
		btnDeleteBook.setBounds(290, 127, 139, 29);
		contentPane.add(btnDeleteBook);

		JLabel lblMemberNameLabel = new JLabel("Member Name");
		lblMemberNameLabel.setBounds(33, 78, 90, 16);
		contentPane.add(lblMemberNameLabel);

		lblMemberName = new JLabel("");
		lblMemberName.setBounds(130, 78, 299, 16);
		contentPane.add(lblMemberName);

	}

	protected void cancel_Click() {
		MainMenuView mm = new MainMenuView(this.loginuser);
		mm.setVisible(true);
		this.dispose();
	}

	protected void checkout() {
		try {
			if (member == null)
				throw new LibrarySystemException("Add Library Member");
			if (bookCopyList.size() == 0)
				throw new LibrarySystemException("Add at least one Book");

			CheckOutBookController cob = new CheckOutBookController();

			List<BookCopy> myBookCopy = new ArrayList<BookCopy>();
			this.bookCopyList.forEach((x, v) -> {
				myBookCopy.add(v);
			});
			member = cob.checkOut(member, myBookCopy);

			JOptionPane.showMessageDialog(this, "Checkout Successfully");
			MainMenuView mmv = new MainMenuView(this.loginuser);
			mmv.setVisible(true);
			this.dispose();

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		}
	}

	protected void btnAddBook_click() {
		CheckOutBook_AddBookView cob_abv = new CheckOutBook_AddBookView(this);
		this.hide();
		cob_abv.setVisible(true);
	}

	protected void checkMember() {

		try {
			if (txtMemberID.getText().trim().equals(""))
				throw new LibrarySystemException("Enter Valid Memeber ID");

			CheckOutBookController cob = new CheckOutBookController();
			member = cob.checkMember(txtMemberID.getText());

			txtMemberID.setEnabled(false);
			btnSearchMember.setEnabled(false);
			lblMemberName.setText(member.getFirstName() + " " + member.getLastName());

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		}
	}

	protected void showBook() {
		DefaultListModel tempModel = (DefaultListModel) jlstBooklist.getModel();
		tempModel.clear();
		
		this.bookCopyList.forEach((k,v)->{
			tempModel.addElement(v.getBook().getIsbnNumber() + " - " + v.getBook().getTitle() + "CopyNumber :"
					+ v.getUniqueCopyNumber());
		});
			
	};

	public void addBook(Book b) {
		try {
			CheckOutBookController cob = new CheckOutBookController();
			BookCopy bc = cob.isBookAvailable(b);
			if (bc == null)
				throw new LibrarySystemException("Book is not available");
			this.bookCopyList.put(bc.getBook().getIsbnNumber() + bc.getUniqueCopyNumber(), bc);
			showBook();
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
