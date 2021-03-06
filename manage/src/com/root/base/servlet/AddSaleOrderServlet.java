package com.root.base.servlet;

import java.io.IOException;   
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.manage.entity.CustomerSupplier;
import com.root.base.entity.SaleOrder;
import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

public class AddSaleOrderServlet extends HttpServlet {

	
	public AddSaleOrderServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	private SaleOrderService sds=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String orderCode=request.getParameter("orderCode");
		String orderDate=request.getParameter("orderDate");
		String clientName=request.getParameter("clientName");
		String contacter=request.getParameter("contacter");
		String tel=request.getParameter("tel");
		String fax=request.getParameter("fax");
		String trans=request.getParameter("trans");
		String businesser=request.getParameter("businesser");
		String remarks=request.getParameter("remark");
		String delivery=request.getParameter("delivery");
		SaleOrder order=new SaleOrder();
		order.setCode(orderCode);
			order.setOrderDate(orderDate);
			order.setDeliveryDate(delivery);
		
		
		CustomerSupplier supplier=new CustomerSupplier();
		supplier.setCsName(clientName);
		order.setCustomerCode(supplier);
		
		order.setContActer(contacter);
		order.setTel(tel);
		order.setFax(fax);
		order.setTrans(trans);
		order.setBusinesser(businesser);
		order.setRemarks(remarks);
	
		System.out.println(order.getOrderDate());		
		int ret=sds.addSaleOrder(order);
		if(ret==1){
			response.sendRedirect("/manage/manage/sale/order.jsp");
		}
	}

}
