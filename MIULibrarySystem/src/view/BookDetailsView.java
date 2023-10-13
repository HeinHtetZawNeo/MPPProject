package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import exception.LibrarySystemException;
import model.Book;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BookDetailsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblTitle;
	private JLabel lblIsbn;
	private JLabel lblBorrowDay;
	private JLabel lblQty;
	private JButton btnOK;
	private JFrame previousFrame;
	private String isbn;
	private JLabel lblNewLabel_5;
	private JLabel lblAvailable;
	private JLabel lblNewLabel_6;
	private JLabel lblInCirculation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetailsView frame = new BookDetailsView(null, "isbn");
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
	public BookDetailsView(JFrame previousFrame, String isbn) {
		this.previousFrame = previousFrame;
		this.isbn = isbn;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblNewLabel = new JLabel("Book Detail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 400, 20);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(10, 40, 150, 20);
		contentPane.add(lblNewLabel_1);

		lblTitle = new JLabel("");
		lblTitle.setBounds(170, 40, 150, 20);
		contentPane.add(lblTitle);

		lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setBounds(10, 70, 150, 20);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Borrow Days");
		lblNewLabel_3.setBounds(10, 100, 150, 20);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(10, 130, 150, 20);
		contentPane.add(lblNewLabel_4);

		lblIsbn = new JLabel("");
		lblIsbn.setBounds(170, 70, 150, 20);
		contentPane.add(lblIsbn);

		lblBorrowDay = new JLabel("");
		lblBorrowDay.setBounds(170, 100, 150, 20);
		contentPane.add(lblBorrowDay);

		lblQty = new JLabel("");
		lblQty.setBounds(170, 130, 150, 20);
		contentPane.add(lblQty);

		btnOK = new JButton("OK");
		btnOK.setBounds(120, 520, 150, 30);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_click();
			}
		});
		contentPane.add(btnOK);
		
		lblNewLabel_5 = new JLabel("Available");
		lblNewLabel_5.setBounds(10, 160, 150, 20);
		contentPane.add(lblNewLabel_5);
		
		lblAvailable = new JLabel("");
		lblAvailable.setBounds(170, 160, 150, 20);
		contentPane.add(lblAvailable);
		
		lblNewLabel_6 = new JLabel("In Circulation");
		lblNewLabel_6.setBounds(10, 190, 150, 20);
		contentPane.add(lblNewLabel_6);
		
		lblInCirculation = new JLabel("");
		lblInCirculation.setBounds(170, 190, 150, 20);
		contentPane.add(lblInCirculation);

		loadData();
	}

	protected void btnOK_click() {
		previousFrame.setVisible(true);
		this.dispose();
		;
	}

	private void loadData() {
		try {
			ShowAllController sac = new ShowAllController();
			Book b = sac.getBook(this.isbn);
			if (b == null)
				throw new LibrarySystemException("Something went wrong");

			lblTitle.setText(b.getTitle());
			lblIsbn.setText(b.getIsbnNumber());
			lblBorrowDay.setText(b.getBorrowDay() + "");
			lblQty.setText(b.getBookCopyList().size() + "");
			lblAvailable.setText(sac.isAvailable(b, "Available"));
			lblInCirculation.setText(sac.isAvailable(b, "NotAvailable"));

		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}
}
