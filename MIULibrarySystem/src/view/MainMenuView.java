package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
	private JButton checkOutButton;
	private JButton addMemberButton;
	private JButton addBookButton;
	private JButton addBookCopyButton;
	private JButton logoutButton;
	private JButton showAllBookButton;
	private JButton showAllMemberButton;
	private LoginUser loginUser;

	public MainMenuView(LoginUser loginUser) {
		this.loginUser = loginUser;
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null); // Center the frame on the screen
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns with 10-pixel gaps
		// Create buttons

		addMemberButton = new JButton("Add Member");
		checkOutButton = new JButton("Check Out Book");
		addBookButton = new JButton("Add Book");
		addBookCopyButton = new JButton("Add Book Copy");
		logoutButton = new JButton("Log Out");
		showAllBookButton = new JButton("Show All Book");
		showAllMemberButton = new JButton("Show All Member");
		displayButtonsWithRole();
		// Add action listeners to the buttons
		addMemberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle the ADD MEMBER button click
				addMember_Click();
			}
		});

		checkOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle the CHECK OUT BOOK button click
				checkOut_Click();
			}
		});

		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle the ADD BOOK button click
				addBook_Click();
			}
		});

		addBookCopyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle the ADD BOOK COPY button click
				addBookCopy_Click();
			}
		});

		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle the Log Out button click
				// JOptionPane.showMessageDialog(MainMenu.this, "Log Out clicked");
				dispose();
				LoginView loginAgain = new LoginView();
				loginAgain.setVisible(true);

			}
		});

		showAllBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showAllBookButton_Click();
			}
		});
		showAllMemberButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showAllMemberButton_Click();
			}
		});
		// Add buttons to the panel
		panel.add(addMemberButton);
		panel.add(checkOutButton);
		panel.add(addBookButton);
		panel.add(addBookCopyButton);
		panel.add(showAllBookButton);
		panel.add(showAllMemberButton);
		setVisible(true);
		// Create another panel for the Log Out button
		JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		logoutPanel.add(logoutButton);

		// Add panels to the frame
		add(panel, BorderLayout.CENTER);
		add(logoutPanel, BorderLayout.SOUTH);
	}

	protected void showAllMemberButton_Click() {
		ShowAllMemberView sam = new ShowAllMemberView(loginUser);
		sam.setVisible(true);
		this.dispose();
	}

	protected void showAllBookButton_Click() {
		ShowAllBookView sab = new ShowAllBookView(loginUser);
		sab.setVisible(true);
		this.dispose();
	}

	private void displayButtonsWithRole() {
		if (this.loginUser instanceof SuperUser) {
		} else if (loginUser instanceof Admin) {
			checkOutButton.setEnabled(false);
		} else if (this.loginUser instanceof Librarian) {
			addMemberButton.setEnabled(false);
			addBookButton.setEnabled(false);
			addBookCopyButton.setEnabled(false);
		} else {
			checkOutButton.setEnabled(false);
			addMemberButton.setEnabled(false);
			addBookButton.setEnabled(false);
			addBookCopyButton.setEnabled(false);
		}
	}

	protected void addBookCopy_Click() {
		AddBookCopyView abcv = new AddBookCopyView(this.loginUser);
		abcv.setVisible(true);
		this.dispose();
	}

	protected void addBook_Click() {
		AddBookView abv = new AddBookView(this.loginUser);
		abv.setVisible(true);
		this.dispose();
	}

	protected void checkOut_Click() {
		CheckOutBookView cob = new CheckOutBookView(this.loginUser);
		cob.setVisible(true);
		this.dispose();
	}

	protected void addMember_Click() {
		AddMemberView amv = new AddMemberView(loginUser);
		amv.setVisible(true);
		this.dispose();
	}
}
