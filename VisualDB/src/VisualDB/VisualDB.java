package VisualDB;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualDB {

	private JFrame frame;
	private JTextField textname;
	private JTextField textmobile;
	private JTextField textcourse;
	//private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualDB window = new VisualDB();
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
	public VisualDB() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Object data[][] = { 
    };
    String col[] = {"Id", "Name", "Mobile", "Course"};

    final DefaultTableModel model = new DefaultTableModel(data, col);
    final JTable table = new JTable(model);
    //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    //table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 865, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lbl_name = new JLabel("name");
		
		JLabel lbl_mobile = new JLabel("mobile");
		
		JLabel lbl_cousre = new JLabel("course");
		
		textname = new JTextField();
		textname.setColumns(10);
		
		textmobile = new JTextField();
		textmobile.setColumns(10);
		
		textcourse = new JTextField();
		textcourse.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
	
		JButton btnUpdate = new JButton("Update");
		
		JButton btnDelete = new JButton("Delete");
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_name)
						.addComponent(lbl_mobile)
						.addComponent(lbl_cousre))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textcourse, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addComponent(textmobile)
						.addComponent(textname))
					.addGap(98)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(173)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnUpdate)
					.addGap(18)
					.addComponent(btnDelete)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(105)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_name)
								.addComponent(textname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_mobile)
								.addComponent(textmobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_cousre)
								.addComponent(textcourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnUpdate)
						.addComponent(btnDelete))
					.addGap(85))
		);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int iRow = table.getSelectedRow();
				textname.setText(table.getValueAt(iRow, 1).toString());
			    textmobile.setText(table.getValueAt(iRow, 2).toString());
			    textcourse.setText(table.getValueAt(iRow,3).toString());
			    
			    //// test to get Id
			    //JOptionPane.showMessageDialog(null,table.getValueAt(iRow,0).toString());
			    
				
			}
		});
		
    	btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = textname.getText();
				String mobile = textmobile.getText();
				String  course = textcourse.getText();
				
				
				Connection con;
				Statement st;
				try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linda","root","");
					st = con.createStatement();
					String query = "insert into record values (null,'"+name+"','"+mobile+"','"+course+"');";
				    st.executeUpdate(query);
				    st.close();
				    con.close();
				    
				    textname.setText("");
				    textmobile.setText("");
				    textcourse.setText("");
				    
				    textname.requestFocus();
				    tableUpdate();
				    JOptionPane.showMessageDialog(null, "Record Added");
				    
				}catch(SQLException Ex) {
					JOptionPane.showMessageDialog(null, Ex);
				}
			}
		});
    	
    	btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = textname.getText();
				String mobile = textmobile.getText();
				String  course = textcourse.getText();
				int iRow = table.getSelectedRow();
				
				if(!(iRow>-1))
				{
					JOptionPane.showMessageDialog(null, "Please select a row for update");
				}else {
					//JOptionPane.showMessageDialog(null, "Row selected");
					
					Connection con;
					Statement st;
					try {
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linda","root","");
						st = con.createStatement();
						
						String iID= (String) table.getValueAt(iRow, 0);
						String query = "delete from record where id = '"+iID+"';";
								//"update record set name ='"+name+"', mobile='"+mobile+"',course='"+course+"' where id='"+iId+"';";
						//String query = "insert into record values (null,'"+name+"','"+mobile+"','"+course+"');";		
						//UPDATE table_name SET field1 = new-value1, field2 = new-value2[WHERE Clause]
						query = "update record set name ='"+name+"', mobile='"+mobile+"',course='"+course+"'where id='"+iID+"';";
						//"update users set num_points = ? where first_name = ?";
					    st.executeUpdate(query);
					    st.close();
					    con.close();
					    
					    textname.setText("");
					    textmobile.setText("");
					    textcourse.setText("");
					    
					    textname.requestFocus();
					    tableUpdate();
					    JOptionPane.showMessageDialog(null, "Record Updated");
					    
					}catch(SQLException Ex) {
						JOptionPane.showMessageDialog(null, Ex);
					}	
					
				}
				
					
					
					
				
			}
		});
    	
    	btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int iRow = table.getSelectedRow();
				
				if(!(iRow>-1))
				{
					JOptionPane.showMessageDialog(null, "Please select a row for delete");
				}else {
					
					Connection con;
					Statement st;
					try {
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linda","root","");
						st = con.createStatement();
						
						String iID= (String) table.getValueAt(iRow, 0);
						String query = "delete from record where id = '"+iID+"';";
					    st.executeUpdate(query);
					    st.close();
					    con.close();
					    
					    textname.setText("");
					    textmobile.setText("");
					    textcourse.setText("");
					    
					    textname.requestFocus();
					    tableUpdate();
					    
					    JOptionPane.showMessageDialog(null, "Record Deleted");
					    
					}catch(SQLException Ex) {
						JOptionPane.showMessageDialog(null, Ex);
					}	
					
				}
				
				
			}
		});
		
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
		tableUpdate();
	}
	//comment
	private void tableUpdate() {
		 try {
			 Connection con;
			 Statement st = null;
			 ResultSet rs;
			 try {
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/linda","root","");
					st = con.createStatement();
				}catch(Exception Ex) {
					System.out.println(Ex);
				}
			 String id;
			 String name;
			 String mobile;
			 String course;
			 
		     String query = "select * from record";
		     rs = st.executeQuery(query);
		     
		     model.setNumRows(0); ///clear all data from table
		     
		     while (rs.next()) {
		        
		    	 id = rs.getString("id");
		    	 name = rs.getString("name");
		    	 mobile = rs.getString("mobile");
		    	 course = rs.getString("course");
		    	 Object[] newRecord = { id,name,mobile,course };
		    	 model.addRow(newRecord);
		    	 
		     }
		     
		 } catch(Exception ex) {
		     System.out.println(ex);
		 }
	 }
	
}
