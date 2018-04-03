package com.higasi.booksystem;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//web.xml에서 지정한 경로 이후의 Mapping작업을 진행한다. 초기설정에선 WEB-INF/views + / + return값(home) + jsp 가 된다.
	@RequestMapping(value = "Home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Client의 국적(접속장소)출력
		logger.info("Welcome home! The client locale is {}.", locale);		

		//date를 formattedDate에 입력
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		//model의 변수 serverTime에 date값 입력
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}
