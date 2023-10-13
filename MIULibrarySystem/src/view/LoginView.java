package view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import exception.LoginException;
import model.LoginUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginView frame = new LoginView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WELCOME TO MAHARISHI INTERNATIONAL UNIVERSITY LIBRARY");
		setBounds(200, 200, 650, 500);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null); // Use null layout for absolute positioning

		// Background Image Label
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setBackground(Color.PINK);
		backgroundLabel.setBounds(3, 16, 640, 262);
		ImageIcon imageIcon = new ImageIcon(LoginView.class.getResource("/view/resources/IMAGE3.jpg"));
		backgroundLabel.setIcon(imageIcon);
		contentPane.add(backgroundLabel);

		// Username Label
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		usernameLabel.setBounds(33, 304, 213, 45);
		contentPane.add(usernameLabel);

		// Username Field
		usernameField = new JTextField(20);
		usernameField.setBounds(199, 312, 221, 35);
		contentPane.add(usernameField);

		// Password Label
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		passwordLabel.setBounds(33, 358, 181, 45);
		contentPane.add(passwordLabel);

		// Password Field
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 371, 221, 35);
		contentPane.add(passwordField);

		// Enter Button
		JButton enterButton = new JButton("ENTER");
		enterButton.setFont(new Font("Arial", Font.PLAIN, 24));
		enterButton.setBounds(465, 317, 150, 50);
		contentPane.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Perform actions when the Enter button is clicked
				login_Click();

			}
		});
	}// constructor

	protected void login_Click() {
		// Perform actions when the Enter button is clicked
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		try {
			if (username.trim().equals("")) {
				throw new LoginException("Add User Name");
			} else if (String.valueOf(password).trim().equals("")) {
				throw new LoginException("Add Password");
			} else {
				LoginController controller = new LoginController();
				LoginUser loginUser = controller.verifyUsernamePassword(username, password);
				if (loginUser == null)
					throw new LoginException("Invalid Credential");
				
				MainMenuView mm = new MainMenuView(loginUser);
				mm.setVisible(true);
				this.dispose();
			}
		} catch (LoginException exc) {
			JOptionPane.showMessageDialog(this, exc.getMessage());
		} finally {
			usernameField.setText("");
			passwordField.setText("");
		}
	}
}// class
