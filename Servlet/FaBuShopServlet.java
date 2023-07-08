package org.YaoLeGouDB.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.Dao.ShopMingXiDao;
import org.YaoLeGouDB.Dao.ShopTypeDao;
import org.YaoLeGouDB.Impl.ShopInfoDaoImpl;
import org.YaoLeGouDB.Impl.ShopMingXiDaoImpl;
import org.YaoLeGouDB.Impl.ShopTypeDaoImpl;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.ShopMingXi;
import org.YaoLeGouDB.entity.ShopType;
import org.YaoLeGouDB.utils.Tool;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
@WebServlet(urlPatterns={"/FaBuShop.do"})
public class FaBuShopServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*PrintWriter out = response.getWriter();*/
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String State = request.getParameter("State");
		if(State == null){
			
			/**
			 * 读取所有商品类型
			 */
			String UserName = request.getParameter("UserName");
			ShopTypeDao std = new ShopTypeDaoImpl();
			List<ShopType> ShopTypeList = std.GetAllShopTypeInfo();
			request.setAttribute("ShopTypeList", ShopTypeList);
			
			System.out.println(UserName);
			request.setAttribute("UserName", UserName);
			request.getRequestDispatcher("/FaBuShop.jsp").forward(request, response);
		}else if(State.equals("Add")){
			
			/**
			 * 添加商品
			 */
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
			String ShopName = req.getParameter("ShopName");
			String ShopMiaoShu = req.getParameter("ShopMiaoShu");
			String ShopPrice = req.getParameter("ShopPrice");
			String Color = req.getParameter("Color");
			String ChiCun = req.getParameter("ChiCun");
			String ShopType = req.getParameter("ShopType");
			String Context = req.getParameter("content1");
			String UserName = req.getParameter("ZhuRenUser");
			/*String[] ColorName = req.getParameterValues("ColorName");*/
			
			
			ShopInfoDao sid = new ShopInfoDaoImpl();
			
			
			ShopInfo si = new ShopInfo();
			si.setChicun(ChiCun);
			si.setColor(Color);
			si.setContext(Context);
			si.setImage("暂无封面图片");
			si.setShopmiaoshu(ShopMiaoShu);
			si.setShopname(ShopName);
			si.setShopprice(Double.parseDouble(ShopPrice));
			si.setShopid(Integer.parseInt(ShopType));
			si.setZhuRenUser(UserName);
			//添加商品信息
			boolean is = sid.AddInfoInfo(si);
			
			//查询商品id
			int shopid = sid.GetShopId(ShopName, ShopMiaoShu, UserName);
			
			Tool t = new Tool();
			ShopMingXiDao smxd = new ShopMingXiDaoImpl();
			
			//接受上传的文件
    		Files files = smartUpload.getFiles();
    		File file= null;
    		for(int i =0;i<files.getCount();i++){
    			file = files.getFile(i);
    			try {
    				
    				if(i==0){
    					//往本地上传图片
    					file.saveAs("/attached/image/"+file.getFileName());

    					//为文件名改名
    					String ImagePath = t.Updatefengmian(file.getFileName(), shopid);
    					String tu = "attached/image/"+shopid+"_fengmian.jpg";
    					boolean iss = sid.ByIdUpdateShopImage(shopid, tu);
    				}else{
    					int mingxiid = smxd.ByShopIdGetInfoCount(shopid);
    					ShopMingXi smx = new ShopMingXi();
    					smx.setShopid(shopid);
    					
    					//往本地上传图片
    					file.saveAs("/attached/image/"+file.getFileName());
    					t.Updatedefault(file.getFileName(), shopid, mingxiid);
    					String tu = "attached/image/"+shopid+"_default_"+mingxiid+".jpg";
    					smx.setSimg(tu);
    					smx.setBimg(tu);
    					
    					boolean isok = smxd.AddMingXiInfo(smx);
    				}
    				
    				
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
			response.sendRedirect(request.getContextPath()+"/UpdateColor.do?shopid="+shopid);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//default debug doGet() method
		doGet(request, response);
	}

}
