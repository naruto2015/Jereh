package manage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import manage.util.DocumentHandler;

public class PrintServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrintServlet() {
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
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String remark=request.getParameter("remark");
		String ftl=request.getParameter("ftl");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("type", type);
		dataMap.put("id", id);
		dataMap.put("name", name);
		dataMap.put("state", state);
		dataMap.put("remark", remark);
		//导出word
		DocumentHandler doc = new DocumentHandler();
		doc.createDoc(dataMap, this.getServletContext().getRealPath("/download/part.doc"),ftl);
		JSONObject obj=new JSONObject();
		String fileName="part.doc";
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
