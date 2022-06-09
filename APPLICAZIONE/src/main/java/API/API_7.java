package API;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import db.SaveMySQL;

public class API_7 {

	// api sinistro
		
	public int id;
	public int id_user;
	public int id_polizza;
	public Date data;
	
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_polizza() {
		return id_polizza;
	}
	public void setId_polizza(int id_polizza) {
		this.id_polizza = id_polizza;
	}
	public void setData(Date data_inizio) {
		this.data = data_inizio;
	}
	public void setData() {
		Date date = Date.valueOf(LocalDate.now());
		this.data = date;
	}
	public Date getData() {
		return data;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
	
		// richiesta assistenza nel db
		public void generaSinistro(int idPolizza, int idUser) throws Exception {
			//SaveMySQL savePolizza = new SaveMySQL();
			Statement stmt = null;
			Connection conn = null;
			setData();
			boolean ok =false;
			System.out.println("INSERIMENTO sinistro");
			conn= SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			try {
				// controllo esistenza polizza-user
				System.out.println("id user --->"+idUser);
				String sql = "SELECT * FROM polizza WHERE idpolizza ='"+idPolizza+"' and fk_user= '"+idUser+"' ;";
				stmt.execute(sql);
				ok = true;
			}catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			// se esiste la corrispondenza si può inserire il sinistro, se no NO
			if(ok) {
			String sql = "INSERT INTO sinistro(fk_polizza, data, fk_user)";   
			sql += " VALUES('"
					+ idPolizza+ "','"
					+ getData()+ "','"
					+  idUser + "' );";
			System.out.println("INSERT QUERY: "+sql);
			stmt.execute(sql);
			int i = getId(idPolizza); //1
			this.id = i;
			this.data = getData();
			this.id_polizza = idPolizza;
			conn.commit();
			}
			else {
				System.out.println("non esiste la polizza-user");
			}
		}
		// ricerca user-polizza
		public void generaSinistro(API_4 Polizza, int idUser) throws Exception {
			//SaveMySQL savePolizza = new SaveMySQL();
			Statement stmt = null;
			Connection conn = null;
			setData();
			System.out.println("INSERIMENTO sinistro1");
			conn= SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			boolean ok = false;
			int idPol = Polizza.getId_polizza(); //2
			try {
				// controllo esistenza polizza-user
				
				String sql = "SELECT * FROM polizza WHERE idpolizza ='"+idPol+"' and fk_user= '"+idUser+"' ;";
				stmt.execute(sql);
				ok = true;
			}catch (SQLException e) {
				System.out.println("errore:" + e.getMessage());
			}
			if(ok) {
			String sql = "INSERT INTO sinistro(fk_polizza, data)";   
			sql += " VALUES('"
					+  idPol+ "','"
					+ getData()+ "' );";
			System.out.println("INSERT QUERY: "+sql);
			stmt.execute(sql);
			conn.commit();
			
			}else {
				System.out.println("non esiste la polizza-user");
			}
		}
		
		public  int getId(int idPolizza) throws SQLException {
			Statement st;
			ResultSet rs;
			Statement stmt = null;
			Connection conn = null;
			try {
				System.out.println("RICERCA id sinistro");
				conn = SaveMySQL.getDBConnection();
				conn.setAutoCommit(false); // non so cosa sia
				stmt = conn.createStatement(); //nemmeno
				String sql = "SELECT * FROM sinistro WHERE fk_polizza = '"+idPolizza+"' ;";
				try {
					st = conn.createStatement(); 
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						System.out.println("ID SINISTRO "+rs.getInt("idsinistro"));
						System.out.println("DATA "+rs.getDate("data"));
						this.id = rs.getInt("idsinistro");
						return rs.getInt("idsinistro");
					}
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
				}
				conn.commit();
				return -1;
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
		}
		
		public  Date getdata(int idSinistro) throws SQLException {
			Statement st;
			ResultSet rs;
			Statement stmt = null;
			Connection conn = null;
			try {
				System.out.println("RICERCA data sinistro");
				conn = SaveMySQL.getDBConnection();
				conn.setAutoCommit(false); // non so cosa sia
				stmt = conn.createStatement(); //nemmeno
				String sql = "SELECT * FROM sinistro WHERE idsinistro = '"+idSinistro+"' ;";
				try {
					st = conn.createStatement(); 
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						System.out.println("ID SINISTRO"+rs.getInt("idsinistro"));
						System.out.println("DATA "+rs.getDate("data"));
						this.data = rs.getDate("data");
						return rs.getDate("data");
					}
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
				}
				conn.commit();
				return null;
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
		}
		
		
		
		//metodo utilizzato per fornire tutte le polizze relative ad un user
		public API_7[] getSinistri(int idUser) throws SQLException{
				Statement st;
				ResultSet rs;
				Statement stmt = null;
				Connection conn = null;
				int i =0;
				try {
					System.out.println("RICERCA SINISTRI");
					conn = SaveMySQL.getDBConnection();
					int k = numSinistri(idUser, conn);
					API_7 api[] = new API_7[k];
					conn.setAutoCommit(false); // non so cosa sia
					stmt = conn.createStatement(); //nemmeno


					String sql2 = "SELECT * FROM sinistro WHERE fk_user = '"+idUser+"';";
					// ________________________________query
					try {
						st = conn.createStatement(); 
						rs = st.executeQuery(sql2); // faccio la query su uno statement
						while (rs.next() == true && i<k) {
							api[i] = new API_7();
							/////////////////////
							System.out.println(rs.getInt("idsinistro"));
							System.out.println(rs.getInt("fk_polizza"));
							System.out.println(rs.getDate("data"));
							System.out.println(rs.getInt("fk_user"));
							
							api[i].setId(rs.getInt("idsinistro"));
							api[i].setData(rs.getDate("data"));
						    api[i].setId_polizza(rs.getInt("fk_polizza"));
						    api[i].setId_user(rs.getInt("fk_user"));
						
							i++;
							
						}
					} catch (SQLException e) {
						System.out.println("errore:" + e.getMessage());
					}
					conn.commit();
					return api;
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
			}	
		
		//metodo utilizzato per rilevare il numero di polizze inerenti ad un user
		// viene utilizzato per instanziare l'array delle proposte del metodo sopra
		public int numSinistri(int id_user, Connection conn) throws SQLException {
			Statement st;
			ResultSet rs;
			Statement stmt = null;
			int i =0;
			try {
				System.out.println("RICERCA NUMERO SINISTRI");
				if(conn == null)conn = SaveMySQL.getDBConnection();
				conn.setAutoCommit(false); // non so cosa sia
				stmt = conn.createStatement(); //nemmeno


				String sql2 = "SELECT * FROM sinistro WHERE fk_user = '"+id_user+"';";
				// ________________________________query
				try {
					st = conn.createStatement(); 
					rs = st.executeQuery(sql2); // faccio la query su uno statement
					while (rs.next() == true ) {
						i = rs.getRow();
						System.out.println("numero sinistri -->" + i);
						
					}
				} catch (SQLException e) {
					System.out.println("errore:" + e.getMessage());
				}

				conn.commit();
				return i;
			}catch(SQLException sqle) {
				if(conn!= null) {conn.rollback(); System.out.println("VUOTO");}
				System.out.println("INSERT ERROR: Transaction is being rolled back   cccc");
				throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			}catch(Exception err) {
				if(conn != null ) {conn.rollback(); System.out.println("VUOTO");}
				System.out.println("GENERIC ERROR: Transaction is being rolled back    cccc");
				throw new SQLException(err.getMessage());
			}
		}
		
		
		
}
