package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Search {
	
	@RequestMapping(value = "BookListSearch", method = RequestMethod.POST)
	public String SearchResult(HttpServletRequest request, Model model) {
		String Subject = request.getParameter("SCheck");
		String SearchText = request.getParameter("SearchText");
		
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		BookList = BookListShow(Subject, SearchText);
		
		model.addAttribute("TestList",BookList);
		model.addAttribute("SCheck", Subject);
		model.addAttribute("SearchText", SearchText);
		return "BookList";
	}
	
	public ArrayList<BookListEntity> BookListShow(String Subject, String SearchText) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		
		try {
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
			conn = dataSource.getConnection();
			
			String BookListSelect = "Select * from BookList where "+ Subject +" Like '%"+SearchText+"%' ;";
			pstmt = conn.prepareStatement(BookListSelect);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookListEntity entity = new BookListEntity();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setSubject(rs.getString("subject"));
				entity.setInsertTime(rs.getString("insert_time"));
				entity.setRentalCheck(rs.getInt("rental_check"));
				
				BookList.add(entity);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return BookList;
	}
}
