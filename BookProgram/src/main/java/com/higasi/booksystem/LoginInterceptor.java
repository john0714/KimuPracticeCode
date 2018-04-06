package com.higasi.booksystem;

import javax.servlet.http.*;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//interceptor연습, 보존
 		HttpSession session = request.getSession();
		String check = (String) session.getAttribute("id");
		
		System.out.println("인터셉터 로그인여부:" + check);
		if (check == null){
			response.sendRedirect("index.jsp");
			return false;
		}
		return true;
	}
}
