package com.manage.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.entity.CustomerSupplier;
import com.manage.service.CustomerSupplierService;
import com.manage.service.impl.CSServiceImpl;
import com.manage.util.DateUtil;
import com.manage.util.GetCode;

public class addBaseCustomerServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		    GetCode gc=new GetCode();
		    String code= gc.get();
		    Date date=new Date();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time=sdf.format(date);
			
			CustomerSupplier cs=new CustomerSupplier();
			cs.setCode(code);
			
			List<CustomerSupplier> list=new ArrayList<CustomerSupplier>();
			list.add(cs);
			request.setAttribute("list",list);
			
			request.getRequestDispatcher("/manage/base/list.jsp").forward(request, response);
		   
	}

}
