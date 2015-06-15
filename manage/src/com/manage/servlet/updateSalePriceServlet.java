package com.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manage.entity.SalePrice;
import com.manage.service.SalePriceService;
import com.manage.service.impl.SPServiceImpl;

public class updateSalePriceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public updateSalePriceServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

   private SalePriceService sps=new SPServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String code=request.getParameter("code");
			SalePrice salePrice=new SalePrice();
			salePrice=sps.searchByCode(code);
			List<SalePrice> list=new ArrayList<SalePrice>();
			list.add(salePrice);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/manage/sale/priceList.jsp").forward(request, response);
		
	}

}
