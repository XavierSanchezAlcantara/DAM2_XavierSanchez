package VISTAS;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DADES.SQLClients;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class frmGestioClients extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	Connection conexion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmGestioClients frame = new frmGestioClients();
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
	public frmGestioClients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addGap(86))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		SQLClients con=new SQLClients();
		Connection c= DriverManager.getConnection("jdbc:sqlite:/home/xavi/Muntatge");
		String sql="SELECT * FROM Clients";
		java.sql.Statement st;
		DefaultTableModel model= new DefaultTableModel();
		model.addColumn("CIF");
		model.addColumn("NOM");
		model.addColumn("COGNOM");
		model.addColumn("DIRECCIO");
		model.addColumn("BANKCODE");
		model.addColumn("TELEFON");
		table.setModel(model);
		String[] dato=new String[6];
		try {
			st=c.createStatement();
			ResultSet result=st.executeQuery(sql);
			dato[0]=result.getString(1);
			dato[1]=result.getString(2);
			dato[2]=result.getString(3);
			dato[3]=result.getString(4);
			dato[4]=result.getString(5);
			dato[5]=result.getString(6);
			model.addRow(dato);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	

}
