package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import Dades.E_SSQL;
import Model.Client;
import Model.E_S;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MovimentUser_View extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	
	static E_SSQL conE_S = new E_SSQL();
	static Client cli;
	JDateChooser dateDesde = new JDateChooser();
	JDateChooser dateHasta = new JDateChooser();
	
	public MovimentUser_View(Client cli) {
		
		this.cli = cli;
		
		setBounds(100, 100, 552, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 516, 156);
		contentPanel.add(scrollPane);
		
		table = new JTable();
				
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
			
					
		model.addColumn("Data");
		model.addColumn("Gimnas");
		model.addColumn("Entrada o salida");
						
		table.setModel(model);
				
		updateTable();
		
		scrollPane.setViewportView(table);
		
		btnVerTiempo();
		calendarioDesde();
		calendarioHasta();
		panelInferior();
		
	}
	
	
	public static void updateTable(){ 
		
		try {
			
			model.setRowCount(0);
								
			for (E_S e_s:conE_S.consultaMovimentClient(cli)) {
				model.addRow(new Object[] {
					e_s.getData(),
					e_s.getGimnas(),
					e_s.getTipus()
				});	
			}			
			
		} catch (Exception e) {
			
		}
		
	}
	
	
	public static void updateTableDesdeHasta(String desde, String hasta){ 
		
		try {
			
			model.setRowCount(0);
								
			for (E_S e_s:conE_S.consultaMovimentClientDesdeHasta(cli, desde, hasta)) {
				model.addRow(new Object[] {
					e_s.getData(),
					e_s.getGimnas(),
					e_s.getTipus()
				});	
			}			
			
		} catch (Exception e) {
			
		}
		
	}
	
	
	public static void cuentaTiempo(ArrayList<E_S> moviments2){ 
		
		
		boolean aux = false; 
		int tiempo = 0;
		
		for (int i = 0; i < moviments2.size(); i++) {
			
			if((i+2) <= moviments2.size()) {
				
				if (moviments2.get(0).getTipus().equals("S") && aux == false) {
					
					i++;
					aux = true;
					
					try {
						tiempo += conE_S.consultaTiempo(moviments2.get(i).getData(), moviments2.get(i+1).getData());
						i++;
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				} else {
					
					
					try {
						tiempo += conE_S.consultaTiempo(moviments2.get(i).getData(), moviments2.get(i+1).getData());
						i++;
						
					} catch (SQLException e) {
						System.out.println("Falla en el ELSE  que compruba el primer puesto del array e_s");
						e.printStackTrace();
					}
				}
			}
			
		}//for END
		
		System.out.println(tiempo);
		
		JOptionPane.showMessageDialog(null, "Hola estos dias has estado un total de: "+ CalcularTiempo(tiempo) +" minutos" );	
		
		
	}
	
	private static String CalcularTiempo(int tsegundos) {
		int horas = (tsegundos / 3600);
	    int minutos = ((tsegundos-horas*3600)/60);
	    int segundos = tsegundos-(horas*3600+minutos*60);
	    
	    return Integer.toString(horas) + ":" + Integer.toString(minutos) + ":" + Integer.toString(segundos);
	}
	
	
	//-------------------------------------------------------------ACCESORIOS VIEW
	public void btnVerTiempo() {
	}
	
	 
	public void panelInferior() {
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		{
			JButton cancelButton = new JButton("Cerrar Session");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}	
			});
			
			JButton cancelButton_1 = new JButton("Atras");
			cancelButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					UserView windowClient = new UserView(cli);																
					windowClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					windowClient.setVisible(true);
					dispose();
				}
			});
			cancelButton_1.setActionCommand("Cancel");
			buttonPane.add(cancelButton_1);
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		
	}
	
	
	public void calendarioDesde() {
		JLabel label = new JLabel("Desde:");
		label.setBounds(40, 7, 70, 15);
		contentPanel.add(label);
			
		dateDesde.setBounds(37, 33, 153, 19);
		contentPanel.add(dateDesde);
	}
	
	
	public void calendarioHasta() {
		JLabel label_1 = new JLabel("Hasta:");
		label_1.setBounds(227, 7, 70, 15);
		contentPanel.add(label_1);
		
		dateHasta.setBounds(227, 33, 153, 19);
		contentPanel.add(dateHasta);
		
		JButton okButton = new JButton("Ver tiempo");
		okButton.setActionCommand("OK");
		okButton.setBounds(423, 29, 103, 23);
		contentPanel.add(okButton);
		
		
	}
}
