package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Login {
	public int loginset(String id, String password) throws SQLException {
		String Lid = id;
		String LPW = password;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//DBConenct(DIコンテナ:XML)
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
		conn = dataSource.getConnection();
		
		String BookListSelect = "Select * from User;";
		pstmt = conn.prepareStatement(BookListSelect);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			if(Lid.equals(rs.getString("ID")) && LPW.equals(rs.getString("Password"))) {
				return rs.getInt("Management_Check");
			} 
		}
		return 100;
	}
}
