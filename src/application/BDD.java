package application;

import java.sql.*;

public class BDD{
	private static Connection connection;
	private static Statement statement;
	private String query;
	
	public BDD() {
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}*/
		try {
			String url = "jdbc:mysql://localhost:3306/amazonie";
			String user = "root";
			String password = "Angela081996my$ql";
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public Connection closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return connection;
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return resultSet;
	}
	
	public String executeUpdate(String query) {
		String result = "";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			result = query;
		} catch(SQLException e) {
			System.err.println(e.getMessage());
			result = e.getMessage();
		}
		return result;
	}
	
	public ResultSet selectAll (String nomTable) {
		String query = "SELECT * FROM " + nomTable;
		return this.executeQuery(query);		
	}

	public ResultSet selectAll (String nomTable, String etat) {
		String query = "SELECT * FROM " + nomTable + " WHERE " + etat;
		return this.executeQuery(query);
	}
	
	public ResultSet select (String nomTable, String[] nomColonne) {
		String query = "SELECT ";
		for(int i = 0; i < nomColonne.length; i++) {
			query += nomColonne[i];
			if(i != nomColonne.length - 1) {
				query += ", ";
			}
		}
		query += " FROM " + nomTable;
		return this.executeQuery(query);
	}
	
	public ResultSet select (String nomTable, String[] nomColonne, String etat) {
		String query = "SELECT ";
		for(int i = 0; i < nomColonne.length; i++) {
			query += nomColonne[i];
			if(i != nomColonne.length - 1) {
				query += ", ";
			}
		}
		query += " FROM " + nomTable + " WHERE " + etat;
		return this.executeQuery(query);
	}
	
	public String insert (String nomTable, String[] contenu) {
		query = "INSERT INTO " + nomTable + " VALUES (";
		for(int i = 0; i < contenu.length; i++) {
			query += "'" +contenu[i] + "'";
			if(i != contenu.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		return this.executeUpdate(query);
	}
	
	public String insert (String nomTable, String[] nomColonne, String[] contenu) {
		query = "INSERT INTO " + nomTable + "( ";
		for (int i = 0; i < nomColonne.length; i++) {
			query += nomColonne[i];
			if(i != nomColonne.length - 1) {
				query += ", ";
			}
		}
		query += ") VALUES (";
		for(int i = 0; i < contenu.length; i++) {
			query += "'" +contenu[i] + "'";
			if(i != contenu.length - 1) {
				query += ", ";
			}
		}
		query += ")";
		return this.executeUpdate(query);
	}
	
	public String delete (String nomTable) {
		query = "DELETE FROM " + nomTable;
		return this.executeUpdate(query);
	}
	
	public String delete (String nomTable, String etat) {
		query = "DELETE FROM " + nomTable + " WHERE " + etat;
		return this.executeUpdate(query);
	}
	
	public String update (String nomTable, String [] nomColonne, String [] contenu, String etat) {
		query = "UPDATE " +nomTable+ " SET ";
		for (int i = 0 ; i < nomColonne.length; i++) {
			query += nomColonne[i]+ " = '" +contenu[i]+ "'";
			if(i != contenu.length - 1) {
				query += ", ";
			}
		}
		query += " WHERE " +etat;
		return this.executeUpdate(query);
	}
}
