package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AddBookController;
import exception.LibrarySystemException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;

import model.Address;
import model.Author;
import model.Book;
import model.LoginUser;

public class AddBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_3;
	private JTextField txtTitle;
	private JTextField txtISBN;
	private JTextField txtQuantity;
	private JList jListAuthor;
	private JComboBox cmbBorrowDay;
	private List<Author> authors;
	private LoginUser loginuser;

	{
		authors = new ArrayList<Author>();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookView frame = new AddBookView(null);
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
	public AddBookView(LoginUser loginuser) {
		this.loginuser = loginuser;
		setTitle("Add New Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 375);

		setLocationRelativeTo(null);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add New Book");
		lblNewLabel.setBounds(220, 10, 200, 20);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBounds(10, 40, 200, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ISBN Number");
		lblNewLabel_2.setBounds(10, 70, 200, 20);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("No Of Borrow Days");
		lblNewLabel_3.setBounds(10, 100, 200, 20);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(10, 130, 200, 20);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Author");
		lblNewLabel_5.setBounds(10, 160, 200, 20);
		contentPane.add(lblNewLabel_5);

		txtTitle = new JTextField();
		txtTitle.setBounds(220, 40, 300, 20);
		contentPane.add(txtTitle);

		txtISBN = new JTextField();
		txtISBN.setBounds(220, 70, 300, 20);
		contentPane.add(txtISBN);

		cmbBorrowDay = new JComboBox();
		cmbBorrowDay.setModel(new DefaultComboBoxModel(new String[] { "21", "7" }));
		cmbBorrowDay.setBounds(220, 100, 300, 20);
		contentPane.add(cmbBorrowDay);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(220, 130, 300, 20);
		contentPane.add(txtQuantity);

		jListAuthor = new JList(new DefaultListModel<String>());
		jListAuthor.setBounds(220, 160, 300, 150);
		contentPane.add(jListAuthor);

		JButton AddAuthor = new JButton("Add Author");
		AddAuthor.setBounds(530, 160, 100, 20);
		AddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAuthor_Click();
			}
		});
		contentPane.add(AddAuthor);

		JButton Save = new JButton("Save");
		Save.setBounds(150, 320, 100, 20);
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_Click();
			}
		});
		contentPane.add(Save);

		JButton Cancel = new JButton("Cancel");
		Cancel.setBounds(350, 320, 100, 20);
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_Click();
			}
		});
		contentPane.add(Cancel);

	}

	protected void save_Click() {
		try {
			if (txtTitle.getText().isBlank())
				throw new LibrarySystemException("Enter Title");
			if (txtISBN.getText().isBlank())
				throw new LibrarySystemException("Enter ISBN Number");
			if (authors.size() == 0)
				throw new LibrarySystemException("Add at least one author");
			if (txtQuantity.getText().isBlank())
				throw new LibrarySystemException("Enter Quantity");
			if (!txtQuantity.getText().matches("-?\\d+(\\.\\d+)?"))
				throw new LibrarySystemException("Enter Quantity with valid input");

			Book book = new Book(Integer.parseInt(cmbBorrowDay.getSelectedItem() + ""), txtTitle.getText(),
					txtISBN.getText(), authors, Integer.parseInt(txtQuantity.getText()));

			AddBookController abc = new AddBookController();
			abc.addbook(book);
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	protected void cancel_Click() {
		MainMenuView mmv = new MainMenuView(this.loginuser);
		mmv.setVisible(true);
		this.dispose();
	}

	protected void ShowAuthors() {
		DefaultListModel tempModel = (DefaultListModel) jListAuthor.getModel();
		tempModel.clear();
		for (Author a : authors) {
			tempModel.addElement(a.getFirstName() + " " + a.getLastName());
		}
	}

	protected void AddAuthor_Click() {
		AddAuthorView addAuthor = new AddAuthorView(this);
		addAuthor.setVisible(true);
		this.setVisible(false);
	}

	public void addAuthor(Author a) {
		authors.add(a);
	}
}
