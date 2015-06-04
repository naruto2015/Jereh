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

public class addBaseContentServlet extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doPost(request, response);
	
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/json; charset=utf-8");
		baseContentDao bcd=new baseContentDaoImpl();
		request.setCharacterEncoding("utf-8");
		
		//获取请求参数
		String categoryCode=request.getParameter("categoryCode");
		String code=request.getParameter("code");
		String codeName=request.getParameter("codeName");
		String compName=request.getParameter("compName");
		String addUser=request.getParameter("addUser");
		String isShow=request.getParameter("isShow");
	    String compCode=bcd.findBycompName(compName);
	    System.out.println("公司名字是"+compName);
	    
	    //封装数据
	    baseContent bc=new baseContent();
	    bc.setCode(code);
	    bc.setCodeName(codeName);
	    bc.setOrderNo(code);
	    bc.setCategoryCode(categoryCode);
	    bc.setAddUser(addUser);
	    bc.setIsShow(isShow);
	    bc.setCompCode(compCode);
	    
		bcd.insertBaseContent(bc);
	    int ret=1;
		 
		 
		response.getWriter().print(ret);
	}

}
