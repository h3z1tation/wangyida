package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.DingDanXiangQingDao;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.entity.DingDanXiangQing;
@WebServlet(urlPatterns={"/DaiFuKuan.do"})
public class DaiFuKuanServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		if(State == null){
			
			String UserName = request.getParameter("UserName");
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			List<DingDanXiangQing> list = ddxqd.ByMyUserGeAllInfo(UserName);
			
			/*List<DingDanXiangQing> list2 = ddxqd.GetAllInfo(DingDanNumber);*/
			request.setAttribute("list", list);
			request.setAttribute("UserName", UserName);
			request.getRequestDispatcher("DaiFuKuan.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
