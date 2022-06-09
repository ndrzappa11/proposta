package db;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import API.API_1;
import API.API_2;
import API.API_4;


public class SaveMySQL {


	//		METODO DI CONNESSIONE AL DATABASE
	private static final String DB_CONNECTION1 = "jdbc:postgresql://database-1.cs899svqo5yc.us-east-1.rds.amazonaws.com:5432/api";
	private static final String DB_CONNECTION3 = "jdbc:mysql://localhost:3306/api?user=root&useSSL=false";
	//private static final String DB_USER = "root";
	//private static final String DB_PASSWORD = "Raviolo.1234";//forse raviolo
	public static  Connection getDBConnection() throws Exception {
		System.out.println("     ");
		System.out.println("-------MySQL JDBC Connection---------");
		Connection dbConnection = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");  //connessione al db mysql
			Class.forName("org.postgresql.Driver");  //connessione al db postgrest
			System.out.println("class for name funziona");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out.println("ERRORE class.ForName");
		} // fine try-catch
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION1, "root", "raviolo.123"); 
			//dbConnection = DriverManager.getConnection(DB_CONNECTION3);	//connessione al db locale su localhost
			System.out.println("CONNESSIONE SQL AD API STABILITA");
		}catch(SQLException e) {
			System.out.println("CCONNESSIONE SQL AD API  NON  STABILITA!");
			throw new SQLException(e.getErrorCode()+":"+e.getMessage());
		}
		System.out.println("---------------------------------");
		System.out.println("     ");
		return dbConnection;
	}


	public static double getDouble(String sql, String varRicercata) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getDouble(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
		return -1;
	}
	
	public static int getInt(String sql, String varRicercata) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getInt(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
		return -1;
	}

	public static String getString(String sql, String varRicercata) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getString(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
		return "non trovata";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////

	public static void popolaDatabase() throws Exception {
		Statement st;
		ResultSet rs;
		String sql;
		Statement stmt = null;
		Connection conn = null;
		conn = getDBConnection();

		double premio;
		double massimale;

		double valore;
		String tip_mach;
		String durata;
		String tip;



		String[] tipA = {"assicurazione", "estensione di garanzia", "assicurazione e estensione di garanzia"};
		/**String tip1 = "assicurazione";
		String tip2 = "estensione di garanzia";
		String tip3 = "assicurazione e estensione di garanzia"; **/

		String[] tip_machA = {"verniciatura", "elettrodomestici", "ecografia", "altro ..."};
		/**String tip_mach1 = "verniciatura";
		String tip_mach2 = "elettrodomestici";
		String tip_mach3 = "ecografia";
		String tip_mach4 = "altro ..."; **/

		String[] durataA = {"tre_mesi", "sei_mesi", "dodici_mesi", "ventiquattro_mesi"};
		/**String durata1 = "tre_mesi";
		String durata2 = "sei_mesi";
		String durata3 = "dodici_mesi";
		String durata4 = "ventiquattro_mesi"; **/


		try {
			conn = getDBConnection();
			System.out.println("funziona la connessione");
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			for(int i=0; i < 3; i++) //tipA
				for(int j=0; j< 4; j++) //tip_machA
					for(int k=0; k<4; k++) //durataA
					{
						String insertMacch = "INSERT INTO proposte(premio, massimale, valore, tip_mach, durata, tip_polizza)";   
						insertMacch += " VALUES("
								+ getRandomNumberD()+ ","
								+ getRandomNumberD()+ ","
								+ getRandomNumberD()+ ",'"
								+ tip_machA[j]+ "','"
								+ durataA[k]+ "','"		
								+ tipA[i]+ "');";
						System.out.println("INSERT QUERY: "+insertMacch);
						stmt.execute(insertMacch);
					}



			//ArrayList<EmployeeBean> employees = company.getCompanyEmployees();

			conn.commit();

		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back");
			throw new SQLException(err.getMessage());
		}finally {
			if(stmt != null) stmt.close();
			if(conn != null)conn.close();
		}
	}

	public static double getRandomNumberD() {
		double s = (((Math.random() * (1000 - 1)) + 1));
		s= Math.round(s*100.0)/100.0;
		return s;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static double getDouble(String sql, String varRicercata, Connection conn) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		try {
			if(conn == null) conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getDouble(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}
		return -1;
	}
	
	public static int getInt(String sql, String varRicercata, Connection conn) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		try {
			if(conn == null) conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getInt(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}
		return -1;
	}

	public static String getString(String sql, String varRicercata, Connection conn) throws SQLException {
		Statement st;
		ResultSet rs;
		Statement stmt = null;
		try {
			if(conn == null) conn = SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno

			// ________________________________query
			try {
				st = conn.createStatement(); 
				rs = st.executeQuery(sql); // faccio la query su uno statement
				while (rs.next() == true) {
					System.out.println(varRicercata+" = "+rs.getDouble(varRicercata));

					return rs.getString(varRicercata);
				}
			} catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}

			conn.commit();
		}catch(SQLException sqle) {
			if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception err) {
			if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
			System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
			throw new SQLException(err.getMessage());
		}
		return "non trovata";
	}
}

