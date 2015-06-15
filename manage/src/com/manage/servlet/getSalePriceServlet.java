package com.manage.servlet;

import java.io.IOException;
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

import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.entity.SalePrice;
import com.manage.service.SalePriceService;
import com.manage.service.impl.SPServiceImpl;
import com.manage.util.JSONDateProcessor;

public class getSalePriceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getSalePriceServlet() {
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
		response.setContentType("text/json; charset=utf-8");
		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		
		SalePrice sp=new SalePrice();
		CustomerSupplier cs=new CustomerSupplier();
		String code=request.getParameter("code");
		String csName=request.getParameter("pname");
		String sqdate=request.getParameter("startdate");
		String adddate=request.getParameter("enddate");
//		SimpleDateFormat sdf=new SimpleDateFormat();
//		Date sqdate1=null;
//		Date adddate1=null;
//			try {
//				sqdate1 = sdf.parse(sqdate);
//				adddate1=sdf.parse(adddate);
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
			sp.setCode(code);
		}
		if(csName!=null&&!csName.equals("")){
			cs.setCsName(csName);
			sp.setCs(cs);
		}
//	    if(sqdate!=null&&!sqdate.equals("")){
//	    	sp.setSqdate(sqdate1);
//	    }
//	    if(adddate!=null&&!adddate.equals("")){
//	    	sp.setAdddate(adddate1);
//	    }	
//		if(addDate!=null&&!addDate.equals("")){
//			cs.setAddDate(date);
//		}

		PageBean pb=sps.getList(sp,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
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


