package manage.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.manage.entity.CustomerSupplier;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;
import manage.util.JSONDateProcessor;

public class GetPurorderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPurorderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	private PoService ps=new PoServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
		String date1=request.getParameter("beginDate");
		String date2=request.getParameter("endDate");
		String csname=request.getParameter("csname");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM月-yyyy");
		Po po=null;
		try {
			Date odate = sdf.parse(date1);
			Date ddate=sdf.parse(date2);
			po=new Po();
			CustomerSupplier cs=new CustomerSupplier();
			po.setCode(code);
			po.setOdate(odate);
			po.setDdate(ddate);
			cs.setCsName(csname);
			po.setSupplier(cs);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="2";
		}
		PageBean pb=ps.getByConditions(po, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		JsonConfig  config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JSONDateProcessor("yyyy年MM月dd日"));
		JSONObject obj=new JSONObject();
		Map<Object,Object> attrs=new HashMap<Object,Object>();
		attrs.put("rows",pb.getData());
		attrs.put("total", pb.getRecordCount());
		obj.putAll(attrs,config);
		String data=obj.toString(); 
		//System.out.println(data);
		response.getWriter().println(data);
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
