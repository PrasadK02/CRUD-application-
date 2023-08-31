package com.employee;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.employee.db.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class EmployeeLogin {

	private JFrame frame;
	private JTextField uf;
	private JPasswordField pf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setForeground(Color.BLACK);
		frame.setTitle("Employee Login Page");
		frame.setBounds(370, 200, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Username Field
		uf = new JTextField();
		uf.setHorizontalAlignment(SwingConstants.CENTER);
		uf.setForeground(Color.BLACK);
		uf.setFont(new Font("Arial", Font.BOLD, 11));
		uf.setBackground(Color.GRAY);
		uf.setBounds(243, 165, 266, 30);
		frame.getContentPane().add(uf);
		uf.setColumns(10);
		
		//Password Field
		pf = new JPasswordField();
		pf.setHorizontalAlignment(SwingConstants.CENTER);
		pf.setFont(new Font("Monospaced", Font.BOLD, 12));
		pf.setBackground(Color.GRAY);
		pf.setBounds(243, 237, 266, 30);
		frame.getContentPane().add(pf);
		
		JLabel lblNewLabel = new JLabel("Enter Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(243, 142, 138, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterPassword.setForeground(Color.WHITE);
		lblEnterPassword.setBounds(243, 215, 138, 21);
		frame.getContentPane().add(lblEnterPassword);
		
		//Code for Login button
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				  
				String q = "select * from empusers where UID = ? and Pw = ?";
				
				try {
					PreparedStatement ps = DBConnection.dbConn().prepareStatement(q);
					ps.setString(1, uf.getText());
					ps.setString(2, pf.getText());
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(btnLogIn, "Welcome to Employee Management!!!");
						EMHomePage frame = new EMHomePage();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(btnLogIn, "Invalid Username or Password!");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setForeground(Color.BLACK);
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLogIn.setBounds(340, 298, 79, 21);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\image-786x418.jpg"));
		lblNewLabel_1.setBounds(0, 0, 786, 413);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
