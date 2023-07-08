package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.DingDanXiangQingDao;
import org.YaoLeGouDB.Dao.GouWuCheDao;
import org.YaoLeGouDB.Dao.SHAddressDao;
import org.YaoLeGouDB.Dao.ShengDao;
import org.YaoLeGouDB.Dao.ZhiFuTypeDao;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.Impl.GouWuCheDaoImpl;
import org.YaoLeGouDB.Impl.SHAddressDaoImpl;
import org.YaoLeGouDB.Impl.ShengDaoImpl;
import org.YaoLeGouDB.Impl.ZhiFuTypeDaoImpl;
import org.YaoLeGouDB.entity.DingDanXiangQing;
import org.YaoLeGouDB.entity.GouWuChe;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.entity.Sheng;
import org.YaoLeGouDB.entity.ZhiFuType;
import org.YaoLeGouDB.utils.LinShiDataBase;

@WebServlet(urlPatterns={"/GouWuChe.do"})
public class GouWuCheServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		GouWuCheDao gwcd = new GouWuCheDaoImpl();
		if(State == null){
			
			String UserName = request.getParameter("UserName");
			List<GouWuChe> GouWuChelist = gwcd.GetAllGouWuCheInfo(UserName);
			
			request.setAttribute("UserName", UserName);
			request.setAttribute("GouWuChelist", GouWuChelist);
			request.getRequestDispatcher("/GouWuChe.jsp").forward(request, response);
		}else if(State.equals("del")){
			String UserName = request.getParameter("username");
			String Id = request.getParameter("id");
			
			boolean is = gwcd.ByUserNameDelInfo(UserName,Integer.parseInt(Id));
			response.getWriter().print(is);
		}else if(State.equals("AddCount")){
			
			//添加购物车信息
			String username = request.getParameter("username");
			String id = request.getParameter("id");
			String shopid = request.getParameter("shopid");
			String count = request.getParameter("count");
			gwcd.ByIdUserNameAndShopIdUpdateCountInfojia(Integer.parseInt(id), username, Integer.parseInt(shopid), Integer.parseInt(count));
		}else if(State.equals("JianCount")){
			
			//减去购物车信息
			String username = request.getParameter("username");
			String id = request.getParameter("id");
			String shopid = request.getParameter("shopid");
			String count = request.getParameter("count");
			gwcd.ByIdUserNameAndShopIdUpdateCountInfojian(Integer.parseInt(id), username, Integer.parseInt(shopid), Integer.parseInt(count));
		}else if(State.equals("submit")){
			
			//提交订单处理
			/**
			 * 前往订单页面
			 */
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			String DingDanNumber = request.getParameter("DingDanNumber");
			
			boolean is = ddxqd.ByDingDanNumberGetInfo(DingDanNumber);
			if(is){
				SHAddressDao shad = new SHAddressDaoImpl();
				String UserName = request.getParameter("UserName");
				List<SHAddress> ShouHuolist = shad.GetAllInfo(UserName);
				
				//处理订单
				String[] id = request.getParameterValues("ids");//接收一组商品的默认id
				String[] shopid = request.getParameterValues("shopid");	//接收一组商品id
				String[] shangpincount = request.getParameterValues("shangpincount");	//接受一组商品数量
				String[] shangpincolor = request.getParameterValues("shangpincolor");	//接受一组商品颜色
				String[] shangpinchicun = request.getParameterValues("shangpinchicun");	//接受一组商品尺寸
				String[] username = request.getParameterValues("usernames");	//接受一组商品名称
				
				
				
				
				//往订单详情商品表里面插入数据
				
				for (int i = 0; i < shangpinchicun.length; i++) {
					if(!shangpinchicun[i].equals("")){
						DingDanXiangQing ddxq = new DingDanXiangQing();
						ddxq.setChicui(shangpinchicun[i]);
						ddxq.setColor(shangpincolor[i]);
						int count = Integer.parseInt(shangpincount[i]);
						ddxq.setCount(count);
						ddxq.setDingdannumber(DingDanNumber);
						ddxq.setShopid(Integer.parseInt(shopid[i]));
						ddxq.setUsername(username[i]);
						ddxq.setMyuser(UserName);
						ddxqd.AddDingDanXingQingInfo(ddxq);
						//删除购物车的信息
						if(id != null){
							gwcd.ByIdUserDeleteInfo(Integer.parseInt(id[i]), UserName);
						}
					}
				}
				
				//查询全部插入的数据
				List<DingDanXiangQing> list = ddxqd.ByUserNameAndDingDanNumberGetInfo(UserName, DingDanNumber);
				for (DingDanXiangQing dingDanXiangQing : list) {
					System.out.println(dingDanXiangQing.getUsername()+"  "+dingDanXiangQing.getDingdannumber());
				}
				/**
				 * 查询全部的省市区	
				 */
				ShengDao sd = new ShengDaoImpl();
				List<Sheng> ShengList = sd.GetAllInfo();
				
				ZhiFuTypeDao zftd = new ZhiFuTypeDaoImpl();
				List<ZhiFuType> ZhiFuTypeList = zftd.GetAllInfo();
				
				//订单详情表里面的信息
				request.setAttribute("list", list);
				
				request.setAttribute("ZhiFuTypeList", ZhiFuTypeList);
				request.setAttribute("ShengList", ShengList);
				request.setAttribute("ShouHuolist", ShouHuolist);
				request.setAttribute("UserName", UserName);
				request.setAttribute("DingDanNumber", DingDanNumber);
				
				LinShiDataBase.DingDanNumber = DingDanNumber;
				request.getRequestDispatcher("/DingDan.jsp").forward(request, response);
			}else{
				SHAddressDao shad = new SHAddressDaoImpl();
				String UserName = request.getParameter("UserName");
				List<SHAddress> ShouHuolist = shad.GetAllInfo(UserName);
				
				//查询全部插入的数据
				List<DingDanXiangQing> list = ddxqd.ByUserNameAndDingDanNumberGetInfo(UserName, DingDanNumber);
				for (DingDanXiangQing dingDanXiangQing : list) {
					System.out.println(dingDanXiangQing.getUsername()+"  "+dingDanXiangQing.getDingdannumber());
				}
				/**
				 * 查询全部的省市区	
				 */
				ShengDao sd = new ShengDaoImpl();
				List<Sheng> ShengList = sd.GetAllInfo();
				
				ZhiFuTypeDao zftd = new ZhiFuTypeDaoImpl();
				List<ZhiFuType> ZhiFuTypeList = zftd.GetAllInfo();
				
				//订单详情表里面的信息
				request.setAttribute("list", list);
				
				request.setAttribute("ZhiFuTypeList", ZhiFuTypeList);
				request.setAttribute("ShengList", ShengList);
				request.setAttribute("ShouHuolist", ShouHuolist);
				request.setAttribute("UserName", UserName);
				request.getSession().setAttribute("DingDanNumber", DingDanNumber);
				request.getRequestDispatcher("/DingDan.jsp").forward(request, response);
			}
			
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
