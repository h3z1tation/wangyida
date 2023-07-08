package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.BannerDao;
import org.YaoLeGouDB.Dao.DaoHangTiaoDao;
import org.YaoLeGouDB.Dao.JiBenInfoDao;
import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.Dao.ShouYeShopDao;
import org.YaoLeGouDB.Dao.UserDao;
import org.YaoLeGouDB.Impl.BannerDaoImpl;
import org.YaoLeGouDB.Impl.DaoHangTiaoDaoImpl;
import org.YaoLeGouDB.Impl.JiBenInfoDaoImpl;
import org.YaoLeGouDB.Impl.ShopInfoDaoImpl;
import org.YaoLeGouDB.Impl.ShouYeShopDaoImpl;
import org.YaoLeGouDB.Impl.UserDaoImpl;
import org.YaoLeGouDB.entity.Banner;
import org.YaoLeGouDB.entity.DaoHangTiao;
import org.YaoLeGouDB.entity.JiBenInfo;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.ShouYeShop;
import org.YaoLeGouDB.entity.User;

import com.alibaba.fastjson.JSON;

@WebServlet(urlPatterns={"/index.do"})
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String State = request.getParameter("State");
		if(State == null){
			
			//正常初始化首页全部信息。
			
			/**查询全部导航条 信息 */
			DaoHangTiaoDao dhtd = new DaoHangTiaoDaoImpl();
			List<DaoHangTiao> DaoHangTiaoList = dhtd.GetAllInfo();
			
			/**查询全部网站基本 信息*/
			JiBenInfoDao jbid = new JiBenInfoDaoImpl();
			List<JiBenInfo> JiBenInfoList = jbid.GetAllInfo();
			
			/**查询全部Banner图*/
			BannerDao bd = new BannerDaoImpl();
			List<Banner> BannerList = bd.GetAllInfo();
			
			ShouYeShopDao sysd = new ShouYeShopDaoImpl();
			
			//加载首页楼层
			List<ShouYeShop> ShouYeShopList1 = sysd.ByShopTyIdGetAllInfo(1);
			List<ShouYeShop> ShouYeShopList2 = sysd.ByShopTyIdGetAllInfo(2);
			List<ShouYeShop> ShouYeShopList3 = sysd.ByShopTyIdGetAllInfo(3);
			
			request.setAttribute("BannerList", BannerList);
			request.setAttribute("ShouYeShopList1", ShouYeShopList1);
			request.setAttribute("ShouYeShopList2", ShouYeShopList2);
			request.setAttribute("ShouYeShopList3", ShouYeShopList3);
			request.getSession().setAttribute("JiBenInfoList", JiBenInfoList);
			request.getSession().setAttribute("DaoHangTiaoList", DaoHangTiaoList);
			request.getRequestDispatcher("/ShouYe.jsp").forward(request, response);
			
		}else if(State.equals("Login")){
			
			//登录验证信息
			String UserName = request.getParameter("UserName");
			String PassWord = request.getParameter("PassWord");
			
			User u = new User();
			u.setUsername(UserName);
			u.setPassword(PassWord);
			
			UserDao ud = new UserDaoImpl();
			User user = ud.ClickLogin(u);
			if(user != null){
				int stateid = ud.ByUserNameGetStateInfo(UserName);
				if(stateid == 0){
					request.getSession().setAttribute("user", user);
					out.print(true);
				}else{
					out.print("dongjie");
				}
				
			}else{
				out.print(false);
			}
			
			
			
		}else if(State.equals("Serachr")){
		
			
			//这是通过搜索栏跳转的jsp
			
			
		}else if(State.equals("cha")){
			
			String typeid = request.getParameter("typeid");
			ShopInfoDao sid = new ShopInfoDaoImpl();
			List<ShopInfo> list = sid.ByTypeIdGetBigShopXiaoLing(Integer.parseInt(typeid));
			String sidList = JSON.toJSONString(list);
			
			response.getWriter().print(sidList);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
