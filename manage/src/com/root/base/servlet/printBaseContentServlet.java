package com.root.base.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

import com.root.base.dao.impl.DocumentHandler;

public class printBaseContentServlet extends HttpServlet {

 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/josn; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	 
	    String categoryCode=request.getParameter("categoryCode"); 
	    String code=request.getParameter("code"); 
	    String codeName=request.getParameter("codeName");
	    String remarks=request.getParameter("remarks"); 
	    String isShow=request.getParameter("isShow");
	    String orderNo=request.getParameter("orderNo");
	    System.out.println("print"+code);
	    System.out.println("isShow");
	    Map<String,Object> dataMap=new HashMap<String, Object>();
	    
	    dataMap.put("categoryCode", categoryCode);
	    dataMap.put("code", code);
	    dataMap.put("codeName", codeName);
	    dataMap.put("remarks", remarks);
	    dataMap.put("isShow", isShow);
	    dataMap.put("orderNo", orderNo);
	    
	    DocumentHandler mdoc=new DocumentHandler();
	    String path=this.getServletContext().getRealPath("/upload/baseContent.doc");
	       
        mdoc.createDoc(dataMap, path);  
	    List list=new ArrayList();
	    list.add(path);
        JSONArray obj=JSONArray.fromObject(list);
        
        

        System.out.println(obj);
         
	    response.getWriter().print(obj);
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doGet(request, response);
	    
		 
	}
 
 
}
