package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.root.base.dao.impl.SaleOrderDaoImpl;
import com.root.base.entity.SaleOrder;
import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

public class GetSaleOrderByCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetSaleOrderByCodeServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	private SaleOrderService service=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("code");
		SaleOrder order=service.getSaleOrderByCode(code);
		
		JSONObject obj=JSONObject.fromObject(order);
		String orderDate=obj.toString();
		System.out.println(orderDate);
		response.getWriter().print(orderDate);
	}

}
