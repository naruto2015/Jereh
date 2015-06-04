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

public class updateBaseContentServlet extends HttpServlet {

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
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
	     
	    System.out.println("杰瑞"+compCode+"  "+code);
	    
	    //封装数据
	    baseContent bc=new baseContent();
	    bc.setCode(code);
	    bc.setCodeName(codeName);
	    bc.setOrderNo(code);
	    bc.setCategoryCode(categoryCode);
	    bc.setAddUser(addUser);
	    bc.setIsShow(isShow);
	    bc.setCompCode(compCode);
	    
	    bcd.updateBaseContentByCode(bc);
	    int ret=1;
	    response.getWriter().print(ret);
		System.out.println("修改成功！");
		 
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		this.doGet(request, response);
	}

}
