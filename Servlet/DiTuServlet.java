package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.DiTuDao;
import org.YaoLeGouDB.Impl.DiTuDaoImpl;
import org.YaoLeGouDB.entity.DiTu;

import com.alibaba.fastjson.JSON;
@WebServlet(urlPatterns={"/DiTu.do"})
public class DiTuServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		DiTuDao dtd = new DiTuDaoImpl();
		List<DiTu> list = dtd.GetAllInfo();
		String str = JSON.toJSONString(list);
		
		response.getWriter().print(str);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
