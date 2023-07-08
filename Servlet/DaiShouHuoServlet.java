package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.DingDanBiaoDao;
import org.YaoLeGouDB.Dao.DingDanXiangQingDao;
import org.YaoLeGouDB.Dao.SHAddressDao;
import org.YaoLeGouDB.Impl.DingDanBiaoDaoImpl;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.Impl.SHAddressDaoImpl;
import org.YaoLeGouDB.entity.DingDanBiao;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.entity.User;
@WebServlet(urlPatterns={"/DaiShouHuo.do"})
public class DaiShouHuoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		DingDanBiaoDao ddbd = new DingDanBiaoDaoImpl();
		String State = request.getParameter("State");
		if(State == null){
			
			String UserName = request.getParameter("UserName");
			
			List<DingDanBiao> list = ddbd.ByZhuRenUserGetInfo(UserName);
			
			request.setAttribute("list", list);
			
			SHAddressDao sdd = new SHAddressDaoImpl();
			List<SHAddress> sddList = sdd.GetAllInfo(UserName);
			request.setAttribute("sddList", sddList);
			request.setAttribute("UserName", UserName);
			request.getRequestDispatcher("DaiShouHuo.jsp").forward(request, response);
			
		}else if(State.equals("QueRenShouHuo")){
			
			String[] price = request.getParameterValues("price");
			String[] dianzhuuser = request.getParameterValues("dianzhuuser");
			
			
			String DingDanNumber = request.getParameter("DingDanNumber");
			ddbd.ByDingDanNumberQueRenShouHuo(DingDanNumber);
			
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			ddxqd.ByDingDanNumberUpdateStateidWei3(DingDanNumber);
			
			for (int i = 0; i < price.length; i++) {
				ddbd.ByUserNameAndMoneyAddMoney(price[i], dianzhuuser[i]);
			}
			
			User user = (User) request.getSession().getAttribute("user");
			response.sendRedirect(request.getContextPath()+"/YiWanCheng.do?UserName="+user.getUsername());
		}else if(State.equals("updateaddress")){
			
			String DingDanNumber = request.getParameter("DingDanNumber");
			String addressid = request.getParameter("addressid");
			
			boolean is = ddbd.UpdateAddressInfo(Integer.parseInt(addressid),DingDanNumber);
			response.getWriter().print(is);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
