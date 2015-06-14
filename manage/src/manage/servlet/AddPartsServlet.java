package manage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.root.base.entity.BaseParts;

import manage.entity.PoDetail;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

public class AddPartsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddPartsServlet() {
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

		request.setCharacterEncoding("utf-8");
		String ocode=request.getParameter("ocode");
		String pcode=request.getParameter("pcode");
		String nums=request.getParameter("nums");
		List<PoDetail> pdList=new ArrayList<PoDetail>();
		PoDetail pd=new PoDetail();
		BaseParts bp=new BaseParts();
		pd.setOcode(ocode);
		bp.setPartsCode(pcode);
		pd.setPart(bp);
		pd.setNums(Integer.parseInt(nums));
		pdList.add(pd);
		ps.addDetial(pdList);

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
