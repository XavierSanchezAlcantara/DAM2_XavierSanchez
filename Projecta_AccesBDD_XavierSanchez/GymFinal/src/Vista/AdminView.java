package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Dades.ClientsSQL;
import Model.Client;
import Model.E_S;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	
	private JTextField txtDni;
	private JTextField txtNom;
	private JTextField txtCognoms;
	private JTextField txtTelf;
	private JTextField txtAdresa;
	private JTextField txtCorreu;
	private JTextField txtPassword;
	private JTextField textBuscador;
	JComboBox rolBox = new JComboBox();
	JComboBox deutorBox = new JComboBox();
	
	JRadioButton rdbtnDeutor;
	JRadioButton rdbtnAdmin;
	JRadioButton rdbtnTodos;
	
	JButton btnModifica;
	JButton btnElimina;
	JButton btnNuevo;
	JButton btnInserta;
	
	private JTable table;

	public AdminView() {
		
		setBounds(100, 100, 814, 563);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		//Creacion de panel para la tabla
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 108, 626, 349);
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

		model.addColumn("Dni");
		model.addColumn("Password");
		model.addColumn("Rol");
		model.addColumn("Nom");
		model.addColumn("Cognom");
		model.addColumn("Adresa");
		model.addColumn("Telf");
		model.addColumn("Correu");
		model.addColumn("Deutor");
		
		table.setModel(model);

		//---FUNCIONES TABLA 
		updateTable();
		selectRow();
		scrollPane.setViewportView(table);
		

		nombreCajas();
		cajasTexto();
		
		btnBuscar();
		btnNuevo();
		btnModifica();
		btnInserta();
		btnElimina();
		btnFiltros();
		
		panelInferior();
	}
	
//--------------------------------------------------------------------------------FUNCIONES TABLA--------------------------------------------------------------------------------------	
		

	public void selectRow() {//----FUNCION AL SELECCIONAR CAMPO
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
			
				btnModifica.setEnabled(true);// DESBLOQUEA BTN EDITAR
				btnElimina.setEnabled(true);// DESBLOQUEA BTN ELIMINAR
				btnInserta.setEnabled(false);// BLOQUEA BTN INSERTAR
				btnNuevo.setEnabled(true);// DESBLOQUEA BTN NUEVO
						
				txtDni.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF
			
				//------CAMBIA VALOR CAJAS TEXTO SEGUN EL REGISTRO SELECCIONADO
				txtDni.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtPassword.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				
				//------CAMBIA VALOR DE LA CAJA ROL SEGUN EL REGISTRO SELECCIONADO		
				if (table.getValueAt(table.getSelectedRow(),2).toString().equals("U")){
					rolBox.setModel(new DefaultComboBoxModel(new String[] {"U", "A"}));
				} else {
					rolBox.setModel(new DefaultComboBoxModel(new String[] {"A", "U"}));
				}
				
				txtNom.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtCognoms.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txtAdresa.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				txtTelf.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				txtCorreu.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
				
				//------CAMBIA VALOR DE LA CAJA DEUTOR SEGUN EL REGISTRO SELECCIONADO		
				if (table.getValueAt(table.getSelectedRow(),8).toString().equals("false")){
					deutorBox.setModel(new DefaultComboBoxModel(new String[] {"false", "true"}));
				} else {
					deutorBox.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
				}
			}
		});
		
	}

	
	private void updateTable() {
		//---Actualiza valores que se muestran en la tabla
		ClientsSQL conCli = new ClientsSQL();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Client cli:conCli.consultaClients()) {
				model.addRow(new Object[] {
						cli.getDni(),
						cli.getPassword(),
						cli.getRol(),
						cli.getNom(),
						cli.getCognom(),
						cli.getadresa(),
						cli.getTelf(),
						cli.getCorreu(),
						cli.getDeutor()
				});	
			}	

		} catch (Exception e) {

		}
		
	}
	
	
	private void updateTableDeutor() {
		//---Actualiza valores que se muestran en la tabla
		ClientsSQL conCli = new ClientsSQL();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Client cli:conCli.consultaDeutorClients()) {
				model.addRow(new Object[] {
						cli.getDni(),
						cli.getPassword(),
						cli.getRol(),
						cli.getNom(),
						cli.getCognom(),
						cli.getadresa(),
						cli.getTelf(),
						cli.getCorreu(),
						cli.getDeutor()
				});	
			}	

		} catch (Exception e) {

		}
		
	}
	
	
	private void updateTableAdmin() {
		//---Actualiza valores que se muestran en la tabla
		ClientsSQL conCli = new ClientsSQL();
		try {			
			
			model.setRowCount(0);	

			//----RELLENA TABLA
			for (Client cli:conCli.consultaAdminClients()) {
				model.addRow(new Object[] {
						cli.getDni(),
						cli.getPassword(),
						cli.getRol(),
						cli.getNom(),
						cli.getCognom(),
						cli.getadresa(),
						cli.getTelf(),
						cli.getCorreu(),
						cli.getDeutor()
				});	
			}	

		} catch (Exception e) {

		}
		
	}

