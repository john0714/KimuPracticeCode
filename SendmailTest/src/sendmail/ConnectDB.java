package sendmail;

import java.sql.*;

/**
 * DBMS : MySQL
 * DB Server : mysql543.db.sakura.ne.jp
 * Database Name : game-mania_test
 * Database ID : game-mania
 * Database password : hayato0210 
 */

public class ConnectDB {
	public PreparedStatement connect() throws SQLException {
		//繋ぎり例 jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>
		//私のサーバー
		String DBname = "KimuDB";
		String DBid = "root";
		String DBpassword = "a108106a";
		String url = "jdbc:mysql://localhost:3306/" + DBname;
		
		//斉藤さんのサーバー
//		String DBname = "game-mania_test";
//		String DBid = "game-mania";
//		String DBpassword = "hayato0210";
//		String url = "jdbc:mysql://49.212.207.93:3306/" + DBname;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//mysql-connector
			conn=DriverManager.getConnection(url, DBid, DBpassword);
			
			String MailInfoselect = "select * from MailInformation where id = ?";
			pstmt = conn.prepareStatement(MailInfoselect);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return pstmt;
	}
}
