package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dades.ClientsSQL;
import Dades.E_SSQL;
import Model.Client;
import Model.E_S;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UserView extends JDialog {
	
	private static DefaultTableModel model;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	JComboBox comboBox = new JComboBox();
	static Client cli;
	static E_SSQL conE_S = new E_SSQL();
	static ClientsSQL conCli = new ClientsSQL();
	
	public UserView(Client cli) {
		
		this.cli = cli;
		
		setBounds(100, 100, 559, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		//Scroll Panel para tabla 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(161, 59, 372, 79);
		contentPanel.add(scrollPane);
		
		//----CREA TAULA MOVIMENT USUARI
		table = new JTable();
		
		//----IMPIDE EDITAR LAS CASILLAS		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				//Todas las celdas en false
				return false;
			}
		};
		
		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
			
		model.addColumn("Data");
		model.addColumn("Gimnas");
		model.addColumn("Entrada o salida");
				
		table.setModel(model);
		
		updateTable();
		
		scrollPane.setViewportView(table);
		
		
		lastText();
		eleccionGym();
		
		btnFichar();
		btnPagar();

		
		panelInferior();
		
		
	}//------------------------------------------------------------UserView()-------------------------------------------------------------------
	
	
	//-------------------------------------------------------------FUNCIONES TABLA 
	public static void updateTable(){ //---Informacion tablas
		
		try {
			
			model.setRowCount(0);
								
			//----RELLENA TABLA
			E_S e_s = new E_S(conE_S.consultaUltimMovimentClient(cli)); 
			
			model.addRow(new Object[] {
				
					e_s.getData(),
					e_s.getGimnas(),
					e_s.getTipus()
			});	
						
			
		} catch (Exception e) {
			
		}
		
	}//updateTable()--------	
	
	
	public void lastText() {
		
		JLabel lblUltimRegistre = new JLabel("Ultimo Registro");
		lblUltimRegistre.setBounds(171, 33, 230, 15);
		contentPanel.add(lblUltimRegistre);
	}
	
	
	public void eleccionGym() {
	
		//CAJA DE ELECCION GIMNASIO
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"GS Santpedor", "GS Manresa", "GS Barcelona", "GS SantVi"}));
		comboBox.setBounds(10, 28, 131, 25);
		contentPanel.add(comboBox);
	}
	
	//-------------------------------------------------------------BOTONES
	
	public void btnFichar() {
		JButton btnFichar = new JButton("Fichar ");
		btnFichar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				E_S mov = new E_S(cli.getDni());
				
				try {
					
					E_S aux = conE_S.consultaUltimMovimentClient(cli);

					
					if(aux.getTipus().equals("S")) {
						
						mov = new E_S(cli.getDni(),comboBox.getSelectedItem().toString(),"E");
			
					} else if(aux.getTipus().equals("E")) {
						
						mov = new E_S(cli.getDni(),comboBox.getSelectedItem().toString(),"S");
						
					} 
						
					conE_S.insertaMoviment(mov);
					updateTable();
					
				} catch (Exception e) {
									
					try {
						
						mov = new E_S(cli.getDni(),comboBox.getSelectedItem().toString(),"E");
						
						conE_S.insertaMoviment(mov);
						updateTable();
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
						
					}				
				}
			}
			
		});
		btnFichar.setBounds(10, 77, 131, 25);
		contentPanel.add(btnFichar);
	}
	
	public void btnPagar() {
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					cli.setDeutor("false");
					conCli.modificaClients(cli);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
		

		btnPagar.setBounds(10, 113, 131, 25);
		contentPanel.add(btnPagar);
		
		{
			JButton okButton = new JButton("Ver Registros");
			okButton.setBounds(373, 29, 160, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					MovimentUser_View window  = new MovimentUser_View(cli);								
												
					window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					window.setVisible(true);
					dispose();
				}
			});
			
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel lblNewLabel = new JLabel("Gimnasio:");
			lblNewLabel.setBounds(10, 11, 71, 14);
			contentPanel.add(lblNewLabel);
		}
		
		}
	
	
	public void panelInferior() {
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton cancelButton = new JButton("Cerrar Sesion");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
	}
	
}
