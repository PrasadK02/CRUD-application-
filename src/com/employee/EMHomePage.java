package com.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.employee.db.DBConnection;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class EMHomePage extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField FNField;
	private JTextField LNField;
	private JTextField CompanyField;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EMHomePage frame = new EMHomePage();
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
	public EMHomePage() {
		setTitle("HomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ID field
		IDField = new JTextField();
		IDField.setHorizontalAlignment(SwingConstants.CENTER);
		IDField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		IDField.setForeground(new Color(255, 250, 250));
		IDField.setBackground(new Color(0, 0, 0));
		IDField.setBounds(143, 95, 185, 25);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Arial", Font.BOLD, 14));
		lblID.setForeground(new Color(255, 250, 250));
		lblID.setBounds(34, 94, 73, 25);
		contentPane.add(lblID);
		
		//FirstName Field
		FNField = new JTextField();
		FNField.setHorizontalAlignment(SwingConstants.CENTER);
		FNField.setForeground(new Color(255, 250, 250));
		FNField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		FNField.setColumns(10);
		FNField.setBackground(new Color(0, 0, 0));
		FNField.setBounds(143, 152, 185, 25);
		contentPane.add(FNField);
		
		//LastName Field
		LNField = new JTextField();
		LNField.setHorizontalAlignment(SwingConstants.CENTER);
		LNField.setForeground(new Color(255, 250, 250));
		LNField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		LNField.setColumns(10);
		LNField.setBackground(new Color(0, 0, 0));
		LNField.setBounds(143, 209, 185, 25);
		contentPane.add(LNField);
		
		//Company Field
		CompanyField = new JTextField();
		CompanyField.setHorizontalAlignment(SwingConstants.CENTER);
		CompanyField.setForeground(new Color(255, 250, 250));
		CompanyField.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		CompanyField.setColumns(10);
		CompanyField.setBackground(new Color(0, 0, 0));
		CompanyField.setBounds(143, 266, 185, 25);
		contentPane.add(CompanyField);
		
		JLabel lblFN = new JLabel("First Name");
		lblFN.setForeground(new Color(255, 250, 250));
		lblFN.setFont(new Font("Arial", Font.BOLD, 14));
		lblFN.setBounds(34, 151, 86, 25);
		contentPane.add(lblFN);
		
		JLabel lblLN = new JLabel("Last Name");
		lblLN.setForeground(new Color(255, 250, 250));
		lblLN.setFont(new Font("Arial", Font.BOLD, 14));
		lblLN.setBounds(34, 208, 86, 25);
		contentPane.add(lblLN);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setForeground(new Color(255, 250, 250));
		lblCompany.setFont(new Font("Arial", Font.BOLD, 14));
		lblCompany.setBounds(34, 265, 73, 25);
		contentPane.add(lblCompany);
		
		//Syntax for Insert Button
		JButton btnInsert = new JButton("Insert");
		btnInsert.setForeground(new Color(255, 250, 250));
		btnInsert.setToolTipText("Insert Details");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q = "insert into emp values(?,?,?,?)";
				
				try {
					PreparedStatement ps = DBConnection.dbConn().prepareStatement(q);
					ps.setInt(1, Integer.parseInt(IDField.getText()) );
					ps.setString(2, FNField.getText());
					ps.setString(3, LNField.getText());
					ps.setString(4, CompanyField.getText());
					
					ps.execute();
					JOptionPane.showMessageDialog(null, "Inserted Successfully!!!");
					readDataFromDb();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBackground(new Color(0, 0, 0));
		btnInsert.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnInsert.setBounds(71, 335, 85, 21);
		contentPane.add(btnInsert);
		
		
		//Syntax for Delete Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 250, 250));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String q = " delete from emp where id=? ";
				
				try {
					PreparedStatement ps = DBConnection.dbConn().prepareStatement(q);
					ps.setInt(1, Integer.parseInt(IDField.getText()));
					ps.execute();
					
					JOptionPane.showMessageDialog(btnDelete, "Deleted Successfully!!!");
					readDataFromDb();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setToolTipText("Delete Details");
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.setBackground(new Color(0, 0, 0));
		btnDelete.setBounds(71, 380, 85, 21);
		contentPane.add(btnDelete);
		
		//Syntax for Update Button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 250, 250));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String q = "update emp set first_name=? , last_name = ?, company = ? where id = ?"; 
				
				try {
					PreparedStatement ps = DBConnection.dbConn().prepareStatement(q);
					ps.setString(1, FNField.getText());
					ps.setString(2, LNField.getText());
					ps.setString(3, CompanyField.getText());
					
					ps.setInt(4, Integer.parseInt(IDField.getText()));
					
					ps.execute();
					
					JOptionPane.showMessageDialog(btnUpdate, "Updated Successfully!!!");
					readDataFromDb();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setToolTipText("Modify Details");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnUpdate.setBackground(new Color(0, 0, 0));
		btnUpdate.setBounds(243, 335, 85, 21);
		contentPane.add(btnUpdate);
		
		//Syntax for Load Button
		JButton btnLoad = new JButton("Load");
		btnLoad.setForeground(new Color(255, 250, 250));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readDataFromDb();
			}
		});
		btnLoad.setToolTipText("Load Existing Details");
		btnLoad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLoad.setBackground(new Color(0, 0, 0));
		btnLoad.setBounds(243, 380, 85, 21);
		contentPane.add(btnLoad);
		
		//Table
		table = new JTable();
		table.setForeground(new Color(255, 250, 250));
		table.setFont(new Font("Tahoma", Font.BOLD, 10));
		table.setBackground(new Color(0, 0, 0));
		table.setBounds(439, 55, 337, 398);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setForeground(new Color(255, 250, 250));
		table_1.setFont(new Font("Serif", Font.BOLD, 12));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "First Name", "Last Name", "Company"},
			},
			new String[] {
				"ID", "First Name", "Last Name", "Company"
			}
		));
		table_1.setBackground(new Color(0, 0, 0));
		table_1.setBounds(439, 39, 337, 16);
		contentPane.add(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(new Color(0, 0, 0));
		lblNewLabel_1.setForeground(new Color(255, 250, 250));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\image-786x463 (1).jpg"));
		lblNewLabel_1.setBounds(0, 0, 786, 463);
		contentPane.add(lblNewLabel_1);
		
		
	}

	//Method to read data from database which is used in insert, update, load and delete buttons
	private void readDataFromDb() {
			String q = "Select * from emp";
			
			try {
				Statement stmt = DBConnection.dbConn().createStatement();
				ResultSet rs = stmt.executeQuery(q);
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
}
