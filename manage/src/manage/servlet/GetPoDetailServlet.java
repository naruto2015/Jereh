package manage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import manage.entity.PoDetail;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

public class GetPoDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetPoDetailServlet() {
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
		String code=request.getParameter("ocode");
		List<PoDetail> pdList=ps.getDetailByCode(code);
		JSONArray arr=JSONArray.fromObject(pdList);
		String data=arr.toString();
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
