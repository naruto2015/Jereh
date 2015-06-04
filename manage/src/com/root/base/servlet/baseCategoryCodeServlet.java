package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.root.base.dao.baseContentDao;
import com.root.base.dao.impl.baseContentDaoImpl;

public class baseCategoryCodeServlet extends HttpServlet {

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		baseContentDao bcd=new baseContentDaoImpl();
		List list=bcd.findCategoryCode();
		JSONArray listobj=JSONArray.fromObject(list);
		String data=listobj.toString();
		System.out.println(data);
		response.getWriter().print(data);
		
		 
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 this.doGet(request, response);
	}

}
