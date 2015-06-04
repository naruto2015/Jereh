package manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.entity.PartType;
import manage.service.PartTypeService;
import manage.service.impl.PartTypeServiceImpl;

public class AddPartTypeSevlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddPartTypeSevlet() {
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
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id1");
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String state=request.getParameter("state");
		String remark=request.getParameter("remark");
		String operator=request.getParameter("operator");
		String optname=request.getParameter("optname");
		
		PartType part=new PartType();
		part.setId(id);
		part.setName(name);
		part.setState(state);
		part.setRemark(remark);
		part.setOperator(operator);
		part.setOptname(optname);
		ps.add(part);
		response.sendRedirect("../manage/base/PartTypeManage.jsp");
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