//--------------------------------------------------------------------------------BOTONES--------------------------------------------------------------------------------------	
	
	public void btnBuscar() {
		
		textBuscador = new JTextField();
		textBuscador.setBounds(10, 105, 118, 23);
		contentPanel.add(textBuscador);
		textBuscador.setColumns(10);
				
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//---Actualiza valores que se muestran en la tabla
				ClientsSQL conCli = new ClientsSQL();
				try {			
					
					model.setRowCount(0);	

					//----RELLENA TABLA
					for (Client cli:conCli.buscaClients(new Client(textBuscador.getText().toString()))) {
						model.addRow(new Object[] {
								cli.getDni(),
								cli.getPassword(),
								cli.getRol(),
								cli.getNom(),
								cli.getCognom(),
								cli.getadresa(),
								cli.getTelf(),
								cli.getCorreu(),
								cli.getDeutor()
						});	
					}	

				} catch (Exception e1) {

				}
			}
		});
		btnNewButton.setBounds(11, 139, 117, 25);
		contentPanel.add(btnNewButton);

	}
	
	
	public void btnModifica() {
		
		btnModifica = new JButton("Modificar");
		btnModifica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				ClientsSQL conCli = new ClientsSQL();


				if (table.getSelectedRow() != -1) {
					Client cli = new Client(
							(String) model.getValueAt(table.getSelectedRow(), 0),
							(String) model.getValueAt(table.getSelectedRow(), 1),
							(String) model.getValueAt(table.getSelectedRow(), 2),
							(String) model.getValueAt(table.getSelectedRow(), 3),
							(String) model.getValueAt(table.getSelectedRow(), 4),
							(String) model.getValueAt(table.getSelectedRow(), 5),
							(String) model.getValueAt(table.getSelectedRow(), 6),
							(String) model.getValueAt(table.getSelectedRow(), 7),
							(String) model.getValueAt(table.getSelectedRow(), 8)
							);

					cli.setPassword(txtPassword.getText().toString());
					cli.setRol(rolBox.getSelectedItem().toString());
					cli.setNom(txtNom.getText().toString());
					cli.setCognom(txtCognoms.getText().toString()); 
					cli.setadresa(txtAdresa.getText().toString()); 
					cli.setTelf(txtTelf.getText().toString());
					cli.setCorreu(txtCorreu.getText().toString()); 
					cli.setDeutor(deutorBox.getSelectedItem().toString());

					//Confirmacion de editado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres editar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						if(txtDni.getText().toString().equals(cli.getDni())) {

							try {
								conCli.modificaClients(cli);						
								updateTable();

							} catch (SQLException e1) {
								e1.printStackTrace();
							}						
						} else {
							JOptionPane.showMessageDialog(null, "EL DNI NO SE PUEDE CAMBIAR");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero EDITAR");
				}

			}
		});
		btnModifica.setBounds(11, 319, 117, 25);
		contentPanel.add(btnModifica);
	}
	
	
	public void btnElimina() {
		
		btnElimina = new JButton("Eliminar");
		btnElimina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ClientsSQL conCli = new ClientsSQL();
				
				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {
					
					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			
					
					if(dialogResult == 0){
						
						// Obtenemos el primer dato del registro seleccionado (El cif)
						Client cli = new Client((String) model.getValueAt(table.getSelectedRow(), 0));
				        				        
				        try {//---BORRA DE LA TABLA SQL
				        	model.removeRow(table.getSelectedRow());	
				        	conCli.deleteClients(cli);
							
						} catch (SQLException e) {
							System.out.println("No se ha podido eliminar el registro");
							e.printStackTrace();
						}
					} 
		            
		        } else {
		        	JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
		        }
	
				
			}
		});
		btnElimina.setBounds(11, 355, 117, 25);
		contentPanel.add(btnElimina);
	}
	
	
	public void btnNuevo() {
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnModifica.setEnabled(false);// BLOQUEA BTN EDITAR
				btnElimina.setEnabled(false);// BLOQUEA BTN ELIMINAR
				btnInserta.setEnabled(true);// DESBLOQUEA BTN INSERTAR
				btnNuevo.setEnabled(false);// BLOQUEA BTN NUEVO
						
				txtDni.setEnabled(false);// BLOQUEA CAJA DE TEXTO CIF
				
				txtDni.setText("");
				txtPassword.setText("");
				txtNom.setText("");
				txtCognoms.setText("");
				txtAdresa.setText("");
				txtTelf.setText("");
				txtCorreu.setText("");
			}
		});
		
		btnNuevo.setEnabled(false);
		btnNuevo.setBounds(11, 391, 117, 25);
		contentPanel.add(btnNuevo);
	}
	
	
	public void btnInserta() {
		
		btnInserta = new JButton("Insertar");
		btnInserta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ClientsSQL conCli = new ClientsSQL();
				
				try {
					
					conCli.insertaClients(new Client(
							txtDni.getText().toString(),
							txtPassword.getText().toString(),
							rolBox.getSelectedItem().toString(),
							txtNom.getText().toString(),
							txtCognoms.getText().toString(),
							txtAdresa.getText().toString(),
							txtTelf.getText().toString(),
							txtCorreu.getText().toString(),
							deutorBox.getSelectedItem().toString()
							));
					
					updateTable();
					
				} catch (SQLException e1) {
					System.out.println("Falla al ainsertar cliente");
					JOptionPane.showMessageDialog(null, "Ha habido un error al insertar revise los campos");	
					e1.printStackTrace();
				}
				
			}
		});
		btnInserta.setBounds(11, 427, 117, 25);
		contentPanel.add(btnInserta);		
	}
	
	
	public void btnFiltros() {
		//Botones filtro
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (rdbtnDeutor.isSelected()) {
					updateTableDeutor();
				} else if (rdbtnAdmin.isSelected()) {
					updateTableAdmin();
				} else if(rdbtnTodos.isSelected()) {
					updateTable();
				}
				
			}
		});
		
		btnFiltrar.setBounds(11, 175, 117, 25);
		contentPanel.add(btnFiltrar);
				
		//radioButton
				
		rdbtnDeutor = new JRadioButton("Moroso");
		rdbtnDeutor.setBounds(35, 207, 81, 23);
		contentPanel.add(rdbtnDeutor);
				
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(35, 240, 81, 23);
		contentPanel.add(rdbtnAdmin);
				
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setBounds(35, 275, 81, 23);
		contentPanel.add(rdbtnTodos);
				

		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnDeutor);
		group.add(rdbtnAdmin);
		group.add(rdbtnTodos);
		
	}

	
	public void panelInferior() {
		//PANEL INFERIOR CONTIENE BOTTON OK & CANCELAR
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		{
			JButton okButton = new JButton("Ver Registros");
			okButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						
						Client cli = new Client(
								(String) model.getValueAt(table.getSelectedRow(), 0),
								(String) model.getValueAt(table.getSelectedRow(), 1),
								(String) model.getValueAt(table.getSelectedRow(), 2),
								(String) model.getValueAt(table.getSelectedRow(), 3),
								(String) model.getValueAt(table.getSelectedRow(), 4),
								(String) model.getValueAt(table.getSelectedRow(), 5),
								(String) model.getValueAt(table.getSelectedRow(), 6),
								(String) model.getValueAt(table.getSelectedRow(), 7),
								(String) model.getValueAt(table.getSelectedRow(), 8)
								);
					
					
						Ent_SorView window = new Ent_SorView(cli);														
						window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						window.setVisible(true);
						dispose();
					}
				}
			});
			
			okButton.setActionCommand("Ver Movimientos");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		
		{
			JButton cancelButton = new JButton("Cerrar Sesion");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
	}
	
