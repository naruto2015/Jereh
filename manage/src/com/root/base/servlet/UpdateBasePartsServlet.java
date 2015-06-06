package com.root.base.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.root.base.entity.BaseParts;
import com.root.base.service.impl.BasePartsService;

public class UpdateBasePartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateBasePartsServlet() {
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
		request.setCharacterEncoding("utf-8");
		String partscode=request.getParameter("partscode");
		String partsname=request.getParameter("partsname");
		String partscategory=request.getParameter("partscategory");
		String partsbrand=request.getParameter("partsbrand");
		String partsno=request.getParameter("partsno");
		String partsgeneralpartsno=request.getParameter("partsgeneralpartsno");
		String partsmodel=request.getParameter("partsmodel");
		String partsmodelold=request.getParameter("partsmodelold");
		String partssize=request.getParameter("partssize");
		String partsweight=request.getParameter("partsweight");
		String partsimg=request.getParameter("partsimg");
		String partsunit=request.getParameter("partsunit");
		String saleprice=request.getParameter("saleprice");
		String isshow=request.getParameter("isshow");
		String remarks=request.getParameter("remarks");
		
		BaseParts part=new BaseParts();
		part.setPartsCode(partscode);
		part.setPartsName(partsname);
		part.setPartsCategory(partscategory);
		part.setPartsBrand(partsbrand);
		part.setPartsNo(partsno);
		part.setPartsGeneralPartsNo(partsgeneralpartsno);
		part.setPartsModel(partsmodel);
		part.setPartsModelOld(partsmodelold);
		part.setPartsSize(partssize);
		part.setPartsWeight(partsweight);
		part.setPartsImg(partsimg);
		part.setPartsUnit(partsunit);
		part.setSalePrice(saleprice);
		part.setIsShow(isshow);
		part.setRemarks(remarks);
		
		partsService.updatePart(part);
		response.sendRedirect("/manage/manage/base/baseparts.jsp");
	}
}
