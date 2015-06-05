package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.util.Document;





public class outWordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public outWordServlet() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);
		
		String code = request.getParameter("hcode");
	    String addDate = new String(request.getParameter("hdate").getBytes("iso8859-1"),"utf-8");
		String csName = new String(request.getParameter("hpname").getBytes("iso8859-1"),"utf-8");
		String contacter = new String(request.getParameter("hlinkman").getBytes("iso8859-1"),"utf-8");
		String telephone = request.getParameter("htelphone");
		String fax = request.getParameter("hfax");
		String postCode = request.getParameter("hmcode");
		String email = request.getParameter("hemail");
		String province = request.getParameter("hprovince");
		String city = request.getParameter("hcity");
		String address=new String(request.getParameter("haddress").getBytes("iso8859-1"),"utf-8"); 

		String legaler = request.getParameter("hlegaler");
		String url = request.getParameter("hurl");
		String QQ = request.getParameter("hqq");
		String MSN = request.getParameter("hmsn");
		String aliwang = request.getParameter("hwangwang");
		String agent = request.getParameter("hagent");
		String bank = request.getParameter("hbank");
		String account = request.getParameter("haccount");
		String tax = request.getParameter("htax");
		String categorycode = request.getParameter("htype");
		String isShow = request.getParameter("hisshow");
		String remarks = request.getParameter("hremarks");
		
		
		 Map<String, Object> dataMap = new HashMap<String, Object>(); 		 
		 dataMap.put("code", code);
		dataMap.put("addDate", addDate);
		dataMap.put("csName", csName);
		 dataMap.put("contacter",contacter);
		 dataMap.put("telephone",telephone);
		 dataMap.put("fax", fax);
		 dataMap.put("postCode", postCode);
		 dataMap.put("email", email);
		 dataMap.put("province", province);
		 dataMap.put("city", city);
		 dataMap.put("address", address);
		 dataMap.put("legaler",legaler);
		 dataMap.put("url", url);
		 dataMap.put("QQ", QQ);
		 dataMap.put("MSN", MSN);
		dataMap.put("aliwang", aliwang);
		dataMap.put("agent", agent);
		dataMap.put("bank", bank);
		dataMap.put("account", account);
		dataMap.put("tax", tax);
		dataMap.put("categorycode", categorycode);
		dataMap.put("isShow", isShow);
		dataMap.put("remarks", remarks);
		
		Document doc = new Document();
		doc.createDoc(dataMap,"D://Document//Document"+time+".doc");
	}

}