//--------------------------------------------------------------------------------CAJAS TEXTO--------------------------------------------------------------------------------------		
	
	
	public void nombreCajas() {
		//NOMBRE CAJAS 
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(10, 16, 42, 15);
		contentPanel.add(lblDni);

		JLabel lblNom = new JLabel("Nombre");
		lblNom.setBounds(138, 16, 55, 15);
		contentPanel.add(lblNom);

		JLabel lblCognoms = new JLabel("Apellido");
		lblCognoms.setBounds(308, 16, 52, 15);
		contentPanel.add(lblCognoms);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setBounds(171, 51, 77, 15);
		contentPanel.add(lblPassword);

		JLabel lblAdrea = new JLabel("Direccion");
		lblAdrea.setBounds(10, 51, 70, 15);
		contentPanel.add(lblAdrea);

		JLabel lblCorreu = new JLabel("Direccion Electronica");
		lblCorreu.setBounds(345, 51, 135, 15);
		contentPanel.add(lblCorreu);

		JLabel lblRol = new JLabel("Tipo Usuario");
		lblRol.setBounds(614, 51, 91, 15);
		contentPanel.add(lblRol);

		JLabel lblTelf = new JLabel("Telefono");
		lblTelf.setBounds(470, 16, 70, 15);
		contentPanel.add(lblTelf);
		
		JLabel lblDeutor = new JLabel("Moroso");
		lblDeutor.setBounds(653, 16, 52, 15);
		contentPanel.add(lblDeutor);
	}
	
	
	public void cajasTexto() {
		
		//COMOBOX CLIENTE O ADMIN
		rolBox.setModel(new DefaultComboBoxModel(new String[] {"U", "A"}));
		rolBox.setBounds(727, 46, 52, 24);
		contentPanel.add(rolBox);
		
		deutorBox.setModel(new DefaultComboBoxModel(new String[] {"true", "false"}));
		deutorBox.setBounds(715, 11, 64, 24);
		contentPanel.add(deutorBox);
		
		//CONTENIDO CAJAS 
		txtDni = new JTextField();
		txtDni.setBounds(35, 13, 95, 19);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(203, 13, 95, 19);
		contentPanel.add(txtNom);
		
		txtCognoms = new JTextField();
		txtCognoms.setColumns(10);
		txtCognoms.setBounds(370, 13, 95, 19);
		contentPanel.add(txtCognoms);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(249, 48, 86, 19);
		contentPanel.add(txtPassword);
		
		txtAdresa = new JTextField();
		txtAdresa.setColumns(10);
		txtAdresa.setBounds(66, 48, 95, 19);
		contentPanel.add(txtAdresa);
		
		txtCorreu = new JTextField();
		txtCorreu.setColumns(10);
		txtCorreu.setBounds(490, 48, 114, 19);
		contentPanel.add(txtCorreu);
		
		txtTelf = new JTextField();
		txtTelf.setColumns(10);
		txtTelf.setBounds(543, 13, 100, 19);
		contentPanel.add(txtTelf);
		
	}
}
