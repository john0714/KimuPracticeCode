package com.higasi.booksystem;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "BookList", method = RequestMethod.GET)
	public String MovetoBookList(Model model) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BookDBInfo.xml");
		ConnectDB BookSource = (ConnectDB) context.getBean("ShowDataBase");
		ArrayList<BookListEntity> BookList = new ArrayList<BookListEntity>();
		BookList = BookSource.BookListShow();
		model.addAttribute("TestList",BookList);
		
		return "BookList";
	}
}
