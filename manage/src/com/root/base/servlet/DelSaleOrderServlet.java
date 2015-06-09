package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

public class DelSaleOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DelSaleOrderServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	private SaleOrderService service=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("code");
		int ret=service.delOrder(code);
		if(ret==1){
			response.getWriter().println("删除成功！");
		}else{
			response.getWriter().println("删除失败！");
		}
		
	}

}
