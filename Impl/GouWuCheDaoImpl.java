package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.GouWuCheDao;
import org.YaoLeGouDB.entity.GouWuChe;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class GouWuCheDaoImpl extends BaseDao implements GouWuCheDao {

	@Override
	public boolean AddGouWuCheInfo(GouWuChe gwc) {
		String sql = "insert into GouWuChe(UserName,ShopName,ChiCun,Color,`Count`,Price,Image,ShopId) values"
				+ "(?,?,?,?,?,?,?,?)";
		Object[] sz = {gwc.getUsername(),gwc.getShopname(),gwc.getChicun(),gwc.getColor(),gwc.getCount(),gwc.getPrice(),gwc.getImage(),gwc.getShopid()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public List<GouWuChe> GetAllGouWuCheInfo(String UserName){
		String sql = "select * from gouwuche as gwc inner join ShopInfo as si on(gwc.shopid = si.shopid) where gwc.UserName = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		List<GouWuChe> list = new ArrayList<GouWuChe>();
		try {
			while(rs.next()){
				GouWuChe gwc = new GouWuChe();
				gwc.setChicun(rs.getString("chicun"));
				gwc.setColor(rs.getString("color"));
				gwc.setCount(rs.getInt("count"));
				gwc.setId(rs.getInt("id"));
				gwc.setImage(rs.getString("image"));
				gwc.setPrice(rs.getDouble("price"));
				gwc.setShopid(rs.getInt("shopId"));
				gwc.setShopname(rs.getString("shopname"));
				gwc.setUsername(rs.getString("username"));
				
				ShopInfo si = new ShopInfo();
				si.setChicun(rs.getString("si.chicun"));
				si.setColor(rs.getString("si.color"));
				si.setContext(rs.getString("si.context"));
				si.setCreatetime(rs.getString("si.createtime"));
				si.setImage(rs.getString("si.image"));
				si.setShopid(rs.getInt("si.shopid"));
				si.setShopmiaoshu(rs.getString("si.shopmiaoshu"));
				si.setShopname(rs.getString("si.shopname"));
				si.setShopprice(rs.getDouble("si.shopprice"));
				si.setShoptypeid(rs.getInt("si.shoptypeid"));
				si.setZhuRenUser(rs.getString("si.zhurenuser"));
				
				gwc.setSi(si);
				list.add(gwc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int GetSize(String UserName) {
		String sql = "select count(*) as a from gouwuche where UserName = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		int rwo = 0;
		try {
			while(rs.next()){
				rwo = rs.getInt("a");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rwo;
	}

	@Override
	public boolean ByUserNameDelInfo(String UserName,int id) {
		String sql = "delete from gouwuche where UserName = ? and id = ?";
		Object[] sz = {UserName,id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public GouWuChe ByShopIdAndUserNameIsExist(String UserName, int ShopId,String Chicun,String Color) {
		String sql = "select * from GouWuChe where UserName = ? and ShopId = ? and ChiCun = ? and Color = ?";
		Object[] sz = {UserName,ShopId,Chicun,Color};
		ResultSet rs = super.executeQuery(sql, sz);
		GouWuChe gwc = null;
		try {
			while(rs.next()){
				gwc = new GouWuChe();
				gwc.setChicun(rs.getString("chicun"));
				gwc.setColor(rs.getString("color"));
				gwc.setCount(rs.getInt("count"));
				gwc.setId(rs.getInt("id"));
				gwc.setImage(rs.getString("image"));
				gwc.setPrice(rs.getDouble("price"));
				gwc.setShopid(rs.getInt("shopId"));
				gwc.setShopname(rs.getString("shopname"));
				gwc.setUsername(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gwc;
	}

	@Override
	public boolean UpdateInfo(String chicun,String color,int count,String username,int shopid,int id) {
		String sql = "update gouwuche set chicun = ? , color = ?, `count` = count + ? where username = ? and shopid = ? and id = ?";
		Object[] sz = {chicun,color,count,username,shopid,id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdUserNameAndShopIdUpdateCountInfojia(int id,String UserName, int ShopId,int count) {
		String sql = "update gouwuche set `count` = count + ? where id = ? and UserName = ? and shopid = ?";
		Object[] sz = {count,ShopId,UserName,id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdUserNameAndShopIdUpdateCountInfojian(int id,
			String UserName, int ShopId, int count) {
		String sql = "update gouwuche set `count` = count - ? where id = ? and UserName = ? and shopid = ?";
		Object[] sz = {count,ShopId,UserName,id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdUserDeleteInfo(int id, String UserName) {
		String sql = "delete from gouwuche where id = ? and UserName = ?";
		Object[] sz = {id,UserName};
		return super.executeUpdate(sql, sz);
	}

}
