package manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.util.DocumentHandler;
import net.sf.json.JSONObject;

public class PrintPurOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrintPurOrderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String ocode=request.getParameter("ocode");
		String csname=request.getParameter("csname");
		String odate=request.getParameter("odate");
		String linkman=request.getParameter("linkman");
		String tel=request.getParameter("tel");
		String fax=request.getParameter("zip");
		String trans=request.getParameter("trans");
		String ddate=request.getParameter("ddate");
		String person=request.getParameter("person");
		String remark=request.getParameter("remark");
		String ftl=request.getParameter("ftl");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ocode", ocode);
		dataMap.put("csname", csname);
		dataMap.put("odate", odate);
		dataMap.put("linkman", linkman);
		dataMap.put("tel", tel);
		dataMap.put("fax", fax);
		dataMap.put("trans", trans);
		dataMap.put("ddate", ddate);
		dataMap.put("person", person);
		dataMap.put("remark", remark);
		//导出word
		DocumentHandler doc = new DocumentHandler();
		doc.createDoc(dataMap, this.getServletContext().getRealPath("/download/PurOrder.doc"),ftl);
		JSONObject obj=new JSONObject();
		String fileName="PurOrder.doc";
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
