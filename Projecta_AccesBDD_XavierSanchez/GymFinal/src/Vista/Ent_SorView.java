package Vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JMonthChooser;

import Dades.ClientsSQL;
import Dades.E_SSQL;
import Model.Client;
import Model.E_S;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Ent_SorView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;

	JRadioButton rdbtnGimnas;
	JRadioButton rdbtnMoviment;
	JRadioButton rdbtnData;
	
	static JComboBox e_sBox;
	static JComboBox gymBox;
	
	static JDateChooser dateDesde;
	static JDateChooser dateHasta;
	static Client cli;

	public Ent_SorView(Client cli) {
		
		this.cli = cli;
		
		setBounds(100, 100, 625, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(234, 24, 365, 306);
		contentPanel.add(scrollPane);
		
		//----CREA TAULA MOVIMENT USUARI
		table = new JTable();
		
		//----IMPIDE EDITAR LAS CASILLAS		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				
				return false;
			}
		};

		//----INTRODUCE NOMBRE COLUMNAS TABLA DESDE SQL
		model.addColumn("Nº");
		model.addColumn("Data");
		model.addColumn("Gimnas");
		model.addColumn("E/S");
		

		table.setModel(model);

		//---FUNCIONES TABLA 
		
		updateTable();

		selectRow();
		
		scrollPane.setViewportView(table);
		
		medidasTabla();
		
		filtro();
		calendarios();
		cajasTexto();
		panelInferiror();
		cajaEleccion();
		
	}
	
	//-------------------------------------------------------------FUNCIONES TABLA 
	

	public void selectRow() {//----FUNCION AL SELECCIONAR CAMPO

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				System.out.println(table.getValueAt(table.getSelectedRow(), 1));
				System.out.println(table.getValueAt(table.getSelectedRow(), 2));

			}
		});
	}
	
	
	public void medidasTabla() {
		TableColumnModel columnModel = table.getColumnModel();

	    columnModel.getColumn(0).setPreferredWidth(100);
	    columnModel.getColumn(1).setPreferredWidth(200);
	    columnModel.getColumn(2).setPreferredWidth(150);
	    columnModel.getColumn(3).setPreferredWidth(100);
	}
	
	
	public static void updateTable(){ 
		//---Actualiza valores que se muestran en la tabla
		E_SSQL conE_S = new E_SSQL();

		try {

			model.setRowCount(0);

			//----RELLENA TABLA
			
			for (E_S e_s:conE_S.consultaMovimentClient(cli)) {
				model.addRow(new Object[] {
						e_s.getMoviment(),
						e_s.getData(),
						e_s.getGimnas(),
						e_s.getTipus()
				});	
			}			

		} catch (Exception e) {

		}

	}//updateTable()--------

	
	public static void updateTableFiltro(int key){ 
		//---Actualiza valores que se muestran en la tabla
		E_SSQL conE_S = new E_SSQL();

		try {

			model.setRowCount(0);

			//----RELLENA TABLA
			switch (key) {
			
			case 1:
				//CUANDO SELECCIONAS FILTRAT POR GIMNASIO
				System.out.println("1");
				for (E_S e_s:conE_S.consultaMovimentClientGym(cli, "Gimnas", gymBox.getSelectedItem().toString() )) {
					model.addRow(new Object[] {
							e_s.getMoviment(),
							e_s.getData(),
							e_s.getGimnas(),
							e_s.getTipus()
					});	
				}
				break;

			case 2:
				System.out.println("2");
				//CUANDO SELECCIONAS FILTRAT POR ENTRADAS O SALIDAS 
				for (E_S e_s:conE_S.consultaMovimentClientGym(cli,"Tipus", e_sBox.getSelectedItem().toString() )) {
					model.addRow(new Object[] {
							e_s.getMoviment(),
							e_s.getData(),
							e_s.getGimnas(),
							e_s.getTipus()
					});	
				}
				break;
				
			case 3:
				System.out.println("3");
				//CUANDO SELECCIONAS FILTRAT POR FECHA
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//COJE FECHAS DE LOS CALENDARIOS
				String desde = sdf.format(dateDesde.getDate()).toString();
				String hasta = sdf.format(dateHasta.getDate()).toString();
				
				for (E_S e_s:conE_S.consultaMovimentClientDesdeHasta(cli, desde, hasta)) {
					model.addRow(new Object[] {
							e_s.getMoviment(),
							e_s.getData(),
							e_s.getGimnas(),
							e_s.getTipus()
					});	
				}
				break;
			}
			
						

		} catch (Exception e) {

		}

	}//updateTableFiltro()--------
	
	
	public void cajaEleccion() {
		
		e_sBox = new JComboBox();
		e_sBox.setModel(new DefaultComboBoxModel(new String[] {"E", "S"}));
		e_sBox.setBounds(101, 100, 59, 24);
		contentPanel.add(e_sBox);
		
		
		gymBox = new JComboBox();
		gymBox.setModel(new DefaultComboBoxModel(new String[] {"GS Santpedor", "GS Manresa", "GS Barcelona", "GS SantVi"}));
		gymBox.setBounds(102, 53, 110, 24);
		contentPanel.add(gymBox);
		

	}

	
	public void calendarios() {
		
		dateDesde = new JDateChooser();
		dateDesde.setBounds(16, 231, 153, 19);
		contentPanel.add(dateDesde);
		
		dateHasta = new JDateChooser();
		dateHasta.setBounds(16, 297, 153, 19);
		contentPanel.add(dateHasta);
	}
	
	
	public void filtro() {
	
		rdbtnGimnas = new JRadioButton("Gimnasio");
		rdbtnGimnas.setBounds(6, 54, 83, 23);
		contentPanel.add(rdbtnGimnas);

		rdbtnMoviment = new JRadioButton("Movimiento");
		rdbtnMoviment.setBounds(6, 101, 92, 23);
		contentPanel.add(rdbtnMoviment);

		rdbtnData = new JRadioButton("Fecha:");
		rdbtnData.setBounds(6, 148, 92, 23);
		contentPanel.add(rdbtnData);	

		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnGimnas);
		group.add(rdbtnMoviment);
		group.add(rdbtnData);
		
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (rdbtnGimnas.isSelected()) {
					updateTableFiltro(1);
					
				} else if (rdbtnMoviment.isSelected()) {
					updateTableFiltro(2);
					
				} else if(rdbtnData.isSelected()) {
					updateTableFiltro(3);
					
				} else {
					updateTable();
				}
			}
		});
		btnFiltrar.setActionCommand("OK");
		btnFiltrar.setBounds(68, 379, 92, 23);
		contentPanel.add(btnFiltrar);
	}
	
	
	public void panelInferiror() {
		//Panel botones inferior

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
//------------------------------------------------------------------------------ATRAS
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				AdminView windowClient = new AdminView();															
				windowClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				windowClient.setVisible(true);
				dispose();
			}
			
		});
		
		btnAtras.setActionCommand("Atras");
		buttonPane.add(btnAtras);
		
