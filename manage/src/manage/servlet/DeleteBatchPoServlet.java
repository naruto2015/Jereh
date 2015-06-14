package manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

public class DeleteBatchPoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteBatchPoServlet() {
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
		String code=request.getParameter("codes");
		String[] codes=code.split(",");
		int ret=0;
		for(int i=0;i<codes.length;i++){
			ret=ps.delete(codes[i]);
		}
		response.getWriter().println(ret);

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
