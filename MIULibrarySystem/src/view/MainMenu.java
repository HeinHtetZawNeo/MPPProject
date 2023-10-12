package view;

import javax.swing.*;

import model.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
        });
    }
    
    public void displayMenu(LoginUser loginUser) {
    	 JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns with 10-pixel gaps

         // Create buttons
    	
    	 JButton addMemberButton = new JButton("ADD MEMBER");
         JButton checkOutButton = new JButton("CHECK OUT BOOK");
         JButton addBookButton = new JButton("ADD BOOK");
         JButton addBookCopyButton = new JButton("ADD BOOK COPY");
         JButton logoutButton = new JButton("Log Out");

         // Add action listeners to the buttons
         addMemberButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Handle the ADD MEMBER button click
                 JOptionPane.showMessageDialog(MainMenu.this, "ADD MEMBER clicked");
             }
         });

         checkOutButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Handle the CHECK OUT BOOK button click
                 JOptionPane.showMessageDialog(MainMenu.this, "CHECK OUT BOOK clicked");
             }
         });

         addBookButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Handle the ADD BOOK button click
                 JOptionPane.showMessageDialog(MainMenu.this, "ADD BOOK clicked");
             }
         });

         addBookCopyButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Handle the ADD BOOK COPY button click
                 JOptionPane.showMessageDialog(MainMenu.this, "ADD BOOK COPY clicked");
             }
         });

         logoutButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Handle the Log Out button click
                 JOptionPane.showMessageDialog(MainMenu.this, "Log Out clicked");
             }
         });

         // Add buttons to the panel
         panel.add(addMemberButton);
         panel.add(checkOutButton);
         panel.add(addBookButton);
         panel.add(addBookCopyButton);


    	if(loginUser instanceof Librarian) {
    		addMemberButton.setEnabled(false);
            addMemberButton.setBackground(Color.LIGHT_GRAY);
            addBookButton.setEnabled(false);
            addBookButton.setBackground(Color.LIGHT_GRAY);
            addBookCopyButton.setEnabled(false);
            addBookCopyButton.setBackground(Color.LIGHT_GRAY);
            
    	}else if(loginUser instanceof Admin){
    		checkOutButton.setEnabled(false);
    		checkOutButton.setBackground(Color.LIGHT_GRAY);
    	}
    	
    	setVisible(true);
        // Create another panel for the Log Out button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.add(logoutButton);

        // Add panels to the frame
        add(panel, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);
    }
}

