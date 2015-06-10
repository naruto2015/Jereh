package com.root.base.servlet;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;
import com.root.base.service.impl.BasePartsService;
import com.root.base.util.JSONDateProcessor;

public class GetBasePartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetBasePartsServlet() {
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
	private BasePartsService partsService=new BasePartsService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		String partsno=request.getParameter("partsNo");
		String partsname=request.getParameter("partsName");
		String partscategory=request.getParameter("partsCategory");
		//System.out.println(partsno+partsname+partscategory);
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		PageBean pb=null;
		BaseParts part=new BaseParts();
		part.setPartsNo(partsno);
		part.setPartsName(partsname);
		part.setPartsCategory(partscategory);
		if(partsno!=null||partsname!=null||partscategory!=null){
			pb=partsService.getByNoNameCategory(part,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		}else{
			pb=partsService.getBasePartsList(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		}
		
		JsonConfig config=new JsonConfig();
		config.setExcludes(new String[]{"spell","partsgeneralpartsno","partssize","partsweight","partsimg","partsunit","costprice","addDate","adduser","addip","compcode"});
		config.registerJsonValueProcessor(Date.class,new JSONDateProcessor("yyyy��MM��dd��"));
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",pb.getData());
		attrs.put("total",pb.getRecordCount());
		jsonObject.putAll(attrs,config);
		String data=jsonObject.toString();
		
//		JSONArray j=JSONArray.fromObject(pb);
//		String data=j.toString();
		
		System.out.println(data);
		response.getWriter().println(data);
	}
}
