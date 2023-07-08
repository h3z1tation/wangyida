package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.BannerDao;
import org.YaoLeGouDB.Dao.ShouYeShopDao;
import org.YaoLeGouDB.Impl.BannerDaoImpl;
import org.YaoLeGouDB.Impl.ShouYeShopDaoImpl;
import org.YaoLeGouDB.entity.Banner;
import org.YaoLeGouDB.entity.ShouYeShop;
import org.YaoLeGouDB.utils.Tool;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet(urlPatterns={"/ht/Banner.do"})
public class BannerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		BannerDao bd = new BannerDaoImpl();
		String State = request.getParameter("State");
		ShouYeShopDao sysd = new ShouYeShopDaoImpl();
		if(State == null){
			List<Banner> Bannerlist = bd.GetAllInfo();
			
			request.setAttribute("Bannerlist", Bannerlist);
			request.getRequestDispatcher("/ht/Banner.jsp").forward(request, response);
		}else if(State.equals("Update")){
			
			
			String BannerId = request.getParameter("BannerId");
			String Is = request.getParameter("Is");
			if(Is.equals("启用")){
				bd.ByBannerIdUpdateIsTingYong0(Integer.parseInt(BannerId));
			}else{
				bd.ByBannerIdUpdateIsTingYong1(Integer.parseInt(BannerId));
			}
		}else if(State.equals("ShangChuan")){
			
			//添加Banner图
			SmartUpload smartUpload = new SmartUpload();
			smartUpload.setCharset("utf-8");
			smartUpload.initialize(this, request, response);
			try {
				smartUpload.upload();
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			//Request可以通过这个获取表单里面的内容
			Request req = smartUpload.getRequest();
			
			String bgColor = req.getParameter("bgColor");
			String URL = req.getParameter("URL");
			String BeiZhu = req.getParameter("bgColor");
			if(BeiZhu == null && BeiZhu.equals("")){
				BeiZhu = "未写任何备注";
			}
			Banner b = new Banner();
			b.setBgcolor(bgColor);
			b.setHref(URL);
			b.setBeizhu(BeiZhu);
			
			
			Tool t = new Tool();
			//头像文件处理
			Files files = smartUpload.getFiles();
			File file = files.getFile(0);
			if(file.isMissing()==false){
				try {
					
					//往本地存储头像照片
					file.saveAs("/Image/Index_Image/"+file.getFileName());
					 //这个是为了 改文件名
					String ImagePath = t.AddBannerImage(file.getFileName());
					b.setImage("Image/Index_Image/"+ImagePath+".jpg");					

				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
			}
			bd.AddBannerInfo(b);
			
			response.sendRedirect(request.getContextPath()+"/ht/Banner.do");
		}else if(State.equals("del")){
			
			String BannerId = request.getParameter("BannerId");
			String ImageURL = bd.ByBannerIdGetImageURL(BannerId);
			Tool t = new Tool();
			t.DelBannerURL(ImageURL);
			boolean is = bd.ByBannerIdDelBanner(BannerId);
			response.getWriter().print(is);
		}else if(State.equals("shangyi")){
			/***********************************以下是首页商品管理***********************************/
			
			
			List<ShouYeShop> ShouYeShoplists = sysd.ByShopTyIdGetAllInfo(1);
			
			request.setAttribute("ShouYeShoplists", ShouYeShoplists);
			request.getRequestDispatcher("/ht/ShangYi.jsp").forward(request, response);
		}else if(State.equals("shangyidel")){
			String id = request.getParameter("Id");
			
			sysd.ByIdAndTypeIdDelInfo(id, "1");
		}else if(State.equals("AddShouYeShopInfo")){
			
			String typeid = request.getParameter("typeid");
			String ShopId = request.getParameter("shopid");
			
			sysd.AddInfo(typeid, ShopId);
		}else if(State.equals("kuzi")){
			
			List<ShouYeShop> ShouYeShoplists = sysd.ByShopTyIdGetAllInfo(2);
			
			request.setAttribute("ShouYeShoplists", ShouYeShoplists);
			request.getRequestDispatcher("/ht/KuZi.jsp").forward(request, response);
		}else if(State.equals("kuzidel")){
			String id = request.getParameter("Id");
			
			sysd.ByIdAndTypeIdDelInfo(id, "2");
		}else if(State.equals("xiezi")){
			
			List<ShouYeShop> ShouYeShoplists = sysd.ByShopTyIdGetAllInfo(3);
			
			request.setAttribute("ShouYeShoplists", ShouYeShoplists);
			request.getRequestDispatcher("/ht/XieZi.jsp").forward(request, response);
		}else if(State.equals("xiezidel")){
			String id = request.getParameter("Id");
			
			sysd.ByIdAndTypeIdDelInfo(id, "3");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
