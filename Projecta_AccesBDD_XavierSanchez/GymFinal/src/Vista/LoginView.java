package Vista;

import Dades.*;
import Model.Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUser;
	private JPasswordField passwordField;
	ClientsSQL conector = new ClientsSQL();

	public LoginView() {
		setBounds(100, 100, 353, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textUser = new JTextField();
		textUser.setBounds(184, 80, 114, 19);
		contentPanel.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblUser = new JLabel("Identificador:");
		lblUser.setBounds(89, 83, 83, 15);
		contentPanel.add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setBounds(89, 115, 83, 15);
		contentPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 112, 114, 19);
		contentPanel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("By: Xavi S\u00E1nchez");
		lblNewLabel.setBounds(208, 13, 104, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gym Simulator");
		lblNewLabel_1.setBounds(208, 41, 85, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblImage = new JLabel("New label");
		lblImage.setBounds(26, 13, 55, 16);
		contentPanel.add(lblImage);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{//BOTON OK
				JButton okButton = new JButton("ENTRAR");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						try {
							
							Client cli = new Client(textUser.getText().toString());

							cli = conector.buscaDniClients(cli);
							
							if(cli.getPassword().equals(passwordField.getText().toString())) {								
								
								if(cli.getRol().equals("A")) {
									
									
									AdminView windowClient = new AdminView();								
									windowClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									windowClient.setVisible(true);
									dispose();
									
								} else if(cli.getRol().equals("U")) {
									
									
									UserView windowClient = new UserView(cli);								
									windowClient.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									windowClient.setVisible(true);
									dispose();
								}					
							};
							
							
						} catch (Exception e2) {
							// TODO: handle exception
							System.out.println("error");
							JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos!!!!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("CANCELAR");
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
}
