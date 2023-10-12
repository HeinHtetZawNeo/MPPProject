package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class AddBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Title;
	private JLabel lblNewLabel_2;
	private JTextField ISBN;
	private JLabel lblNewLabel_3;
	private JTextField Quantity;
	private JList Author;
	private List<Author> authors;
	private Book book1; //a of books  Hasan
	{
		authors = new ArrayList<Author>();
	}
	public void addAuthor(Author a) {
		authors.add(a);
	}
	

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
		setBounds(100, 100, 609, 471);

		contentPane = new JPanel();

		setLocationRelativeTo(null);
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
		Title.setHorizontalAlignment(SwingConstants.LEFT);
		Title.setBounds(147, 67, 209, 20);
		Title.setToolTipText("Title");
		contentPane.add(Title);
		Title.setColumns(10);
		
		JComboBox selectl = new JComboBox();
		selectl.setBackground(new Color(255, 255, 255));
		selectl.setModel(new DefaultComboBoxModel(new String[] {"21", "7"}));
		selectl.setBounds(147, 134, 63, 22);
		contentPane.add(selectl);

		lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setBounds(103, 101, 49, 14);
		contentPane.add(lblNewLabel_2);

		ISBN = new JTextField();
		ISBN.setToolTipText("ISBN");
		ISBN.setBounds(147, 95, 209, 20);
		contentPane.add(ISBN);
		ISBN.setColumns(10);

		lblNewLabel_3 = new JLabel("#OfBorrowDays");
		lblNewLabel_3.setBounds(44, 138, 96, 14);
		contentPane.add(lblNewLabel_3);

		Quantity = new JTextField();
		Quantity.setToolTipText("Quantity");
		Quantity.setBounds(147, 163, 209, 20);
		contentPane.add(Quantity);
		Quantity.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setBounds(77, 166, 49, 14);
		contentPane.add(lblNewLabel_4);

		Author = new JList(new DefaultListModel<String>());
		/*
		 * Author.setModel(new AbstractListModel() {
		 * 
		 * String[] values = new String[] {};
		 * 
		 * public int getSize() { return values.length; }
		 * 
		 * public Object getElementAt(int index) { return values[index]; } });
		 */
		Author.setToolTipText("Author");
		Author.setBounds(147, 207, 233, 74);
		contentPane.add(Author);

		JLabel lblNewLabel_5 = new JLabel("Author");
		lblNewLabel_5.setBounds(77, 241, 49, 14);
		contentPane.add(lblNewLabel_5);

		JButton AddAuthor = new JButton("Add Author");
		AddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAuthor();
			}
		});
		AddAuthor.setBackground(new Color(64, 128, 128));
		AddAuthor.setForeground(new Color(0, 0, 0));
		AddAuthor.setBounds(378, 181, 111, 23);
		contentPane.add(AddAuthor);

		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		if(Title.getText().isEmpty() || ISBN.getText().isEmpty() || authors.isEmpty()  )
			JOptionPane.showMessageDialog(null, "All Fields are Reuqired Please...", "alert", JOptionPane.ERROR_MESSAGE);
		else
		{
				
			 book1 = new Book(Integer.valueOf( (String) selectl.getSelectedItem()),Title.getText(),ISBN.getText(),authors, Integer.valueOf(Quantity.getText()));
				
			 System.out.print(book1.toString());
			
			dispose();
		}
			//	abv.setVisible(true);
			}
		});
		Save.setForeground(new Color(0, 128, 0));
		Save.setToolTipText("Save");
		Save.setBounds(121, 309, 89, 23);
		contentPane.add(Save);

		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				System.exit(0);
			}
		});
		Cancel.setForeground(new Color(255, 0, 0));
		Cancel.setBounds(307, 309, 89, 23);
		contentPane.add(Cancel);
		
		
	}

	protected void ShowAuthors()
	{
		DefaultListModel tempModel = (DefaultListModel)Author.getModel();
		tempModel.clear();
		for(Author a: authors) {
			System.out.println(a.getFirstName());
			System.out.println(a.getLastName());
			System.out.println(a.getPhoneNumber());
			System.out.println( "Expert= "+ a.isCredentials());
			System.out.println("==");
			tempModel.addElement(a.getFirstName()+" "+a.getLastName());
		}
		
				//tempModel.addElement(a.getFirstName()+" "+a.getLastName());
	}

	protected void AddAuthor() {
		AddAuthorView addAuthor = new AddAuthorView(this);
		addAuthor.setVisible(true);
		
		//AddAuthorView autherob1= new AddAuthorView();
		
		//DefaultListModel tempModel = (DefaultListModel) Author.getModel();
		//tempModel.addElement("Hello 1");
	}
}
