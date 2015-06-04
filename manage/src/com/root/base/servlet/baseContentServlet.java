package com.root.base.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.root.base.dao.baseContentDao;
import com.root.base.dao.impl.baseContentDaoImpl;
import com.root.base.entity.PageBean;
import com.root.base.entity.baseContent;

public class baseContentServlet extends HttpServlet {

	
	static PageBean pb=new PageBean();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		baseContent bc=new baseContent();
		String code=request.getParameter("code2");
		String codeName=request.getParameter("codeName2");
		String categoryCode=request.getParameter("categoryCode2");
	
		bc.setCode(code);
		bc.setCodeName(codeName);
		bc.setCategoryCode(categoryCode);
		
		System.out.println("code:"+code+" codeName:"+codeName+" categoryCode:"+categoryCode);
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="8";
		}
		baseContentDao bcd=new baseContentDaoImpl();
		pb=bcd.findAll(Integer.parseInt(pageNo), Integer.parseInt(pageSize), bc);
		
		
		JSONObject obj=new JSONObject();
		
		
		
		obj.put("rows", pb.getData());
		obj.put("total", pb.getRecordCount());
		
		String data=obj.toString();
		//System.out.println("后台获取数据"+pb.getData().get(0).toString());
		System.out.println("数据个数"+pb.getRecordCount());
		response.getWriter().println(data);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
