package com.higasi.booksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class CreateUser {
	
	@RequestMapping(value="CreateUserInDatabase", method = RequestMethod.POST)
	public String MakeUser(HttpServletRequest request, Model model) {
		String ID = request.getParameter("idtext");
		String password = request.getParameter("pwtext");
		String nametext = request.getParameter("nametext");
		String divisiontext = request.getParameter("divisiontext");
		
		if(ID == "" || password == "" || nametext == "" || divisiontext == "") {
			JOptionPane.showMessageDialog(null, "全ての項目を入力してください");
			model.addAttribute("idtext", ID);
			model.addAttribute("pwtext", password);
			model.addAttribute("nametext", nametext);
			model.addAttribute("divisiontext", divisiontext);
			return "Createuser";
		}
		
		//正規形検査
		//*は前の設定がいくつかの文字に設定されるのを意味、{}は特定な数の文字に設定されるのを意味さますので同時に使わないです。
		Pattern p = Pattern.compile("^[a-zA-Z0-9]{8,16}$");//英語と数字だけ
		Matcher mID = p.matcher(ID);
		Matcher mPW = p.matcher(password);
		boolean check = mID.find();
		boolean check2 = mPW.find();
		
		if(!check || !check2) {
			JOptionPane.showMessageDialog(null, "IDあるいはPasswordにエラーが発生しました。\r\nIDとPasswordは必ず英語と数字を含めて8文字以上16文字以下に作ってください。");
			model.addAttribute("idtext", ID);
			model.addAttribute("pwtext", password);
			model.addAttribute("nametext", nametext);
			model.addAttribute("divisiontext", divisiontext);
			
			model.addAttribute("iderror", true);
			model.addAttribute("pwerror", true);
			return "Createuser";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		
		try {
			//DBConenct(DIコンテナ:XML)
			//Spring Frameworkでは ApplicationContextがDIコンテナの役割を担います。
			DriverManagerDataSource dataSource = (DriverManagerDataSource) context.getBean("kimuDBconnect");
			conn = dataSource.getConnection();
			
			String SelectAccountCheck = "Select * from user";
			pstmt = conn.prepareStatement(SelectAccountCheck);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(ID.equals(rs.getString("ID"))) {
					JOptionPane.showMessageDialog(null, "すでに誰か使っているIDです");
					model.addAttribute("idtext", ID);
					model.addAttribute("pwtext", password);
					model.addAttribute("nametext", nametext);
					model.addAttribute("divisiontext", divisiontext);
					
					model.addAttribute("iderror", true);
					return "Createuser";
				}
			}
			
			String InsertUserAccount = "Insert into User (ID, Password, name, division, Management_Check) values('" + ID + "', '"+ password +"', '" + nametext + "', '"+ divisiontext + "', 0)";
			pstmt = conn.prepareStatement(InsertUserAccount);
			pstmt.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "エラー発生");
			model.addAttribute("idtext", ID);
			model.addAttribute("pwtext", password);
			model.addAttribute("nametext", nametext);
			model.addAttribute("divisiontext", divisiontext);
			return "Createuser";
		}
		
		JOptionPane.showMessageDialog(null, "使用者登録を完了しました");
		return "redirect:index.jsp";
	}
}
