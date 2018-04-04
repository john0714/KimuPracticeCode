package com.higasi.booksystem;

import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logout {
	@RequestMapping(value = "Logout", method = RequestMethod.GET)
	public String loginset(HttpSession session, Model model) {
		session.invalidate(); 
		
		//JOptionPane.showMessageDialog(null, "ログアウトしました!");
		return "redirect:index.jsp";
	}
}
