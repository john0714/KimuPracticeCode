package com.higasi.booksystem;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewLog {
	
	@RequestMapping(value = "ViewLog", method = RequestMethod.GET)
	public String ViewRentalLog(Model model) {
		ArrayList<RentalLogEntity> Log = new ArrayList<RentalLogEntity>();
		
		try {
			Log = GetLogList(Log);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("LogList",Log);
		return "ViewLog";
	}
	
	private ArrayList<RentalLogEntity> GetLogList(ArrayList<RentalLogEntity> Log) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
		conn = dataSource.getConnection();
		
		String BookRentalLogTable = "Select * from BookRentalLog";
		String BookListTable = "Select id, name from BookList";
		String UserTable = "Select id_count, name, division from User";
		
		pstmt = conn.prepareStatement(BookRentalLogTable);
		ResultSet rs = pstmt.executeQuery();
		 
		while(rs.next()) {
			RentalLogEntity RLDTO = new RentalLogEntity();
			RLDTO.setID(rs.getInt("id"));
			RLDTO.setTIME(rs.getString("time"));
			
			int Bookid = rs.getInt("BookID");
			int Userid = rs.getInt("UserID");
			int RentalCheck = rs.getInt("type");
		
			pstmt = conn.prepareStatement(BookListTable);
			ResultSet rs2 = pstmt.executeQuery();
			String BookName = "";
			while(rs2.next()) {
				if(rs2.getInt("id") == Bookid) {
					BookName = rs2.getString("name");
					break;
				}
			}
			RLDTO.setBOOKNAME(BookName);
		
			pstmt = conn.prepareStatement(UserTable);
			ResultSet rs3 = pstmt.executeQuery();
			String UserInfo = "";
			while(rs3.next()) {
				if(rs3.getInt("id_count") == Userid) {
					UserInfo = rs3.getString("division") + "-" + rs3.getString("name") ;
					break;
				}
			}
			RLDTO.setPEOPLENAME(UserInfo);
			
			String RentalType = "";
			if(RentalCheck == 1) {
				RentalType = "貸出し";
			} else {	
				RentalType = "返納";
			}
			
			RLDTO.setRENTAL_CHECK(RentalType);
			
			Log.add(RLDTO);
		}
		
		conn.close();
		return Log;
	}
}
