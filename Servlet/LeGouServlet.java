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
import org.YaoLeGouDB.Dao.GouWuCheDao;
import org.YaoLeGouDB.Dao.ShengDao;
import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.Dao.UserInfoDao;
import org.YaoLeGouDB.Dao.XiaoXiTongZhiDao;
import org.YaoLeGouDB.Impl.DingDanBiaoDaoImpl;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.Impl.GouWuCheDaoImpl;
import org.YaoLeGouDB.Impl.ShengDaoImpl;
import org.YaoLeGouDB.Impl.ShopInfoDaoImpl;
import org.YaoLeGouDB.Impl.UserInfoDaoImpl;
import org.YaoLeGouDB.Impl.XiaoXiTongZhiDaoImpl;
import org.YaoLeGouDB.entity.DingDanBiao;
import org.YaoLeGouDB.entity.Sheng;
import org.YaoLeGouDB.entity.User;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.entity.XiaoXiTongZhi;

import com.alibaba.fastjson.JSON;

@WebServlet(urlPatterns={"/LeGou.do"})
public class LeGouServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		User user = (User) request.getSession().getAttribute("user");
		
		UserInfoDao uid = new UserInfoDaoImpl();
		
		if(State == null){
			
			//正常读取数据的方法
			UserInfo  ui = uid.ByUserNameGetInfo(user.getUsername());
			
			int row = 0;
			//判断是否是店铺
			if(ui.getIsdianpu() == 1){
				ShopInfoDao sid = new ShopInfoDaoImpl();
				row = sid.ByUserInfoGetShopCount(ui.getUsername());
			}
			
			GouWuCheDao gwcd = new GouWuCheDaoImpl();
			int count = gwcd.GetSize(ui.getUsername());
			
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			int DaiFuKuanCount = ddxqd.ByUserNameGetCount(ui.getUsername());
			
			DingDanBiaoDao ddbd = new DingDanBiaoDaoImpl();
			int DaiShouHuo = ddbd.ByMyUserGetCount(ui.getUsername());
			
			//查询消息数量
			XiaoXiTongZhiDao xxtzd = new XiaoXiTongZhiDaoImpl();
			int msgcount = xxtzd.GetXiaoXiTongZhiCount(ui.getUsername());
			
			//查询消息信息
			List<XiaoXiTongZhi> xxtzList = xxtzd.ByUserNameGetAllInfo(ui.getUsername());
/*			System.out.println(xxtzList.get(0).getTitle());*/
			//查询订货通知数量
			int DingHuoTongZhiCount = ddbd.ByUserNameGetDingHuoTongZhiCount(ui.getUsername());
			
			int YiWanChengCount = ddbd.ByUserNameGetYiWanChengInfoCount(ui.getUsername());
			
			request.setAttribute("DaiShouHuo", DaiShouHuo);
			request.setAttribute("DaiFuKuanCount", DaiFuKuanCount);
			request.getSession().setAttribute("count", count);
			request.setAttribute("row", row);
			request.setAttribute("msgcount", msgcount);
			request.setAttribute("xxtzList", xxtzList);
			request.setAttribute("DingHuoTongZhiCount", DingHuoTongZhiCount);
			request.setAttribute("YiWanChengCount", YiWanChengCount);
			request.setAttribute("ui", ui);
			request.getRequestDispatcher("LeGou.jsp").forward(request, response);
			
			
		}else if(State.equals("Update")){
			
			//前往更新信息的Servlet的
			UserInfo  ui = uid.ByUserNameGetInfo(user.getUsername());
			
			String address = ui.getAddress();
			if(address != null && !address.equals("")){
				
				int index = address.indexOf(';');
				String address1 = address.substring(0,index);
				String address2 = address.substring(index+1,address.length());
				
				request.setAttribute("address1", address1);
				request.setAttribute("address2", address2);
			}
			/**
			 * 查询全部的省市区	
			 */
			
			ShengDao sd = new ShengDaoImpl();
			List<Sheng> ShengList = sd.GetAllInfo();
			request.setAttribute("ShengList", ShengList);
			
			
			request.getSession().setAttribute("ui", ui);

			request.getRequestDispatcher("Update.jsp").forward(request, response);
			
		}else if(State.equals("KaiTong")){
			
			//开通店铺
			String shenfen = request.getParameter("shenfen");
			String UserName = request.getParameter("UserName");
			
			
			boolean is = uid.KaiTongDianPu(shenfen, UserName);
			response.getWriter().print(is);
			
		}else if(State.equals("exist")){
			
			/**
			 * 判断身份证号是否存在
			 */
			String shenfen = request.getParameter("shenfen");
			UserInfo ui = uid.IsShenFenExist(shenfen);
			if(ui != null){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
			
		}else if(State.equals("Money")){
			
			/**
			 * 判断金额是否足够 1500
			 */
			String UserName = request.getParameter("UserName");
			boolean is = uid.ByIdIsMoney(UserName);
			response.getWriter().print(is);
			
			
		}else if(State.equals("Exit")){
			request.getSession().removeAttribute("user");
			response.sendRedirect(request.getContextPath()+"/index.do");
		}else if(State.equals("msg")){
			
			//正常读取数据的方法
			UserInfo  ui = uid.ByUserNameGetInfo(user.getUsername());
			
			String DingDanNumber = request.getParameter("number");
			
			DingDanBiaoDao ddbd = new DingDanBiaoDaoImpl();
			List<DingDanBiao> list = ddbd.ByUserGetAllStateInfo(DingDanNumber, ui.getUsername());
			
			XiaoXiTongZhiDao xxtz = new XiaoXiTongZhiDaoImpl();
			xxtz.UpdateXiaoXiXiongZhiWei0(DingDanNumber, ui.getUsername());
			
			String liststring = JSON.toJSONString(list);
			response.getWriter().print(liststring);
			
			
		}else if(State.equals("AddMoney")){
			
			String UserName = request.getParameter("UserName");
			uid.ByUserNameAddMoney(UserName);
			
			response.sendRedirect(request.getContextPath()+"/LeGou.do");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
