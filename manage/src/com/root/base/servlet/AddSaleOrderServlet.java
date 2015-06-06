package com.root.base.servlet;

import java.io.IOException; 
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.util.DateUtil;
import com.root.base.entity.SaleOrder;
import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

public class AddSaleOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddSaleOrderServlet() {
		super();
	}

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
	private SaleOrderService sds=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String orderCode=request.getParameter("orderCode");
		String orderDate=request.getParameter("orderDate");
		String clientName=request.getParameter("clientName");
		String contacter=request.getParameter("contacter");
		String tel=request.getParameter("tel");
		String fax=request.getParameter("fax");
		String trans=request.getParameter("trans");
		String businesser=request.getParameter("bussnesser");
		String remarks=request.getParameter("remark");
		String delivery=request.getParameter("delivery");
		DateUtil du=new DateUtil();
		SaleOrder order=new SaleOrder();
		order.setCode(orderCode);
		try {
			order.setOrderDate(du.toDate(orderDate));
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setCustomerCode(clientName);
		order.setContActer(contacter);
		order.setTel(tel);
		order.setFax(fax);
		order.setTrans(trans);
		order.setBusinesser(businesser);
		order.setRemarks(remarks);
		try {
			order.setDeliveryDate(du.toDate(delivery));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int ret=sds.addSaleOrder(order);
		if(ret==1){
			response.sendRedirect("manage/sale/order.jsp");
		}
	}

}
