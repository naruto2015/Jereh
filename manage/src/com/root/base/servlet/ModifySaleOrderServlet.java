package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.entity.CustomerSupplier;
import com.root.base.entity.SaleOrder;
import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

public class ModifySaleOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ModifySaleOrderServlet() {
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("code");
		String orderDate=request.getParameter("orderDate");
		String customerCode=request.getParameter("customerCode");
		String nums=request.getParameter("nums");
		String numsprice=request.getParameter("numsprice");
		String contacter=request.getParameter("telphone");
		String state=request.getParameter("state");
		String businesser=request.getParameter("businesser");
		
		SaleOrder order=new SaleOrder();
		order.setCode(code);
		
		CustomerSupplier supplier=new CustomerSupplier();
		supplier.setCsName(customerCode);
		order.setCustomerCode(supplier);
		
		order.setNums(Integer.parseInt(nums));
		order.setNumsPrice(Integer.parseInt(numsprice));
		order.setContActer(contacter);
		order.setState(state);
		order.setBusinesser(businesser);
		
		int ret=service.updateOrder(order);
		if(ret==1){
			response.getWriter().print("修改成功!");
		}else{
			response.getWriter().print("修改失败!");
		}
	}

}
