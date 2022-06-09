package API;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import db.SaveMySQL;

public class API_6 {

		//richiesta assistenza
	
	public String id_macchinario;
	public String id_polizza;
	public Date data;
	
	
	public String getId_macchinario() {
		return id_macchinario;
	}
	public void setId_macchinario(String id_macchinario) {
		this.id_macchinario = id_macchinario;
	}
	public String getId_polizza() {
		return id_polizza;
	}
	public void setId_polizza(String id_polizza) {
		this.id_polizza = id_polizza;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data_inizio) {
		this.data = data_inizio;
	}
	public void setData() {
		Date date = Date.valueOf(LocalDate.now());
		this.data = date;
	}
	
	
	
	// richiesta assistenza nel db
		public void generaAssistenza(int idMacchinario, int idPolizza) throws Exception {
			//SaveMySQL savePolizza = new SaveMySQL();
			Statement stmt = null;
			Connection conn = null;
			setData();
			System.out.println("INSERIMENTO richiesta assistenza");
			conn= SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			String sql = "INSERT INTO assistenza(fk_macchinario, fk_user, data)";   
			sql += " VALUES('"
					+ idMacchinario+ "','"
					+ idPolizza+ "','"
					+ getData()+ "' );";
			System.out.println("INSERT QUERY: "+sql);
			stmt.execute(sql);
			conn.commit();
		}
		
		public void generaAssistenza(API_1 Macchinario, API_4 Polizza) throws Exception {
			//SaveMySQL savePolizza = new SaveMySQL();
			Statement stmt = null;
			Connection conn = null;
			setData();
			System.out.println("INSERIMENTO richiesta assistenza");
			conn= SaveMySQL.getDBConnection();
			conn.setAutoCommit(false); // non so cosa sia
			stmt = conn.createStatement(); //nemmeno
			String sql = "INSERT INTO assistenza(fk_macchinario, fk_user, data)";   
			sql += " VALUES('"
					+ Macchinario.getIDMacchinario()+ "','"
					+ Polizza.getId_polizza()+ "','"
					+ getData()+ "' );";
			System.out.println("INSERT QUERY: "+sql);
			stmt.execute(sql);
			conn.commit();
		}
		
		public  int getId(int idPolizza, int idMacchinario) throws SQLException {
			Statement st;
			ResultSet rs;
			Statement stmt = null;
			Connection conn = null;
			try {
				System.out.println("RICERCA assistenza");
				conn = SaveMySQL.getDBConnection();
				conn.setAutoCommit(false); // non so cosa sia
				stmt = conn.createStatement(); //nemmeno
				String sql = "SELECT * FROM api.assistenza WHERE fk_polizza = '"+idPolizza+"' "
						+ "AND fk_macchinario = '"+idMacchinario+"' ;";
				try {
					st = conn.createStatement(); 
					rs = st.executeQuery(sql); // faccio la query su uno statement
					while (rs.next() == true) {
						System.out.println(rs.getInt("idssistenza"));
						System.out.println(rs.getDate("data"));
						return rs.getInt("idssistenza");
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
}
