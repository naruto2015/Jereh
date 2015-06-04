package com.manage.servlet;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.manage.entity.CustomerSupplier;
import com.manage.entity.PageBean;
import com.manage.service.CustomerSupplierService;
import com.manage.service.impl.CSServiceImpl;

public class outExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public outExcelServlet() {
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
	 CustomerSupplierService customerService = new CSServiceImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//按照查询取得数据
		String pageNo = request.getParameter("page");
		String pageSize = request.getParameter("rows");
			if (pageNo==null||pageNo.equals("")){
				pageNo="1";
			}
			if (pageSize==null||pageSize.equals("")){
				pageSize="5";
			}
			CustomerSupplier bcs=new CustomerSupplier();
			String code=request.getParameter("code");
			String csName=request.getParameter("csName");
			String addDate=request.getParameter("addDate");
			if(code!=null&&!code.equals("")){
				bcs.setCode(code);
			}
			if(csName!=null&&!csName.equals("")){
				bcs.setCsName(csName);
			}
			PageBean pb = customerService.findList(bcs, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			List bcsList = pb.getData();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);

		File file = new File("D://excel//customer"+time+".xls");
		WritableWorkbook wk = Workbook.createWorkbook(file);
		WritableSheet sheet = wk.createSheet("客户与供应商报表", 0);
		//创建表头
		try {
			sheet.mergeCells(0, 0, 6, 0);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//生成一个单元格样式控制对象 设置单元格的样式
		WritableCellFormat titleFormat = new WritableCellFormat();
		//创建WritableFont字体对象 
		WritableFont titleFont = new WritableFont(
				WritableFont.createFont("黑体"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		//设置字体格式
		titleFormat.setFont(titleFont);
		
		
		//设置文本垂直居中对齐
		try {
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//设置自动换行
		try {
			titleFormat.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加Label对象 参数依次表示在第一列 第一行 使用格式
		Label lab_00  = new Label(0, 0, "客户与供应商报表", titleFormat);
		try {
			sheet.addCell(lab_00);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WritableCellFormat cloumTitleFormat = new WritableCellFormat();
		cloumTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD,false));
		try {
			cloumTitleFormat.setAlignment(Alignment.CENTRE);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Label lab_01 = new Label(0,1,"代码",cloumTitleFormat);
		Label lab_11 = new Label(1,1,"名称",cloumTitleFormat);
		Label lab_21 = new Label(2,1,"类别",cloumTitleFormat);
		Label lab_31 = new Label(3,1,"联系人",cloumTitleFormat);
		Label lab_41 = new Label(4,1,"电话", cloumTitleFormat);
		Label lab_51 = new Label(5,1,"地址",cloumTitleFormat);
		Label lab_61 = new Label(6,1,"显示状态",cloumTitleFormat);
		try {
			sheet.addCell(lab_01);
			sheet.addCell(lab_11);
			sheet.addCell(lab_21);
			sheet.addCell(lab_31);
			sheet.addCell(lab_41);
			sheet.addCell(lab_51);
			sheet.addCell(lab_61);		
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String show="";	
		String type="";
		for(int i =0 ;i<bcsList.size();i++){
			CustomerSupplier baseCustomerSupplier = (CustomerSupplier) bcsList.get(i);	
				try {
					sheet.addCell(new Label(0,i+2,baseCustomerSupplier.getCode(),cloumTitleFormat));
					sheet.addCell(new Label(1,i+2,baseCustomerSupplier.getCsName(),cloumTitleFormat));
					if(baseCustomerSupplier.getType()==null){
						type="";
					}else if(baseCustomerSupplier.getType().equals("1")){
		            	type="客户";
		            }else{
		            	type="供应商";
		            }
					sheet.addCell(new Label(2,i+2,type,cloumTitleFormat));
					sheet.addCell(new Label(3,i+2,baseCustomerSupplier.getLinkMan(),cloumTitleFormat));
					sheet.addCell(new Label(4,i+2,baseCustomerSupplier.getPhone(),cloumTitleFormat));
					sheet.addCell(new Label(5,i+2,baseCustomerSupplier.getAddress(),cloumTitleFormat));
					if(baseCustomerSupplier.getIsShow()==null){
						
						show="";
					}
					
					else if(baseCustomerSupplier.getIsShow().equals("1")){
		            	show="显示";
		            }else{
		            	show="隐藏";
		            }
					sheet.addCell(new Label(6,i+2,show,cloumTitleFormat));
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		wk.write();
		try {
			wk.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   response.sendRedirect("/manage/manage/base/supplier.jsp");
	}

}
