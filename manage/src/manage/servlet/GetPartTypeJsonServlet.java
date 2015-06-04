package manage.servlet;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.util.JSONDateProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import manage.entity.PageBean;
import manage.service.PartTypeService;
import manage.service.impl.PartTypeServiceImpl;



public class GetPartTypeJsonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPartTypeJsonServlet() {
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
	private PartTypeService ps=new PartTypeServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		//String id=request.getParameter("id");
		String name=request.getParameter("name");
		if(pageNo==null||pageNo.equals("")){
			pageNo="1";
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize="2";
		}
		if(name==null||name.equals("--选择类别--")){
			name=null;
		}
		PageBean pb=ps.getByName(name,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		JsonConfig  config=new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JSONDateProcessor("yyyy年MM月dd日"));
		JSONObject obj=new JSONObject();
		Map attrs=new HashMap();
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
