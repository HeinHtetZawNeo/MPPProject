package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AddBookController;
import controller.BookCopyController;
import exception.LibrarySystemException;
import helper.Helper;
import model.Book;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookCopyView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIsbnNumber;
	private JTextField txtQty;
	private LoginUser loginUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookCopyView frame = new AddBookCopyView(null);
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
	public AddBookCopyView(LoginUser loginUser) {
		this.loginUser = loginUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Add Book Copy");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 330, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Book ISBN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 40, 150, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 70, 150, 20);
		contentPane.add(lblNewLabel_2);

		txtIsbnNumber = new JTextField();
		txtIsbnNumber.setBounds(170, 40, 150, 20);
		contentPane.add(txtIsbnNumber);

		txtQty = new JTextField();
		txtQty.setBounds(170, 70, 150, 20);
		contentPane.add(txtQty);

		JButton SaveBookCopy = new JButton("Save");
		SaveBookCopy.setBounds(30, 100, 100, 20);
		contentPane.add(SaveBookCopy);
		SaveBookCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Click();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(200, 100, 100, 20);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
		contentPane.add(btnCancel);
	}

	protected void cancel_Click() {
		MainMenuView mmv = new MainMenuView(this.loginUser);
		mmv.setVisible(true);
		this.dispose();
	}

	protected void save_Click() {
		try {
			if (txtIsbnNumber.getText().isBlank())
				throw new LibrarySystemException("Enter ISBN Number");
			if (!Helper.isISBNNumber(txtIsbnNumber.getText()))
				throw new LibrarySystemException("Enter valid ISBN Number");
			if (txtQty.getText().isBlank())
				throw new LibrarySystemException("Enter Qty");
			if (!Helper.isNumber(txtQty.getText()))
				throw new LibrarySystemException("Enter Valid Qty");

			BookCopyController bookcopy = new BookCopyController();

			Book b = bookcopy.addbookcopy(txtIsbnNumber.getText(), Integer.parseInt(txtQty.getText()));

			JOptionPane.showMessageDialog(this,
					"Now total book copy for " + b.getTitle() + " is " + b.getBookCopyList().size());
			MainMenuView mmv = new MainMenuView(this.loginUser);
			mmv.setVisible(true);
			this.dispose();

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
