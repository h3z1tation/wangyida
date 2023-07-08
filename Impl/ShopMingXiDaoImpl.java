package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import org.YaoLeGouDB.Dao.ShopMingXiDao;
import org.YaoLeGouDB.entity.ShopMingXi;
import org.YaoLeGouDB.utils.BaseDao;

public class ShopMingXiDaoImpl extends BaseDao implements ShopMingXiDao {

	@Override
	public List<ShopMingXi> ByShopIdGetShopImgInfo(int ShopId) {
		String sql = "select * from ShopMingXi where shopid = ?";
		Object[] sz = {ShopId};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShopMingXi> list = new ArrayList<ShopMingXi>();
		try {
			while(rs.next()){
				ShopMingXi smx = new ShopMingXi();
				smx.setBimg(rs.getString("bimg"));
				smx.setShopid(rs.getInt("shopid"));
				smx.setSimg(rs.getString("simg"));
				list.add(smx);
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
	public boolean AddShopMingXiInfo(int ShopId, String SImg, String BImg) {
		String sql = "insert into ShopMingXi(ShopId,SImg,BImg) values(?,?,?)";
		Object[] sz = {ShopId,SImg,BImg};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByShopIdGetInfoCount(int shopid) {
		String sql = "select count(*) as c from shopmingxi where ShopId = ?";
		Object[] sz = {shopid};
		ResultSet rs = super.executeQuery(sql, sz);
		int row = 0;
		try {
			while(rs.next()){
				row = rs.getInt("c");
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
		return row;
	}

	@Override
	public boolean AddMingXiInfo(ShopMingXi smx) {
		String sql = "insert into ShopMingXi(ShopId,SImg,BImg) values(?,?,?)";
		Object[] sz = {smx.getShopid(),smx.getSimg(),smx.getBimg()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByShopIdDelInfo(int id) {
		String sql = "delete from shopmingxi where ShopId = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}
	
}
