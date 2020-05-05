package Dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Client;

public class ClientsSQL {
	Connection c = null;

	Statement sentencia = null;


	String dni;
	String password;
	String rol;
	String nom;
	String cognom;
	String adresa;
	String telf;
	String correu;
	String deutor;	

	ArrayList<Client> clientes = new ArrayList<Client>();
	
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/gymSimulator.db");
			System.out.println("Exito al conectar con base de datos Client");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos Client");

		}
		return c;

	}
	
	public void insertaClients(Client cli) throws SQLException {

		
		try {
			conectar();

			String sqlInsert = "INSERT INTO Client (Dni, Password, Rol, Nom, Cognom, Adresa, Telf, Correu, Deutor) "

		            	 + "VALUES (" + "\"" + cli.getDni() + "\"" + ","
		            	 + "\"" + cli.getPassword() + "\"" + ","
		            	 + "\"" + cli.getRol() + "\"" + ","
		            	 + "\"" + cli.getNom() + "\"" + ","
		            	 + "\"" + cli.getCognom() + "\"" + ","
		            	 + "\"" + cli.getadresa() + "\"" + ","
		            	 + "\"" + cli.getTelf() + "\"" + ","
		            	 + "\"" + cli.getCorreu() + "\"" + ","
		            	 + "\"" + cli.getDeutor() + "\"" + ");";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlInsert);
			sentencia.close();
			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println("Error al insertertar datos en la tabla Client");

		}
	}
	public void modificaClients(Client cli) throws SQLException {

		try {

			conectar();
	
			String sqlUpdate ="UPDATE Client "
							+ "SET "
							+ "Password='" + cli.getPassword() 
							+ "', Rol='" + cli.getRol()
							+ "', Nom='" + cli.getNom()
							+ "', Cognom='" + cli.getCognom()
							+ "', adresa='" + cli.getadresa()
							+ "', Telf='" + cli.getTelf()
							+ "', Correu='" +cli.getCorreu()
							+ "', Deutor='"+ cli.getDeutor()
							+ "' WHERE Dni='" + cli.getDni() + "';";
					
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
	
			System.out.println("Datos actualizados");

		} catch (Exception e) {

		}
	}
	
	//Elimina Client
	public void deleteClients(Client cli) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM Client WHERE Dni='"	+ cli.getDni() + "';";
			
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();


		} catch (Exception e) {


		}

	}
	
	//Muestra Tabla Client
	public ArrayList<Client> consultaClients() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Client;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				clientes.add(new Client(
						dni, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
						correu, 
						deutor));

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {


		}
		return clientes;
	}

	public ArrayList<Client> consultaDeutorClients() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Client WHERE Deutor = 'true';";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				clientes.add(new Client(
						dni, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
						correu, 
						deutor));

			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return clientes;
	}
	
	public ArrayList<Client> consultaAdminClients() throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM Client WHERE Rol = 'A';";
			
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				while (rs.next()) {
						
					dni = rs.getString("Dni");
					password = rs.getString("Password");
					rol = rs.getString("Rol");
					nom = rs.getString("Nom");
					cognom = rs.getString("Cognom");
					adresa = rs.getString("adresa");
					telf = rs.getString("Telf");
					correu = rs.getString("Correu");
					deutor = rs.getString("Deutor");
						
					clientes.add(new Client(
							dni, 
							password, 
							rol,
							nom, 
							cognom, 
							adresa, 
							telf, 
							correu, 
							deutor));

				}

				rs.close();
				sentencia.close();
				c.close();

			} catch (Exception e) {

				Talal: 	System.out.println(e.getMessage());

			}
			return clientes;
		}
		
	public Client buscaDniClients(Client cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Client WHERE Dni = '" + cli.getDni() + "';";
		Client client = new Client(cli.getDni(),cli.getPassword());	
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				 client = new Client(
						dni, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
						correu, 
						deutor);

			}

			rs.close();
			sentencia.close();
			c.close();
		} catch (Exception e) {
			System.out.println("impossible");
			Talal: 	System.out.println(e.getMessage());

		}
		return client;
	}
		
	public ArrayList<Client> buscaClients(Client cli) throws SQLException {
		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM Client WHERE Dni LIKE '%" + cli.getDni() + "%';";
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);

			while (rs.next()) {
					
				dni = rs.getString("Dni");
				password = rs.getString("Password");
				rol = rs.getString("Rol");
				nom = rs.getString("Nom");
				cognom = rs.getString("Cognom");
				adresa = rs.getString("adresa");
				telf = rs.getString("Telf");
				correu = rs.getString("Correu");
				deutor = rs.getString("Deutor");
					
				clientes.add(new Client(
						dni, 
						password, 
						rol,
						nom, 
						cognom, 
						adresa, 
						telf, 
						correu, 
						deutor));
				
			}

			rs.close();
			sentencia.close();
			c.close();
			
		} catch (Exception e) {
			System.out.println("fALLO AL BUSCAR ");
			Talal: 	System.out.println(e.getMessage());

		}
		return clientes;
	}
		
};