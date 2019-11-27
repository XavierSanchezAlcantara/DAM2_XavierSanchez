package VISTAS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DADES.SQLClients;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import java.awt.Dialog;

public class frmGestioClients extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmGestioClients dialog = new frmGestioClients();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.taulas();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.3
	 */
	public frmGestioClients() {
		System.out.println("pene");

		setBounds(100, 100, 757, 480);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(48, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)
						.addGap(75)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap(124, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
								.addGap(63)));
		table = new JTable();
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JButton btnDelete = new JButton("DELETE");
			btnDelete.setActionCommand("OK");

			JButton btnNew = new JButton("NEW");
			btnNew.setActionCommand("OK");

			JButton btnEdit = new JButton("EDIT");
			btnEdit.setActionCommand("OK");

			JButton btnSave = new JButton("SAVE");
			btnSave.addActionListener(new ActionListener() {
				// ACTION BOTON
				// SAVE----------------------------------------------------------------------
				public void actionPerformed(ActionEvent arg0) {
					taulas();

				}

			});
			// DATA BASE

			btnSave.setActionCommand("OK");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(1)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE).addGap(18)
							.addComponent(btnDelete).addPreferredGap(ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(5)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNew)
									.addComponent(btnEdit).addComponent(btnDelete).addComponent(btnSave))));
			buttonPane.setLayout(gl_buttonPane);
		}

	}

	private void taulas() {
		// TODO Auto-generated method stub

		SQLClients con = new SQLClients();
		Connection conexion = con.conectar();

		String sql = "SELECT * FROM Clients";
		java.sql.Statement st;

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("CIF");
		model.addColumn("NOM");
		model.addColumn("COGNOM");
		model.addColumn("ADREÇA");
		model.addColumn("CODIPOSTAL");
		model.addColumn("TLF");

		table.setModel(model);

		String[] dato = new String[6];

		try {
			st = conexion.createStatement();

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				System.out.println(result.getString(1) + "hola");
				dato[0] = result.getString(1);
				dato[1] = result.getString(2);
				dato[2] = result.getString(3);
				dato[3] = result.getString(4);
				dato[4] = result.getString(5);
				dato[5] = result.getString(6);

				model.addRow(dato);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
