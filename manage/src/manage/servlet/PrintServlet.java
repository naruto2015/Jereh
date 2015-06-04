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

		response.setContentType("text/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String remark=request.getParameter("remark");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("type", type);
		dataMap.put("id", id);
		dataMap.put("name", name);
		dataMap.put("state", state);
		dataMap.put("remark", remark);
		//导出word
		DocumentHandler doc = new DocumentHandler();
		doc.createDoc(dataMap, "E:/配件类别信息.doc");
		int data=1;
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
