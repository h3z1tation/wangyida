package org.YaoLeGouDB.Servlet;
/**
 * 处理订单Serlvet
 */
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
import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.Dao.TotalPirceDao;
import org.YaoLeGouDB.Dao.XiaoXiTongZhiDao;
import org.YaoLeGouDB.Impl.DingDanBiaoDaoImpl;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.Impl.SHAddressDaoImpl;
import org.YaoLeGouDB.Impl.ShopInfoDaoImpl;
import org.YaoLeGouDB.Impl.TotalPirceDaoImpl;
import org.YaoLeGouDB.Impl.XiaoXiTongZhiDaoImpl;
import org.YaoLeGouDB.entity.DingDanBiao;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.entity.User;
import org.YaoLeGouDB.entity.XiaoXiTongZhi;

@WebServlet(urlPatterns={"/DingDanBiao.do"})
public class DingDanBiaoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		if(State == null){
			
			String DingDanNumber = request.getParameter("dingdannumber");
			String MyUser = request.getParameter("user");
			String shaddressid = request.getParameter("shaddressid");
			String zhifufangshi = request.getParameter("zhifufangshi");
			String[] maijiauser = request.getParameterValues("maijiauser");
			String dingdanjine = request.getParameter("dingdanjine");
			
			if(zhifufangshi.equals("在线付款")){
				zhifufangshi = "1";
			}else{
				zhifufangshi = "2";
			}
			
			for (int i = 0; i < maijiauser.length; i++) {
				DingDanBiao ddb = new DingDanBiao();
				ddb.setDingdannumber(DingDanNumber);
				ddb.setZhurenuser(MyUser);
				ddb.setAddressid(Integer.parseInt(shaddressid));
				ddb.setZhifuid(Integer.parseInt(zhifufangshi));
				ddb.setPrice(Integer.parseInt(dingdanjine));
				ddb.setUsername(maijiauser[i]);
				
				DingDanBiaoDao ddbd = new DingDanBiaoDaoImpl();
				boolean is = ddbd.AddDingDanInfo(ddb);
			}
			
			TotalPirceDao tp = new TotalPirceDaoImpl();
			tp.AddMoney(Integer.parseInt(dingdanjine));
			
			String[] beizhu = request.getParameterValues("beizhu");
			for (int i = 0; i < beizhu.length; i++) {
				if(beizhu[i].equals("")){
					beizhu[i] = "null";
				}
			}
			SHAddressDao shad = new SHAddressDaoImpl();
			SHAddress sha = shad.ByIdGetAllInfo(Integer.parseInt(shaddressid));
			XiaoXiTongZhiDao xxtzd = new XiaoXiTongZhiDaoImpl();
			for (int i = 0; i < maijiauser.length; i++) {
				XiaoXiTongZhi xxtz = new XiaoXiTongZhi();
				xxtz.setDingdannumber(DingDanNumber);
				xxtz.setUsername(maijiauser[i]);
				xxtz.setTitle("订货通知");
				xxtz.setContext("您的商品已被用户购买，收货地址："+sha.getCity()+" "+sha.getAddress()+"，手机号："+sha.getPhone()+"，买家姓名："+sha.getName()+"，买家备注：'"+(beizhu[i]=="null"?'无':beizhu[i])+"'，请尽快发货。");				
				xxtz.setMyuser(MyUser);
				xxtzd.AddXiaoXiTongZhiInfo(xxtz);
			}
			
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			boolean is = ddxqd.ByDingDanNumberUpdateStateInfo(DingDanNumber);
			
			ddxqd.ByDingDanNumberUpdateStateidWei1(DingDanNumber);
			
			ShopInfoDao sid = new ShopInfoDaoImpl();
			String[] shopid = request.getParameterValues("shopid");
			String[] shopcount = request.getParameterValues("shopcount");
			for (int i = 0; i < shopid.length; i++) {
				sid.ByShopIdAddCount(Integer.parseInt(shopid[i]),Integer.parseInt(shopcount[i]));
			}
			
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("UserName", user.getUsername());
			request.getRequestDispatcher("/DaiShouHuo.do").forward(request, response);
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
