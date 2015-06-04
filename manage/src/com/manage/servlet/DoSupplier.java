package com.manage.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.entity.CustomerSupplier;
import com.manage.service.CustomerSupplierService;
import com.manage.service.impl.CSServiceImpl;
import com.manage.util.DateUtil;


public class DoSupplier extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoSupplier() {
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
	private CustomerSupplierService css=new CSServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
       String pname=request.getParameter("pname");
     //  String date=request.getParameter("date");
       String linkman=request.getParameter("linkman");
       String telphone=request.getParameter("telphone");
       String fax=request.getParameter("fax");
       String mcode=request.getParameter("mcode");
       String email=request.getParameter("email");
       String prov=request.getParameter("province");
       String city=request.getParameter("city");
       String address=request.getParameter("address");
       String legaler=request.getParameter("legaler");
       String url=request.getParameter("url");
       String qq=request.getParameter("qq");
       String wang=request.getParameter("wangwang");
       String msn=request.getParameter("msn");
       String agent=request.getParameter("agent");
       String bank=request.getParameter("bank");
       String account=request.getParameter("account");
       String tax=request.getParameter("tax");
       String isshow=request.getParameter("isshow");
       String type=request.getParameter("type");
       String remarks=request.getParameter("remarks");
       String opt=request.getParameter("opt");
       
       CustomerSupplier cs=new CustomerSupplier();
       cs.setCode(code);
       cs.setCsName(pname);
//       try {
//		cs.setAddDate(DateUtil.toDate(date));
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
       cs.setLinkMan(linkman);
       cs.setPhone(telphone);
       cs.setFax(fax);
       cs.setPostCode(mcode);
       cs.setEmail(email);
       cs.setProvince(prov);
       cs.setCity(city);
       cs.setAddress(address);
       cs.setLegaler(legaler);
       cs.setNetAddress(url);
       cs.setQq(qq);
       cs.setWangWang(wang);
       cs.setMsn(msn);
       cs.setAgent(agent);
       cs.setBank(bank);
       cs.setAccount(account);
       cs.setTax(tax);
       cs.setIsShow(isshow);
       cs.setType(type);
       cs.setRemarks(remarks);
       if(opt.equals("1")){
          css.addCustomerSupplier(cs);
       }
       else {
    	   
    	  css.updateCustomerSupplier(cs);
       }
	  response.sendRedirect("/manage/manage/base/supplier.jsp");
       
	}

}
