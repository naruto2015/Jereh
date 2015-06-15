package com.manage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.manage.entity.PageBean;
import com.manage.service.SalePriceService;
import com.manage.service.impl.SPServiceImpl;


public class getPriceDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getPriceDetailServlet() {
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
	private SalePriceService sps=new SPServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8");
		String code=request.getParameter("code");

		
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		PageBean pb=sps.getDetailList(code,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows", pb.getData());
		attrs.put("total", pb.getRecordCount());
		jsonObject.putAll(attrs);
		//JSONArray jsonArray=JSONArray.fromObject(pb);
		String data =jsonObject.toString();
		//String jsonChannelList=  JSONArray.toJSONString(channelList);
		System.out.println(data);
		response.getWriter().println(data);
	}

}
