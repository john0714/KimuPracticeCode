package com.higasi.booksystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Move {
	@RequestMapping(value = "UserCreate", method = RequestMethod.GET)
	public String MovetoCreateUser() {
		return "Createuser";
	}
	
	@RequestMapping(value = "AddBook", method = RequestMethod.GET)
	public String MovetoAddBook() {
		return "AddBook";
	}
}
