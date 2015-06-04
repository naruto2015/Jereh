package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.root.base.dao.baseContentDao;
import com.root.base.dao.impl.baseContentDaoImpl;
import com.root.base.entity.baseContent;

public class delBaseContentServlet extends HttpServlet {

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("code");
		String categoryCode=request.getParameter("categoryCode");
		
		System.out.println(code+"   "+categoryCode);
		baseContentDao bcd=new baseContentDaoImpl();
		baseContent bc=new baseContent();
		bc.setCode(code);
		bc.setCategoryCode(categoryCode);
		
		bcd.delBaseContentByCodeAndcategoryCode(bc);
		int ret=1;
		response.getWriter().print(ret);
		
		 
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 this.doGet(request, response);
	}

}