//------------------------------------------------------------------------------ELIMINAR
		JButton btnEliminar = new JButton("Eliminar Registro");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				E_SSQL conE_S = new E_SSQL();
				//Controla que tengas un registro seleccionado
				if (table.getSelectedRow() != -1) {

					//Confirmacion de borrado
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Quieres eliminar el registro seleccionado?","Warning",dialogButton);			

					if(dialogResult == 0){

						// Obtenemos el primer dato del registro seleccionado (El numero movimiento)							
						E_S mov = new E_S((int)model.getValueAt(table.getSelectedRow(), 0));

						try {//---BORRA DE LA TABLA SQL
							model.removeRow(table.getSelectedRow());	
							conE_S.deleteMoviment(mov);;

						} catch (SQLException e1) {
							System.out.println("No se ha podido eliminar el registro");
							e1.printStackTrace();
						}
					} 

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un registro primero");
				}
			}
		});
		buttonPane.add(btnEliminar);
		btnEliminar.setActionCommand("Eliminar");
		getRootPane().setDefaultButton(btnEliminar);

		//------------------------------------------------------------------------------CANCELAR
		JButton cancelButton = new JButton("Cerrar Sesion");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}
	
	
	public void cajasTexto() {
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(6, 194, 70, 15);
		contentPanel.add(lblDesde);
		
		JLabel lblNewLabel = new JLabel("Hasta:");
		lblNewLabel.setBounds(6, 262, 70, 15);
		contentPanel.add(lblNewLabel);
		
	}
}



