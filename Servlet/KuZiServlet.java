package org.YaoLeGouDB.Servlet;
/**
 * 裤子的Servlet
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.Dao.ShopMingXiDao;
import org.YaoLeGouDB.Dao.UserInfoDao;
import org.YaoLeGouDB.Impl.ShopInfoDaoImpl;
import org.YaoLeGouDB.Impl.ShopMingXiDaoImpl;
import org.YaoLeGouDB.Impl.UserInfoDaoImpl;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.ShopMingXi;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.utils.pageBean;

@WebServlet(urlPatterns={"/KuZi.do"})
public class KuZiServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		if(State == null){
			
			//分页
			pageBean pb = new pageBean();
			ShopInfoDao sid = new ShopInfoDaoImpl();
			String page = request.getParameter("page");
			if(page != null && !page.equals("")){
				pb.setPageNo(Integer.parseInt(page));
			}
			
			
			
			String SexFenLei = request.getParameter("SexFenLei");
			String ma = request.getParameter("ma");
			String banxing = request.getParameter("banxing");
			String s = request.getParameter("s");
			String b = request.getParameter("b");
			ShopInfo sis = new ShopInfo();
			if(SexFenLei != null && !SexFenLei.equals("全")){
				sis.setSexfenlei(SexFenLei);
			}
			if(ma != null && !ma.equals("部")){
				sis.setMa(ma);
			}
			if(banxing != null && !banxing.equals("全部")){
				sis.setBanxing(banxing);
			}
			if(s != null && !s.equals("") && b != null && !b.equals("")){
				sis.setB(b);
				sis.setS(s);
			}
			sis.setTypearray("2");//设置衣服类型
			pb.setTotalCount(sid.ByShopTypeGetCount(sis));
			
			List<ShopInfo> si = sid.ByShopTypeGetAllInfo(pb.getPageNo(),pb.getPageSize(),sis);
			request.setAttribute("SexFenLei", SexFenLei);
			request.setAttribute("ma", ma);
			request.setAttribute("banxing", banxing);
			request.setAttribute("s", s);
			request.setAttribute("b", b);
			request.setAttribute("si", si);
			request.setAttribute("pb", pb);
			request.getRequestDispatcher("/KuZi.jsp").forward(request, response);
			
			
		}else if(State.equals("Select")){
			
			/**
			 * 通往物品详情
			 */
			String ShopId = request.getParameter("ShopId");
			
			ShopInfoDao sid = new ShopInfoDaoImpl();
			ShopInfo si = sid.ByShopIdGetAllInfo(Integer.parseInt(ShopId));
			
			String color = si.getColor();
			String[] Color = color.split(",");			
			
			String chicun = si.getChicun();
			String[] ChiCun = chicun.split(",");
			
			ShopMingXiDao smxd = new ShopMingXiDaoImpl();
			List<ShopMingXi> ShopMingXiList = smxd.ByShopIdGetShopImgInfo(Integer.parseInt(ShopId));
			
			UserInfoDao uid = new UserInfoDaoImpl();
			UserInfo ui = uid.ByUserNameGetInfo(si.getZhuRenUser());
			
			request.setAttribute("ui", ui);
			request.setAttribute("ChiCun", ChiCun);
			request.setAttribute("Color", Color);
			request.setAttribute("ShopMingXiList", ShopMingXiList);
			request.setAttribute("si", si);
			request.getRequestDispatcher("/WuPinXiangQing.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
