package DADES;
import MODEL.Client;
import java.sql.Connection;

import java.sql.DriverManager;

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

	public void consultaClients(String nombreTabla) throws SQLException {

		conectar();

		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM " + nombreTabla + ";";

		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {

				int ID = rs.getInt("Id");

				String Nombre = rs.getString("Name");

				String Apellidos = rs.getString("Last_Name");

				// String Nota=rs.getString("Nota");

				System.out.println(" Nombre : " + Nombre + " Apellidos : " + Apellidos);

			}

			rs.close();

			sentencia.close();

			c.close();

		} catch (Exception e) {

			System.out.println("Fallo al recuperar datos");

		}

	}

}
