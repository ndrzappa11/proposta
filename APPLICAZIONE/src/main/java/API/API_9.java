package API;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import db.SaveMySQL;

public class API_9 {
	
		// API MONITORAGGIO DEL SINISTRO
	
	public String stato;
	public String descrizione;
	public String doc; //gestire i pdf, mi sa con un altra classe
	public Date data;
	
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
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
					//this.id = rs.getInt("idsinistro");
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
	
	
	// stato del sinistro nel db
	public void generaStato(int sinistro, String stato, String descrizione, String doc) throws Exception {
		//SaveMySQL savePolizza = new SaveMySQL();
		Statement stmt = null;
		Connection conn = null;
		setData();
		System.out.println("INSERIMENTO stato sinistro");
		conn= SaveMySQL.getDBConnection();
		conn.setAutoCommit(false); // non so cosa sia
		stmt = conn.createStatement(); //nemmeno
		// se esiste la corrispondenza si può inserire il sinistro, se no NO
		String sql = "INSERT INTO stato_sinistro(fk_sinistro, stato, descrizione, data)";   
		sql += " VALUES('"
				+ sinistro+ "','"
				+ stato+ "','"
				+ descrizione+ "','"
				+ getData()+ "' );";
		System.out.println("INSERT QUERY: "+sql);
		stmt.execute(sql);
		conn.commit();
		
	}
	
}
