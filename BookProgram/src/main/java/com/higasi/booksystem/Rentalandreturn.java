package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Rentalandreturn {
	
	@RequestMapping(value = "rentalandreturn", method = RequestMethod.POST)
	public String rentalandreturn(HttpServletRequest request, Model model) {
		if(Token.isValid(request)) {
		    Token.set(request);
		    request.setAttribute("TOKEN_SAVE_CHECK", "TRUE");
		} else {
		     request.setAttribute("TOKEN_SAVE_CHECK", "FALSE");
		}
		
		String id = request.getParameter("id");
		String RC = request.getParameter("RC");
		String UC = request.getParameter("UserCode");
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		ConnectDB BookSource = (ConnectDB) context.getBean("ShowDataBase");

		if("TRUE".equals(request.getAttribute("TOKEN_SAVE_CHECK"))) {
			if(RC.equals("1")) {
				BookReturn(id, UC);
			} else {
				Rental(id, UC);
			}
		} else {
			JOptionPane.showMessageDialog(null, "処理中あるいは変わった操作です。");
		}
		
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		BookList = BookSource.BookListShow();
		model.addAttribute("TestList",BookList);
		
		return "BookList";
	}
	
	private void Rental(String id, String UC) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
			
			try {
				conn = dataSource.getConnection();
				String RentalSQL = "Update BookList Set rental_check = 1, RentalUserID = "+ UC +" where id = "+ id + ";";
				String RentalLogSQl = "Insert into BookRentalLog (BookID, UserID, type) values ('"+ id +"', '"+ UC +"', 1);";
				pstmt = conn.prepareStatement(RentalSQL);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(RentalLogSQl);
				pstmt.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	private void BookReturn(String id, String UC) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
		
		try {
			conn = dataSource.getConnection();
			String RentalSQL = "Update BookList Set rental_check = 0, RentalUserID = null where id = "+ id + ";";
			String RentalLogSQl =  "Insert into BookRentalLog (BookID, UserID, type) values ('"+ id +"', '"+ UC +"', 0);";
			pstmt = conn.prepareStatement(RentalSQL);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(RentalLogSQl);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
