package org.YaoLeGouDB.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.LoginAdminDao;
import org.YaoLeGouDB.Dao.QuanXianDao;
import org.YaoLeGouDB.Impl.LoginAdminDaoImpl;
import org.YaoLeGouDB.Impl.QuanXianDaoImpl;
import org.YaoLeGouDB.entity.LoginAdmin;
import org.YaoLeGouDB.entity.QuanXian;

@WebServlet(urlPatterns={"/ht/LoginAdmin.do"})
public class loginAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		QuanXianDao qxd = new QuanXianDaoImpl();
		LoginAdminDao lad = new LoginAdminDaoImpl();
		if(State == null){
			String UserName = request.getParameter("UserName");
			String Pass = request.getParameter("Pass");
			
			LoginAdmin la = lad.ByUserNameAndPassClickIsOk(UserName, Pass);
			if(la != null){
				request.getSession().setAttribute("la", la);
			}
			
			response.getWriter().print(la);
		}else if(State.equals("IndexAdmin")){
			request.getRequestDispatcher("LoginAdmin.do?State=jiazai").forward(request, response);
		}else if(State.equals("add")){
			
			String adminName = request.getParameter("adminName");
			String password = request.getParameter("password");
			String Name = request.getParameter("Name");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String adminRole = request.getParameter("adminRole");
			String beizhu = request.getParameter("beizhu");
			
			if(beizhu != null && !beizhu.equals("")){
				
			}else {
				beizhu = "暂无备注";
			}
			
			LoginAdmin la = new LoginAdmin();
			la.setBeizhu(beizhu);
			la.setName(Name);
			la.setPassword(password);
			la.setPhone(phone);
			la.setSexid(Integer.parseInt(sex));
			la.setShenfenid(Integer.parseInt(adminRole));
			la.setUsername(adminName);
			
			lad.AddAdminInfo(la);
			
			//添加权限
			if(adminRole.equals("1")){
				qxd.ByAdminAddInfo(adminName, 0);
			}else{
				qxd.ByAdminAddInfo(adminName, 1);
			}
			
		}else if(State.equals("SelectUpdate")){
			
			String id = request.getParameter("id");
			LoginAdmin la = lad.ByIdGetAllInfo(id);
			
			request.setAttribute("la", la);
			request.getRequestDispatcher("/ht/UpdateAdmin.jsp").forward(request, response);
			
		}else if(State.equals("Update")){
			String adminName = request.getParameter("adminName");
			String password = request.getParameter("password");
			String Name = request.getParameter("Name");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			String adminRole = request.getParameter("adminRole");
			String beizhu = request.getParameter("beizhu");
			String id = request.getParameter("id");
			
			if(beizhu != null && !beizhu.equals("")){
				
			}else {
				beizhu = "暂无备注";
			}
			
			LoginAdmin la = new LoginAdmin();
			la.setBeizhu(beizhu);
			la.setName(Name);
			la.setPassword(password);
			la.setPhone(phone);
			la.setSexid(Integer.parseInt(sex));
			la.setShenfenid(Integer.parseInt(adminRole));
			la.setUsername(adminName);
			la.setId(Integer.parseInt(id));
			
			lad.ByUserNameUpdateInfo(la);
		}else if(State.equals("jiazai")){
			LoginAdmin la = (LoginAdmin) request.getSession().getAttribute("la");
			
			QuanXian qx = qxd.ByLoginAdminGetInfo(la.getUsername());
			
			request.setAttribute("qx", qx);
			request.getRequestDispatcher("/ht/index.jsp").forward(request, response);
		}else if(State.equals("Exit")){
			request.getSession().removeAttribute("la");
			response.sendRedirect(request.getContextPath()+"/loginAdmin.jsp");
		}else if(State.equals("UpdatePass")){
			
			String rpass = request.getParameter("newpassword2");
			LoginAdmin la = (LoginAdmin)request.getSession().getAttribute("la");
			boolean is = lad.ByUserNameUpdatePass(la.getUsername(), rpass);
			response.getWriter().print(is);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
