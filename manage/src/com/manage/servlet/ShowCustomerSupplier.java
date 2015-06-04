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

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


import com.manage.util.DateUtil;
import com.manage.util.JSONDateProcessor;
import com.manage.dao.impl.CustomerSupplierDaoImpl;
import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.service.CustomerSupplierService;
import com.manage.service.impl.CSServiceImpl;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class ShowCustomerSupplier extends HttpServlet {

 
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	 
	private CustomerSupplierService css=new CSServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		 response.setContentType("text/json; charset=utf-8");
//		 String pageNo=	request.getParameter("page");
//		 String pageSize=request.getParameter("rows");
//		 if(pageNo==null||pageNo.equals("")){
//			 pageNo="1";
//		 }	 if(pageSize==null||pageSize.equals("")){
//			 pageSize="5";
//		 }
//	      PageBean pb=css.getCustomerSupplier(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
//	      
//	      JSONObject obj=new JSONObject();
//	      obj.put("rows", pb.getData());
//	      obj.put("total",pb.getRecordCount());
//	      String data=obj.toString();
//	      System.out.println(data);
//	      System.out.println(pb.getRecordCount());
//	      response.getWriter().println(data);
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		
		CustomerSupplier cs=new CustomerSupplier();
		String code=request.getParameter("code");
		String csName=request.getParameter("csName");
		String addDate=request.getParameter("addDate");
//		SimpleDateFormat sdf=new SimpleDateFormat();
//		Date date=null;
//		
//			try {
//				date = sdf.parse(addDate);
//			} catch (java.text.ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
		JsonConfig config=new JsonConfig();
		config.setExcludes(new String[]{});
		config.registerJsonValueProcessor(Date.class,
				new JSONDateProcessor("yyyy年MM月dd日"));
		
		if(code!=null&&!code.equals("")){
			cs.setCode(code);
		}
		if(csName!=null&&!csName.equals("")){
			cs.setCsName(csName);
		}
	
	
		
//		if(addDate!=null&&!addDate.equals("")){
//			cs.setAddDate(date);
//		}

		PageBean pb=css.findList(cs,Integer.valueOf(pageNo), Integer.valueOf(pageSize));
				JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows", pb.getData());
		attrs.put("total", pb.getRecordCount());
		jsonObject.putAll(attrs,config);
		String data=jsonObject.toString();
		System.out.println(data);
        response.getWriter().println(data);
			
	}

}
