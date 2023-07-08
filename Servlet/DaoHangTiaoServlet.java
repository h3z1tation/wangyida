package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.DaoHangTiaoDao;
import org.YaoLeGouDB.Impl.DaoHangTiaoDaoImpl;
import org.YaoLeGouDB.entity.DaoHangTiao;
@WebServlet(urlPatterns={"/ht/DaoHangTiao.do"})
public class DaoHangTiaoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		DaoHangTiaoDao dhtd = new DaoHangTiaoDaoImpl();
		String State = request.getParameter("State");
		if(State == null){
			
			List<DaoHangTiao> DaoHangTiaoList = dhtd.GetAllInfo();
			
			request.setAttribute("DaoHangTiaoList", DaoHangTiaoList);
			request.getRequestDispatcher("/ht/DaoHangTiaoAdmin.jsp").forward(request, response);
		}else if(State.equals("UpdateState")){
			
			
			String BannerId = request.getParameter("BannerId");
			String Is = request.getParameter("Is");
			if(Is.equals("∆Ù”√")){
				dhtd.ByidUpdateStateWei0(Integer.parseInt(BannerId));
			}else{
				dhtd.ByidUpdateStateWei1(Integer.parseInt(BannerId));
			}
		}else if(State.equals("Add")){
			
			String Name = request.getParameter("newpassword");
			String Href = request.getParameter("newpassword2");
			
			DaoHangTiao dht = new DaoHangTiao();
			dht.setDaohangtiaoname(Name);
			dht.setHref(Href);
			
			boolean is = dhtd.AddInfo(dht);
			
			response.sendRedirect(request.getContextPath()+"/ht/DaoHangTiao.do");
			
		}else if(State.equals("del")){
			
			String Id = request.getParameter("Id");
			boolean is = dhtd.ByIdDelInfo(Integer.parseInt(Id));
			response.getWriter().print(is);
		}else if(State.equals("SelectUpdate")){
			
			String id = request.getParameter("id");
			DaoHangTiao dht = dhtd.ByIdGetAllInfo(Integer.parseInt(id));
			
			request.setAttribute("dht", dht);
			request.getRequestDispatcher("/ht/UpdateDaoHangTiao.jsp").forward(request, response);
		}else if(State.equals("UpdateInfo")){
			
			String id = request.getParameter("id");
			String Name = request.getParameter("newpassword");
			String Href = request.getParameter("newpassword2");
			
			DaoHangTiao dht = new DaoHangTiao();
			dht.setDaohangtiaoname(Name);
			dht.setHref(Href);
			dht.setId(Integer.parseInt(id));
			
			dhtd.ByIdUpdateInfo(dht);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
