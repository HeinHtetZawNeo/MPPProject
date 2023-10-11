package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CheckOutBookController;
import exception.LibrarySystemException;
import model.LibraryMember;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class CheckOutBookView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMemberID;
	private JList jlstBooklist;
	private JButton btnSearchMember;
	private JLabel lblMemberName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckOutBookView frame = new CheckOutBookView();
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
	public CheckOutBookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
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
				addBook();
			}
		});
		btnAddBook.setBounds(290, 99, 139, 29);
		contentPane.add(btnAddBook);

		JButton btnCheckOut = new JButton("CheckOut");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheckOut.setBounds(33, 237, 134, 29);
		contentPane.add(btnCheckOut);

		JButton btnCancel = new JButton("Cancel");
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

	protected void checkMember() {

		try {
			if (txtMemberID.getText().trim().equals(""))
				throw new LibrarySystemException("Enter Valid Memeber ID");
			
			CheckOutBookController cob = new CheckOutBookController();
			LibraryMember member = cob.checkMember(txtMemberID.getText());
			
			txtMemberID.setEnabled(false);
			btnSearchMember.setEnabled(false);
			lblMemberName.setText(member.getFirstName()+" "+member.getLastName());
			
		} catch (LibrarySystemException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			txtMemberID.setText("");
		}
	}

	protected void addBook() {
		
		DefaultListModel tempModel = (DefaultListModel) jlstBooklist.getModel();
		tempModel.addElement("Hello 1");
	}
}
