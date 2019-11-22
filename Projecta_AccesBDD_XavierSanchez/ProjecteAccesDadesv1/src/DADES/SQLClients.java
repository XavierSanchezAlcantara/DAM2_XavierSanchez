package DADES;
import MODEL.Client;
import java.sql.Connection;

import java.sql.DriverManager;
import java.util.ArrayList;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;


public class SQLClients {

	Connection c = null;

	Statement sentencia = null;

	String nombreTabla;

	String Nombre, Apellidos, Nota;

	int ID;

	public void conectar() {

		try {

			Class.forName("org.sqlite.JDBC");

			c = DriverManager.getConnection("jdbc:sqlite:/home/xavi/Muntatge");

			System.out.println("Exito al conectar con base de datos");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos");

		}

	}

	public void insertaClients(Client client) throws SQLException {

		String sqlInsert = "INSERT INTO Clients(cif,nom,cognom,direccio,bankCode,telefono)"
				+"VALUES("+"\""+client.getCif()+"\""+","
				+"\""+client.getNom()+"\""+","
				+"\""+client.getCognom()+"\""+","
				+"\""+client.getDireccio()+"\""+","
				+"\""+client.getBankCode()+"\""+","
				+"\""+client.getTelefono()+"\""+");";

		try {

			conectar();

			sentencia = c.createStatement();

			sentencia.executeUpdate(sqlInsert);

			sentencia.close();

			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertar datos en la tabla");

		}

	}
	public void editarClient(String cif,String campo,String valor) throws SQLException {

		String sqlUpdate = "UPDATE Clients SET "+campo+"='"+valor+"' WHERE cif='"+cif+"';";

		try {

			conectar();

			sentencia = c.createStatement();

			sentencia.executeUpdate(sqlUpdate);

			sentencia.close();

			c.close();

			System.out.println("Datos actualizados");

		} catch (Exception e) {

			System.out.println("Error al insertar datos en la tabla");

		}

	}

	public void eliminarClient(String cif) throws SQLException {

		String sqlDrop = "DELETE FROM Clients WHERE '"+cif+"';";

		try {

			conectar();

			sentencia = c.createStatement();
			

			sentencia.executeUpdate(sqlDrop);

			sentencia.close();

			c.close();

			System.out.println("Datos actualizados");

		} catch (Exception e) {

			System.out.println("Error al insertar datos en la tabla");

		}

	}
	public void consultaClients(String nombreTabla) throws SQLException {
		
		conectar();

		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM " + nombreTabla + ";";
		ArrayList <Client> clientes= new ArrayList <Client>();
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			System.out.println("Registres: ");
			
			while (rs.next()) {
				System.out.println("--------------------------------");
				String cif =rs.getString("cif");
				String nom = rs.getString("nom");
				String cognom = rs.getString("cognom");
				String direccio=rs.getString("direccio");
				String bankCode= rs.getString("bankCode");
				String tlf=rs.getString("telefono");
				System.out.println("CIF: "+cif+"\n Nom: "+nom+"\n Cognoms: "+cognom+"\n Direccio: "+direccio+"\n bankCode: "+bankCode+"\n Telefono: "+tlf);
				clientes.add(new Client(cif, nom, cognom, direccio, bankCode, tlf));
				//System.out.println(clientes.toString());
			}
			for (int i = 0; i < clientes.size(); i++) {
				System.out.println(clientes.get(i));
			}

			rs.close();

			sentencia.close();

			c.close();

		} catch (Exception e) {

			System.out.println("Fallo al recuperar datos");

		}

	}

}
