package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.entity.CustomerSupplier;
import com.manage.entity.SalePrice;
import com.manage.service.SalePriceService;
import com.manage.service.impl.SPServiceImpl;

public class doSalePriceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doSalePriceServlet() {
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
	SalePriceService sps=new SPServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String priceCode=request.getParameter("priceCode");
		//String sqdate=request.getParameter("sqdate");
		String csname=request.getParameter("csname");
		String contacter=request.getParameter("contacter");
		String telphone=request.getParameter("telphone");
		String fax=request.getParameter("fax");
		String remarks=request.getParameter("remarks");
		String cuscode=request.getParameter("cuscode");
		String state=request.getParameter("state");
		String addusername=request.getParameter("addusername");
		String opt=request.getParameter("opt");
		
		SalePrice sp=new SalePrice();
		CustomerSupplier cs=new CustomerSupplier();
		sp.setCode(priceCode);
		cs.setCsName(csname);
		sp.setCs(cs);
		sp.setContacter(contacter);
		sp.setTelphone(telphone);
		sp.setFax(fax);
		sp.setRemarks(remarks);
		sp.setCustomerCode(cuscode);
		sp.setState(state);
		sp.setAddusername(addusername);
		if(opt.equals("1")){
			sps.addSalePrice(sp);
		}else{
			sps.updateSalePrice(sp);
		}
		response.sendRedirect("/manage/manage/sale/salePrice.jsp");		
	}

}
