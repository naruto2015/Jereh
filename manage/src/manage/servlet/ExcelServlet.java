package manage.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
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

import manage.entity.PageBean;
import manage.entity.Po;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

public class ExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExcelServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	private PoService ps=new PoServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		Po po=new Po();
		CustomerSupplier cs=new CustomerSupplier();
		po.setCode(null);
		po.setOdate(null);
		po.setDdate(null);
		cs.setCsName(null);
		po.setSupplier(cs);
//		if(pageNo==null||pageNo.equals("")){
//			pageNo="1";
//		}
//		if(pageSize==null||pageSize.equals("")){
//			pageSize="2";
//		}
		PageBean pb=ps.getByConditions(po, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<Po> poList=pb.getData();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String time = sdf.format(date);
		File file = new File(this.getServletContext().getRealPath("/download/PurOrder"+time+".xls"));
		WritableWorkbook wk = Workbook.createWorkbook(file);
		WritableSheet sheet = wk.createSheet("采购订单报表", 0);
		//创建表头
		try {
			sheet.mergeCells(0, 0, 9, 0);
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
		Label lab_00  = new Label(0, 0, "采购订单报表", titleFormat);
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
		Label lab_01 = new Label(0,1,"订单编号",cloumTitleFormat);
		Label lab_11 = new Label(1,1,"订单日期",cloumTitleFormat);
		Label lab_21 = new Label(2,1,"供应商名",cloumTitleFormat);
		Label lab_31 = new Label(3,1,"数量",cloumTitleFormat);
		Label lab_41 = new Label(4,1,"金额",cloumTitleFormat);
		Label lab_51 = new Label(5,1,"联系人",cloumTitleFormat);
		Label lab_61 = new Label(6,1,"联系方式", cloumTitleFormat);
		Label lab_71 = new Label(7,1,"审核状态",cloumTitleFormat);
		Label lab_81 = new Label(8,1,"操作员",cloumTitleFormat);
		try {
			sheet.addCell(lab_01);
			sheet.addCell(lab_11);
			sheet.addCell(lab_21);
			sheet.addCell(lab_31);
			sheet.addCell(lab_41);
			sheet.addCell(lab_51);
			sheet.addCell(lab_61);
			sheet.addCell(lab_71);
			sheet.addCell(lab_81);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0 ;i<poList.size();i++){
			Po po1=poList.get(i);	
				try {
					sheet.addCell(new Label(0,i+2,po1.getCode(),cloumTitleFormat));
					sheet.addCell(new Label(1,i+2,po1.getOdate(),cloumTitleFormat));
					sheet.addCell(new Label(2,i+2,po1.getSupplier().getCsName(),cloumTitleFormat));
					sheet.addCell(new Label(3,i+2,String.valueOf(po1.getNums()),cloumTitleFormat));
					sheet.addCell(new Label(4,i+2,String.valueOf(po1.getAmount()),cloumTitleFormat));
					sheet.addCell(new Label(5,i+2,po1.getLinkman(),cloumTitleFormat));
					sheet.addCell(new Label(6,i+2,po1.getTel(),cloumTitleFormat));
					sheet.addCell(new Label(7,i+2,po1.getState(),cloumTitleFormat));
					sheet.addCell(new Label(8,i+2,po1.getOperator(),cloumTitleFormat));
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
		JSONObject obj=new JSONObject();
		String fileName="PurOrder"+time+".xls";
		obj.put("fileName", fileName);
		String data=obj.toString();
		response.getWriter().println(data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
