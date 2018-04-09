package sendmail;

import java.sql.*;

/**
 * DBMS : MySQL
 * DB Server : mysql543.db.sakura.ne.jp
 * Database Name : game-mania_test
 * Database ID : game-mania
 * Database password : hayato0210 
 */

public class SetMailInfo {
	public void setMailData() throws SQLException {
		//繋ぎり例 jdbc:mysql://<HOST>:<PORT>/<DATABASE_NAME>
		//私もサーバー
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
			conn.setAutoCommit(false);
			
			//基本的にホストの情報は見せませんが、今回はテストのため書きます。
			//ホストを追加するコードも作るか。それとそこにUIを入って利用すると
			//Create MailInformation Table
			//Create Table MailInformation (id int(6) UNSIGNED Primary key, host varchar(20), pw varchar(20), port int(6), recipient varchar(50) NOT NULL);
			
			//Insert MailInfo
			String MailInfoinsert = "insert into MailInformation values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(MailInfoinsert);
			pstmt.setLong(1, 0);
			pstmt.setString(2, "smtp.naver.com");
			pstmt.setString(3, "a112536a!@");
			pstmt.setLong(4, 465);
			pstmt.setString(5, "john0714@naver.com");
			//pstmt.executeUpdate();
			//conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}
		} finally {
			conn.setAutoCommit(true);
			if(conn!=null) try{conn.close();}catch(SQLException sqle){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException sqle){}
		}
	}
}
