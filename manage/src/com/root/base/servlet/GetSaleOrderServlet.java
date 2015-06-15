package com.root.base.servlet;

import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.root.base.dao.SaleOrderDao;
import com.root.base.dao.impl.SaleOrderDaoImpl;
import com.root.base.entity.PageBean;
import com.root.base.entity.SaleOrder;

public class GetSaleOrderServlet extends HttpServlet {

	
	public GetSaleOrderServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	SaleOrderDao saleDao=new SaleOrderDaoImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("orderCode");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		if(pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize.equals("")){
			pageSize="10";
		}
		
		SaleOrder saleOrder=new SaleOrder();
			saleOrder.setCode(code);
			saleOrder.setOrderDate(startDate);
			saleOrder.setDeliveryDate(endDate);
		
		
		
		PageBean pageBean=saleDao.findSaleOrder(Integer.parseInt(pageNo), Integer.parseInt(pageSize),saleOrder);
		JsonConfig config=new JsonConfig();
		Map arrs=new HashMap();
		arrs.put("rows", pageBean.getData());
		arrs.put("total",pageBean.getRecordCount());
		JSONObject obj=new JSONObject();
		obj.putAll(arrs);
		String data=obj.toString();
		System.out.print(data);
		response.getWriter().print(data);
	}

}
