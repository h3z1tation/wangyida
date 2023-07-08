package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.CityDao;
import org.YaoLeGouDB.Dao.DingDanXiangQingDao;
import org.YaoLeGouDB.Dao.QuDao;
import org.YaoLeGouDB.Dao.SHAddressDao;
import org.YaoLeGouDB.Dao.ShengDao;
import org.YaoLeGouDB.Dao.ZhiFuTypeDao;
import org.YaoLeGouDB.Impl.CityDaoImpl;
import org.YaoLeGouDB.Impl.DingDanXiangQingDaoImpl;
import org.YaoLeGouDB.Impl.QuDaoImpl;
import org.YaoLeGouDB.Impl.SHAddressDaoImpl;
import org.YaoLeGouDB.Impl.ShengDaoImpl;
import org.YaoLeGouDB.Impl.ZhiFuTypeDaoImpl;
import org.YaoLeGouDB.entity.City;
import org.YaoLeGouDB.entity.DingDanXiangQing;
import org.YaoLeGouDB.entity.Qu;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.entity.Sheng;
import org.YaoLeGouDB.entity.User;
import org.YaoLeGouDB.entity.ZhiFuType;

import com.alibaba.fastjson.JSON;
@WebServlet("/DingDan.do")
public class DingDanServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String State = request.getParameter("State");
		SHAddressDao shad = new SHAddressDaoImpl();
		
		if(State == null){
			
			String UserName = request.getParameter("UserName");
			List<SHAddress> ShouHuolist = shad.GetAllInfo(UserName);
			
			String DingDanNumber = request.getParameter("DingDanNumber");
			DingDanXiangQingDao ddxqd = new DingDanXiangQingDaoImpl();
			//根据订单编号查询数据
			List<DingDanXiangQing> list = ddxqd.ByUserNameAndDingDanNumberGetInfo(UserName, DingDanNumber);
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
			request.getRequestDispatcher("/DingDan.jsp").forward(request, response);
			
		}else if(State.equals("Update")){
			
			String UserName = request.getParameter("UserName");
			String id = request.getParameter("id");
			/*String defaults = request.getParameter("defaults");*/
			
			request.setAttribute("UserName", UserName);
			boolean is = shad.ByUserNameAndDefaultAddressCloseUpdate(UserName);
			is = shad.ByUserNameAndIdUpdateInfo(UserName, Integer.parseInt(id));
			
			response.getWriter().print(is);
		}else if(State.equals("Select")){
			
			/**
			 * 查出来的省的id根据id查找相应的市
			 */
			String id = request.getParameter("text");
			ShengDao sd = new ShengDaoImpl();
			Sheng sheng = sd.ByNameGatInfo(id);
			int shengid = sheng.getShengid();
			
			CityDao cd = new CityDaoImpl();
			List<City> city = cd.ByShengIdGetAllInfo(shengid);
			String out = JSON.toJSONString(city);
			response.getWriter().print(out);
		}else if(State.equals("SelectQu")){
			
			String CityName = request.getParameter("text");
			CityDao cd = new CityDaoImpl();
			City city = cd.ByCityNameGetId(CityName);
			int cityid = city.getCityid();
			
			QuDao qd = new QuDaoImpl();
			List<Qu> qu = qd.ByCityIdGetAllInfo(cityid);
			for (Qu qu2 : qu) {
				System.out.println(qu2.getQuname());
			}
			String out = JSON.toJSONString(qu);
			response.getWriter().print(out);
		}else if(State.equals("AddAddress")){
			
			String isps = request.getParameter("isps");
			String Name = request.getParameter("Name");
			String Phone = request.getParameter("Phone");
			String BeiYongPhone = request.getParameter("BeiYongPhone");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String lable = request.getParameter("lable");
			String is = request.getParameter("is");
			String UserName = request.getParameter("un");
			
			if(is != null){
				request.setAttribute("UserName", UserName);
				shad.ByUserNameAndDefaultAddressCloseUpdate(UserName);
				is = "1";
			}else{
				is = "0";
			}
			
			SHAddress sha = new SHAddress();
			sha.setAddress(address2);
			sha.setByphone(BeiYongPhone);
			sha.setCity(address1);
			sha.setLable(lable);
			sha.setName(Name);
			sha.setPhone(Phone);
			sha.setUsername(UserName);
			sha.setDefaults(Integer.parseInt(is));
			
			
			
			boolean isok = shad.AddSHAddressInfo(sha);
			response.getWriter().print(isok);
		}else if(State.equals("DelInfo")){
			
			//删除收货地址信息
			String id = request.getParameter("id");
			String UserName = request.getParameter("user");
			boolean isok = shad.ByIdDelInfo(Integer.parseInt(id),UserName);
			response.getWriter().print(isok);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
