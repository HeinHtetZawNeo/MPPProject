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
import javax.swing.SwingConstants;

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
		setBounds(200, 200, 640, 427);

		contentPane = new JPanel();
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBounds(170, 100, 300, 200);
				panel.setBackground(Color.WHITE);
				contentPane.add(panel);
						panel.setLayout(null);
										
												// Username Label
												JLabel usernameLabel = new JLabel("Username");
												usernameLabel.setBounds(20, 70, 100, 16);
												panel.add(usernameLabel);
												usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
								
										// Username Field
										usernameField = new JTextField(20);
										usernameField.setBounds(120, 70, 150, 20);
										panel.add(usernameField);
								
										// Password Label
										JLabel passwordLabel = new JLabel("Password");
										passwordLabel.setBounds(20, 100, 100, 16);
										panel.add(passwordLabel);
										passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
						
								// Password Field
								passwordField = new JPasswordField();
								passwordField.setBounds(120, 100, 150, 20);
								panel.add(passwordField);
														
																// Enter Button
																JButton enterButton = new JButton("Login");
																enterButton.setBounds(100, 150, 100, 29);
																panel.add(enterButton);
																
																JLabel lblNewLabel = new JLabel("Log In");
																lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
																lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
																lblNewLabel.setBounds(0, 42, 300, 16);
																panel.add(lblNewLabel);
						
								enterButton.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										// Perform actions when the Enter button is clicked
										login_Click();
						
									}
								});
		
				// Background Image Label
				JLabel backgroundLabel = new JLabel();
				backgroundLabel.setBounds(0, 0, 650, 400);
				backgroundLabel.setIcon(new ImageIcon("/Users/heinhtetzaw/Documents/MIU Resources/3. MPP/Git/MPPProject/MIULibrarySystem/rsc/IMAGE3.jpg"));
				contentPane.add(backgroundLabel);
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
