package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddBook {
	
	@RequestMapping(value="AddBookData.do", method = RequestMethod.POST)
	public String MakeUser(HttpServletRequest request, Model model) {
		String BN = request.getParameter("bookname");
		String BS = request.getParameter("booksubject");
		
		if(BN == "" || BS == "" ) {
			JOptionPane.showMessageDialog(null, "全ての項目を入力してください");
			return "AddBook";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		
		try {
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
			conn = dataSource.getConnection();
			
			String SelectAccountCheck = "Select * from user";
			pstmt = conn.prepareStatement(SelectAccountCheck);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(BN.equals(rs.getString("name"))) {
					JOptionPane.showMessageDialog(null, "すでに入っている本です");
					return "AddBook";
				}
			}
			
			String InsertUserAccount = "Insert into BookList (name, subject) values('"+ BN +"', '"+ BS +"');";
			pstmt = conn.prepareStatement(InsertUserAccount);
			pstmt.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "エラー発生");
			return "AddBook";
		}
		
		ConnectDB BookSource = (ConnectDB) context.getBean("ShowDataBase");
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		BookList = BookSource.BookListShow();
		model.addAttribute("TestList",BookList);
		
		JOptionPane.showMessageDialog(null, "本の登録を完了しました");
		return "BookList";
	}
}
