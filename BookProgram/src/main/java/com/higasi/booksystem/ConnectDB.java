package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

//SpringFrameworkはjavaにAnnotation付けばよい、jsp-Servletて直接接続するとServletで作るか、importしなきゃならないです。
//Controllerの Annotationをつくと上に Sが付きられます。
@Controller
public class ConnectDB {
	//必ずservlet-context.xmlの中の「return value」を入らなきゃならない。
	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public String Booklist(HttpServletRequest request, HttpSession session, Model model) throws SQLException {
		String id = request.getParameter("idtext");
		String password = request.getParameter("pwtext");
		
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		BookList = BookListShow();
		
		//Login Check(DIコンテナ:XML)
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		Login LG = (Login) context.getBean("LoginConnect");
		LG.loginset(id, password);
		
		if(LG.loginset(id, password) == 0) {
			//Session設定
			session.setAttribute("id", id);
			session.setAttribute("pw", password);
		
			model.addAttribute("TestList",BookList);
			return "BookListForUser";
		}
		else if(LG.loginset(id, password) == 1) {
			//Session設定
			session.setAttribute("id", id);
			session.setAttribute("pw", password);
		
			model.addAttribute("TestList",BookList);
			return "BookList";
		} else {
			JOptionPane.showMessageDialog(null, "IDあるいはPWが間違いました。\r\n再び入力してください。");
			return "redirect:index.jsp";
		}
	}
	
	public ArrayList<BookListEntity> BookListShow() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		
		try {
			//一般的なJDBC連結
			//Class.forName("com.mysql.jdbc.Driver");//mysql-connector
			//conn=DriverManager.getConnection(url, DBid, DBpassword);
			
			//Spring JDBC Connect(DIコンテナ:XML)
			//Spring Frameworkでは ApplicationContextがDIコンテナの役割を担います。
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
			conn = dataSource.getConnection();
			
			String BookListSelect = "Select * from BookList;";
			pstmt = conn.prepareStatement(BookListSelect);
			ResultSet rs = pstmt.executeQuery();
			
			//ResultSet의 Column갯수
			//ResultSetMetaData rsmd = rs.getMetaData();
			//int columnCnt = rsmd.getColumnCount() ;
			while(rs.next()) {
				BookListEntity entity = new BookListEntity();
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setSubject(rs.getString("subject"));
				entity.setInsertTime(rs.getString("insert_time"));
				entity.setRentalCheck(rs.getInt("rental_check"));
				
				if(rs.getObject("RentalUserID") != null) {
					entity.setRentalUserID(rs.getInt("RentalUserID"));
					String SelectRentalUserID = "Select division, name from user where id_count = "+rs.getInt("RentalUserID")+";";
					PreparedStatement pstmt2 = conn.prepareStatement(SelectRentalUserID);
					ResultSet rs2 = pstmt2.executeQuery();
				
					if(rs2.next()) {
						String division = rs2.getString(1);
						String name = rs2.getString(2);
						entity.setRentalUserInfo(division+"-"+name);
					}
				}
				
				BookList.add(entity);
			}
			
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return BookList;
	}
}
