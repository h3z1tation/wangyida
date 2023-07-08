package org.YaoLeGouDB.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.UserInfoDao;
import org.YaoLeGouDB.Impl.UserInfoDaoImpl;
import org.YaoLeGouDB.entity.UserInfo;
@WebServlet(urlPatterns={"/ht/GeRenInfo.do"})
public class GeRenInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String State = request.getParameter("State");
		if(State == null){
			String UserName = request.getParameter("UserName");
			UserInfoDao uid = new UserInfoDaoImpl();
			UserInfo ui = uid.ByUserNameGetInfo(UserName);
			request.setAttribute("ui", ui);
			request.getRequestDispatcher("/ht/GeRenInfo.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
