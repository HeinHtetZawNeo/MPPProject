package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import exception.LibrarySystemException;
import model.Author;
import model.Book;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;

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
	private int y=190;
	private int x=170;
	private JLabel lblNewLabel_7;
	private JTable authorTable;
	private String[][] data;
	private Book b;

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
		ShowAllController sac = new ShowAllController();
		this.b = sac.getBook(this.isbn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 537);
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
		btnOK.setBounds(120, 460, 150, 30);
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
		lblInCirculation.setBounds(x, y, 150, 20);
		contentPane.add(lblInCirculation);
		
		lblNewLabel_7 = new JLabel("Authors");
		lblNewLabel_7.setBounds(10, 220, 150, 20);
		contentPane.add(lblNewLabel_7);
		
		String[] columnNames = { "First Name", "Last Name", "Credential Expert"};
		data = showAuthorData();
		authorTable = new JTable(data, columnNames);
		// make jtable not to editable
		authorTable.setDefaultEditor(Object.class, null);
		authorTable.setAutoCreateRowSorter(true);
		authorTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					jtableClick(target.getSelectedRow());
				}
			}
		});
		authorTable.setBounds(10, 30, 450, 300);

		JScrollPane sp = new JScrollPane(authorTable);
		sp.setLocation(10, 250);
		sp.setSize(400, 200);
		contentPane.add(sp);

		loadData();
	}

	private String[][] showAuthorData() {
		String[][] result = new String[b.getAuthorList().size()][3];
		int i=0;
		for(Author a: b.getAuthorList()) {
			result[i][0]=a.getFirstName();
			result[i][1]=a.getLastName();
			if(a.isCredentials())
				result[i][2]="Yes";
			else
				result[i][2]="No";
		}
		return result;
	}

	protected void jtableClick(int selectedRow) {
		AuthorDetailView adv = new AuthorDetailView(this, b.getAuthorList().get(selectedRow));
		adv.setVisible(true);
		this.setVisible(false);
	}

	protected void btnOK_click() {
		previousFrame.setVisible(true);
		this.dispose();
	}

	private void loadData() {
		try {
			if (b == null)
				throw new LibrarySystemException("Something went wrong");
			ShowAllController sac = new ShowAllController();
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
