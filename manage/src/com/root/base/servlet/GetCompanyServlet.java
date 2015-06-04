package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.root.base.entity.BaseCompany;
import com.root.base.service.CompanyService;
import com.root.base.service.impl.CompanyServiceImpl;
import com.root.util.DateUtil;

public class GetCompanyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCompanyServlet() {
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
	private CompanyService companyService=new CompanyServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		BaseCompany compCode=(BaseCompany)request.getSession().getAttribute("code");
		//ʹ�õ�ǰ��˾��code ������Ϣ
		BaseCompany comp=companyService.getCompanyInfo(1);
		if(comp!=null){
			JSONObject compObj=JSONObject.fromObject(comp);
			String compInfo=compObj.toString();
			response.getWriter().print(compInfo);
			System.out.println(compInfo);
		}else{
			
			response.getWriter().print("");
		}
	}

}
