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
import org.YaoLeGouDB.Impl.DingDanBiaoDaoImpl;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.entity.DingDanBiao;

@WebServlet(urlPatterns={"/DingHuoTongZhi.do"})
public class DingHuoTongZhiServlet extends HttpServlet {

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
			List<DingDanBiao> list = ddbd.ByUserNameGetInfo(UserName);
			
			request.setAttribute("list", list);
			request.setAttribute("UserName", UserName);
			request.getRequestDispatcher("DingHuoTongZhi.jsp").forward(request, response);
			
		}else if(State.equals("QueRenFaHuo")){
			
			String DingDanNumber = request.getParameter("DingDanNumber");
			/*ddbd.ByDingDanNumberUpdateFaHuoInfo(DingDanNumber);  这是改订单状态的*/
			String UserName = request.getParameter("UserName");
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			ddxqd.ByDingDanNumberUpdateStateidWei2(DingDanNumber,UserName);
			
			response.sendRedirect(request.getContextPath()+"/LeGou.do");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
