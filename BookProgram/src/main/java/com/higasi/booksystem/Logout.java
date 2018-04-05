package com.higasi.booksystem;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logout {
	@RequestMapping(value = "Logout", method = RequestMethod.GET)
	public String loginset(HttpServletResponse response, HttpSession session, Model model) {
		session.invalidate();
		
		return "redirect:index.jsp";
	}
}
