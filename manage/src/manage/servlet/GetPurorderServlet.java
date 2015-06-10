package manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.manage.entity.CustomerSupplier;

import manage.entity.PageBean;
import manage.entity.Po;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

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
		String odate=request.getParameter("beginDate");
		String ddate=request.getParameter("endDate");
		String csname=request.getParameter("csname");
		Po po=new Po();
		CustomerSupplier cs=new CustomerSupplier();
		if(code==null||code.equals("")){
			code=null;
		}
		if(odate==null||odate.equals("")){
			odate=null;
		}
		if(ddate==null||ddate.equals("")){
			ddate=null;
		}
		if(csname==null||csname.equals("")){
			csname=null;
		}
		po.setCode(code);
		po.setOdate(odate);
		po.setDdate(ddate);
		cs.setCsName(csname);
		po.setSupplier(cs);
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="2";
		}
		PageBean pb=ps.getByConditions(po, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		JSONObject obj=new JSONObject();
		obj.put("rows",pb.getData());
		obj.put("total", pb.getRecordCount());
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
