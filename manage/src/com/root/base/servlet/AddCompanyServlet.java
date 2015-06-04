package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.root.base.entity.BaseCompany;
import com.root.base.service.CompanyService;
import com.root.base.service.impl.CompanyServiceImpl;

public class AddCompanyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddCompanyServlet() {
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
	private CompanyService compService=new CompanyServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String code=request.getParameter("compcode");
		String compName=request.getParameter("compname");
		String compAddress=request.getParameter("compaddress");
		String compZip=request.getParameter("compzip");
		String compTel=request.getParameter("comptel");
		String compFax=request.getParameter("compfax");
		String compUrl=request.getParameter("compurl");
		String compEmail=request.getParameter("compemail");
		String compEgaler=request.getParameter("compegaler");
		String compAgent=request.getParameter("agent");
		String compAccount=request.getParameter("account");
		String compBank=request.getParameter("bank");
		String compTax=request.getParameter("tax");
		String remark=request.getParameter("remark");
		
		BaseCompany comp=new BaseCompany();
		comp.setCode(code);
		comp.setCompName(compName);
		comp.setCompAddress(compAddress);
		comp.setCompPostCode(compZip);
		comp.setCompPhone(compTel);
		comp.setCompFax(compFax);
		comp.setCompUrl(compUrl);
		comp.setCompEmail(compEmail);
		comp.setCompEgaler(compEgaler);
		comp.setCompAgent(compAgent);
		comp.setCompAccount(compAccount);
		comp.setCompBank(compBank);
		comp.setCompTax(compTax);
		comp.setRemarks(remark);
		
		int ret=compService.addCompany(comp);
		if(ret==1){
			request.setAttribute("aler", "�ɹ�");
		}else{
			request.setAttribute("aler", "ʧ��");
		}
		response.sendRedirect("/manage/manage/base/baseCompany.jsp");
	}

}
