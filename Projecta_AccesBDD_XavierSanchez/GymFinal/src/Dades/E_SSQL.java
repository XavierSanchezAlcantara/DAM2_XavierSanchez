package Dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Client;
import Model.E_S;

public class E_SSQL {
	Connection c = null;

	Statement sentencia = null;
	
	int moviment;
	String client;
	String gimnas;
	String data;
	String tipus;

	ArrayList<E_S> moviments = new ArrayList<E_S>();
	
	//Conecta base dades
	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:servidor/gymSimulator.db");
			System.out.println("Exito al conectar con base de datos E_S");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos E_S");

		}
		return c;
	}
	
	//Inserta en tabla E_S
	public void insertaMoviment(E_S mov) throws SQLException {

			
			try {
				conectar();

				String sqlInsert = "INSERT INTO E_S (Client, Gimnas, Data, Tipus) "

			            	 + "VALUES (" + "'" + mov.getClient() + "'" + ","
			            	 + "'" + mov.getGimnas() + "'" + ","
			            	 + "'" + mov.getData() + "'" + ","
			            	 + "'" + mov.getTipus() + "'" + ");";
				
				sentencia = c.createStatement();
				sentencia.executeUpdate(sqlInsert);
				sentencia.close();
				c.close();


			} catch (Exception e) {


			}
		}

	//Modifica taula E_S
	public void modificaMoviment(E_S mov) throws SQLException {

		try {

			conectar();
		
			String sqlUpdate ="UPDATE E_s "
								+ "SET "
								+ "Client='" + mov.getGimnas() 
								+ "', Gimnas='" + mov.getGimnas()
								+ "', Data='" + mov.getData()
								+ "', Tipus='" + mov.getTipus() 
								+ "' WHERE moviment =" + mov.getMoviment()
								+ ";";
						
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate);
			sentencia.close();
			c.close();
		
			System.out.println("Datos actualizados");

		} catch (Exception e) {

				System.out.println("Error al actualizar datos en la tabla E_S");

		}
	}
		
	//Elimina registre taula E_S
	public void deleteMoviment(E_S mov) throws SQLException {

		try {

			conectar();

			String sqlDelete = "DELETE FROM E_S WHERE Moviment= " + mov.getMoviment() + ";";
			System.out.println(sqlDelete);	
			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete);
			sentencia.close();
			c.close();
			System.out.println("Datos eliminados");
		} catch (Exception e) {

			System.out.println("Error al eliminar datos en la tabla E_S");

		}

	}
		
	//Muestra Tabla E_S
	public ArrayList<E_S> consultaMoviment() throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM E_S ORDER BY Data desc;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				moviment = rs.getInt("Moviment");
				client = rs.getString("Client");
				gimnas = rs.getString("Gimnas");
				data = rs.getString("Data");
				tipus = rs.getString("Tipus");
						
				//GUARDA EN ARRAY LIST CLIENT
				moviments.add(new E_S(
						moviment, 
						client, 
						gimnas,
						data, 
						tipus));
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return moviments;
	}
	
	//Muestra Tabla E_S Cliente
	public ArrayList<E_S> consultaMovimentClient(Client cli) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM E_S WHERE Client = '" + cli.getDni() + "' ORDER BY Data desc;";
		
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
					
				moviment = rs.getInt("Moviment");
				client = rs.getString("Client");
				gimnas = rs.getString("Gimnas");
				data = rs.getString("Data");
				tipus = rs.getString("Tipus");
						
				//GUARDA EN ARRAY LIST CLIENT
				moviments.add(new E_S(
						moviment, 
						client, 
						gimnas,
						data, 
						tipus));
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return moviments;
	}
	
	//Muestra Ultimo Registro E_S Cliente
	public E_S consultaUltimMovimentClient(Client cli) throws SQLException {

		conectar();
		E_S mov = null;
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM E_S WHERE Client = '" + cli.getDni() + "' ORDER BY moviment desc LIMIT 1;";
		System.out.println(consultaSql);
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
		
				
				moviment = rs.getInt("Moviment");
				client = rs.getString("Client");
				gimnas = rs.getString("Gimnas");
				data = rs.getString("Data");
				tipus = rs.getString("Tipus");
						
				//GUARDA EN ARRAY LIST CLIENT
				mov = new E_S(
						moviment, 
						client, 
						gimnas,
						data, 
						tipus);
			

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return mov;
	}

	//Muestra Registro E_S Desde X Hasta Y
	public ArrayList<E_S> consultaMovimentClientDesdeHasta(Client cli, String desde, String hasta) throws SQLException {

		conectar();
		
		ArrayList<E_S> moviments2 = new ArrayList<E_S>();
		
		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM E_S WHERE Client = " + cli.getDni() + " AND Data BETWEEN '" + desde + "' AND '" + hasta + "';";
		
		//System.out.println(consultaSql);
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {
				
				moviment = rs.getInt("Moviment");
				client = rs.getString("Client");
				gimnas = rs.getString("Gimnas");
				data = rs.getString("Data");
				tipus = rs.getString("Tipus");
						
				//GUARDA EN ARRAY LIST CLIENT
				moviments2.add(new E_S(
						moviment, 
						client, 
						gimnas,
						data, 
						tipus));
			}

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		
		return moviments2;
	}
	 
	//Consulta los segudos entre 2 fechas
	public int consultaTiempo( String desde, String hasta) throws SQLException {

		conectar();
		
		int tiempo=0;
		
		sentencia = c.createStatement();
		String consultaSql = "SELECT strftime ('%s', '" + hasta + "') - strftime ('%s', '" + desde + "');";
		
		System.out.println(consultaSql);
		try {

			ResultSet rs = sentencia.executeQuery(consultaSql);
		
			tiempo= rs.getInt(1);
			System.out.println(tiempo);

			rs.close();
			sentencia.close();
			c.close();

		} catch (Exception e) {

			Talal: 	System.out.println(e.getMessage());

		}
		return tiempo;
	}
	 
	//Muestra Tabla E_S Cliente FILTRA  
		public ArrayList<E_S> consultaMovimentClientGym(Client cli, String where, String variable) throws SQLException {

			conectar();

			sentencia = c.createStatement();
			String consultaSql = "SELECT * FROM E_S WHERE Client = '" + cli.getDni() + "' AND  " + where + " = '" + variable +"' ORDER BY Data desc;";
			System.out.println(consultaSql);
			try {

				ResultSet rs = sentencia.executeQuery(consultaSql);
				while (rs.next()) {
						
					moviment = rs.getInt("Moviment");
					client = rs.getString("Client");
					gimnas = rs.getString("Gimnas");
					data = rs.getString("Data");
					tipus = rs.getString("Tipus");
							
					//GUARDA EN ARRAY LIST CLIENT
					moviments.add(new E_S(
							moviment, 
							client, 
							gimnas,
							data, 
							tipus));
				}

				rs.close();
				sentencia.close();
				c.close();

			} catch (Exception e) {

				Talal: 	System.out.println(e.getMessage());

			}
			return moviments;
		}
		
		
};