package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class AddBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Title;
	private JLabel lblNewLabel_2;
	private JTextField ISBN;
	private JLabel lblNewLabel_3;
	private JTextField Quantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookView frame = new AddBookView();
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
	public AddBookView() {
		setTitle("Add a New Book");
		setFont(new Font("Bodoni MT", Font.PLAIN, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 380);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 128, 64));
		contentPane.setToolTipText("Add a New Book");
		contentPane.setBorder(new EmptyBorder(11, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setBackground(new Color(0, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(93, 70, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Add a Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(165, 16, 122, 14);
		contentPane.add(lblNewLabel);
		
		Title = new JTextField();
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(147, 67, 96, 20);
		Title.setToolTipText("Title");
		contentPane.add(Title);
		Title.setColumns(10);
		
		lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setBounds(103, 101, 49, 14);
		contentPane.add(lblNewLabel_2);
		
		ISBN = new JTextField();
		ISBN.setToolTipText("ISBN");
		ISBN.setBounds(147, 95, 96, 20);
		contentPane.add(ISBN);
		ISBN.setColumns(10);
		
		lblNewLabel_3 = new JLabel("#OfBorrowDays");
		lblNewLabel_3.setBounds(44, 138, 96, 14);
		contentPane.add(lblNewLabel_3);
		
		JList list = new JList();
		list.setBounds(165, 137, 1, 1);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(176, 137, 1, 1);
		contentPane.add(list_1);
		
		JRadioButton BorrowDays21 = new JRadioButton("21");
		BorrowDays21.setBounds(147, 134, 49, 23);
		contentPane.add(BorrowDays21);
		
		JRadioButton BorrowDays7 = new JRadioButton("7");
		BorrowDays7.setBounds(198, 134, 111, 23);
		contentPane.add(BorrowDays7);
		
		Quantity = new JTextField();
		Quantity.setToolTipText("Quantity");
		Quantity.setBounds(147, 163, 96, 20);
		contentPane.add(Quantity);
		Quantity.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(77, 166, 49, 14);
		contentPane.add(lblNewLabel_4);
		
		JList Author = new JList();
		Author.setToolTipText("Author");
		Author.setBounds(147, 207, 233, 74);
		contentPane.add(Author);
		
		JLabel lblNewLabel_5 = new JLabel("Author");
		lblNewLabel_5.setBounds(77, 241, 49, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton AddAuthor = new JButton("Add Author");
		AddAuthor.setBackground(new Color(128, 255, 255));
		AddAuthor.setForeground(new Color(0, 255, 0));
		AddAuthor.setBounds(378, 181, 111, 23);
		contentPane.add(AddAuthor);
		
		JButton Save = new JButton("Save");
		Save.setForeground(new Color(0, 128, 0));
		Save.setToolTipText("Save");
		Save.setBounds(121, 309, 89, 23);
		contentPane.add(Save);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.setForeground(new Color(255, 0, 0));
		Cancel.setBounds(307, 309, 89, 23);
		contentPane.add(Cancel);
	}
}
