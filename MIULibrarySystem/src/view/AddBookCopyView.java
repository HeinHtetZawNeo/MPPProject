package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AddBookController;
import controller.BookCopyController;
import exception.LibrarySystemException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookCopyView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField BookISBN;
	private JTextField Quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookCopyView frame = new AddBookCopyView();
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
	public AddBookCopyView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add a Book Copy");
		lblNewLabel.setBounds(247, 36, 188, 14);
		contentPane.add(lblNewLabel);

		BookISBN = new JTextField();
		BookISBN.setBounds(209, 103, 167, 20);
		contentPane.add(BookISBN);
		BookISBN.setColumns(10);

		Quantity = new JTextField();
		Quantity.setBounds(207, 152, 169, 20);
		contentPane.add(Quantity);
		Quantity.setColumns(10);

		JButton SaveBookCopy = new JButton("Save");
		SaveBookCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Click();
			}
		});
		SaveBookCopy.setBackground(new Color(0, 255, 0));
		SaveBookCopy.setBounds(186, 206, 89, 23);
		contentPane.add(SaveBookCopy);

		JLabel lblNewLabel_1 = new JLabel("Book ISBN");
		lblNewLabel_1.setBounds(105, 106, 76, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(115, 155, 49, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(289, 206, 109, 23);
		contentPane.add(btnNewButton);
	}

	protected void save_Click() {
		try {
			if (BookISBN.getText().isBlank())
				throw new LibrarySystemException("Enter ISBN Number");
			if (Quantity.getText().isBlank())
				throw new LibrarySystemException("Enter Qty");
			if (!Quantity.getText().matches("-?\\d+(\\.\\d+)?"))
				throw new LibrarySystemException("Enter Valid Qty");

			BookCopyController bookcopy = new BookCopyController();

			bookcopy.addbookcopy(BookISBN.getText(), Integer.parseInt(Quantity.getText()));
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
}
