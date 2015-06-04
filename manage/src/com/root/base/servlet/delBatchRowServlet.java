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

public class delBatchRowServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String categoryCode=request.getParameter("categoryCode");
		String code=request.getParameter("code");
		
		baseContentDao bcd=new baseContentDaoImpl();
		baseContent bc=new baseContent();
		
		bc.setCategoryCode(categoryCode);
		bc.setCode(code);
		 
		bcd.delBaseContentByCodeAndcategoryCode(bc);
		int ret=1;
		response.getWriter().print(ret);
		
		 
	}

}
