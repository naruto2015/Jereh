package manage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manage.entity.Po;
import manage.service.PoService;
import manage.service.impl.PoServiceImpl;

import com.manage.entity.CustomerSupplier;

public class UpdatePurorderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdatePurorderServlet() {
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
		String code=request.getParameter("code1");
		String supplierCode=request.getParameter("supplierCode");
		String linkman=request.getParameter("linkman");
		String tel=request.getParameter("tel");
		String fax=request.getParameter("zip");
		String trans=request.getParameter("way");
		if(trans.equals("圆通快递")){
			trans="0";
		}else if(trans.equals("EMS")){
			trans="1";
		}else{
			trans="2";
		}
		String ddate=request.getParameter("ddate");
		String person=request.getParameter("person");
		String remark=request.getParameter("remarks");
		String operator=request.getParameter("operator");
		String nums=request.getParameter("nums1");
		String numsPrice=request.getParameter("numsPrice1");
		String state=request.getParameter("state");
		Po po=new Po();
		CustomerSupplier cs=new CustomerSupplier();
		po.setCode(code);
		cs.setCode(supplierCode);
		po.setSupplier(cs);
		po.setLinkman(linkman);
		po.setTel(tel);
		po.setFax(fax);
		po.setTrans(trans);
		po.setDdate(ddate);
		po.setBusinesser(person);
		po.setRemark(remark);
		po.setNums(Integer.parseInt(nums));
		po.setAmount(Integer.parseInt(numsPrice));
		po.setOperator(operator);
		po.setState(state);
		ps.update(po);
		response.sendRedirect("../manage/purchase/purorder.jsp");

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
