package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private JButton btnDeleteBook;

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
		setBounds(100, 100, 670, 300);
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
		lblMemberId.setBounds(118, 50, 119, 16);
		contentPane.add(lblMemberId);

		txtMemberID = new JTextField();
		txtMemberID.setBounds(248, 45, 222, 26);
		contentPane.add(txtMemberID);
		txtMemberID.setColumns(10);

		btnSearchMember = new JButton("Search Member");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkMember();
			}
		});
		btnSearchMember.setBounds(499, 45, 139, 29);
		contentPane.add(btnSearchMember);

		jlstBooklist = new JList(new DefaultListModel<String>());
		jlstBooklist.setBounds(33, 104, 461, 119);
		jlstBooklist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnDeleteBook.setEnabled(true);
			}
		});
		contentPane.add(jlstBooklist);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddBook_click();
			}
		});
		btnAddBook.setBounds(502, 98, 139, 29);
		contentPane.add(btnAddBook);

		JButton btnCheckOut = new JButton("CheckOut");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkout();
			}
		});
		btnCheckOut.setBounds(124, 237, 134, 29);
		contentPane.add(btnCheckOut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
		btnCancel.setBounds(406, 237, 134, 29);
		contentPane.add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 228, 658, 12);
		contentPane.add(separator);

		btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setBounds(499, 133, 139, 29);
		btnDeleteBook.setEnabled(false);
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteBook_Click();
			}
		});
		contentPane.add(btnDeleteBook);

		JLabel lblMemberNameLabel = new JLabel("Member Name");
		lblMemberNameLabel.setBounds(33, 78, 90, 16);
		contentPane.add(lblMemberNameLabel);

		lblMemberName = new JLabel("");
		lblMemberName.setBounds(130, 78, 299, 16);
		contentPane.add(lblMemberName);

	}

	protected void btnDeleteBook_Click() {
		String selectedValue = jlstBooklist.getSelectedValue()+"";
		String isbn = selectedValue.substring(0, selectedValue.indexOf('~')).trim();
		BookCopy b = this.bookCopyList.get(isbn.trim());
		
		int option = JOptionPane.showConfirmDialog(this, "Delete "+b.getBook().getTitle()+"?");
		if(option==0) {
			this.bookCopyList.remove(isbn);
			showBook();
		}
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

			CheckOutBookController cobv = new CheckOutBookController();

			List<BookCopy> myBookCopy = new ArrayList<BookCopy>();
			this.bookCopyList.forEach((x, v) -> {
				myBookCopy.add(v);
			});
			member = cobv.checkOut(member, myBookCopy);

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

		if(this.bookCopyList.isEmpty()) {
			btnDeleteBook.setEnabled(false);
		}else {
			this.bookCopyList.forEach((k, v) -> {
				tempModel.addElement(v.getBook().getIsbnNumber() + " ~ " + v.getBook().getTitle() + "CopyNumber :"
						+ v.getUniqueCopyNumber());
			});			
		}
	};

	public void addBook(Book b) {
		try {
			CheckOutBookController cob = new CheckOutBookController();
			BookCopy bc = cob.isBookAvailable(b);
			if (bc == null)
				throw new LibrarySystemException("Book is not available");
			this.bookCopyList.put(bc.getBook().getIsbnNumber(), bc);
			showBook();
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
