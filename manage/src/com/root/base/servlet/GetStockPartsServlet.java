package com.root.base.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.root.base.dao.SaleOrderDao;
import com.root.base.entity.BaseParts;
import com.root.base.entity.PageBean;
import com.root.base.entity.Stock;
import com.root.base.service.SaleOrderService;
import com.root.base.service.impl.SaleOrderServiceImpl;

/**
 * 
 * @author 王亚军
 * @date 2015-06-09
 * @pram 配件选择
 *
 */
public class GetStockPartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetStockPartsServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	private SaleOrderService service=new SaleOrderServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/json; charset=utf-8");
		String partsCode=request.getParameter("partsNo");
		String partsName=request.getParameter("partsName");
		String hCode=request.getParameter("hCode");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="5";
		}
		PageBean pageBean=null;
		BaseParts part=new BaseParts();
		Stock stock=new Stock();
		part.setPartsNo(partsCode);
		part.setPartsName(partsName);
		stock.setPcode(part);
		stock.setHcode(hCode);
		
		pageBean=service.getStockParts(Integer.parseInt(pageNo), Integer.parseInt(pageSize), stock);
		JSONObject jsonObject=new JSONObject();
		Map attrs=new HashMap();
		attrs.put("rows",pageBean.getData());
		attrs.put("total",pageBean.getRecordCount());
		jsonObject.putAll(attrs);
		String data=jsonObject.toString();
		
		
		System.out.println(data);
		response.getWriter().println(data);
	}
}
