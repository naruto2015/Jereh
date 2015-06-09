package com.root.base.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.root.base.entity.PageBean;
import com.root.base.service.impl.ReturnService;
import com.root.base.util.JSONDateProcessor;

public class GetReturnServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	private ReturnService returnService=new ReturnService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="10";
		}
		PageBean pb=null;
		pb=returnService.getReturnList(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class,new JSONDateProcessor("yyyy-MM-dd"));
		JSONObject obj=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",pb.getData());
		attrs.put("total",pb.getRecordCount());
		obj.putAll(attrs, config);
		String data=obj.toString();
		System.out.println(data);
		response.getWriter().println(data);
	}
}