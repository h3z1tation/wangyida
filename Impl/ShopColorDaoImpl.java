package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.ShopColorDao;
import org.YaoLeGouDB.entity.ShopColor;
import org.YaoLeGouDB.utils.BaseDao;

public class ShopColorDaoImpl extends BaseDao implements ShopColorDao {

	@Override
	public List<ShopColor> ByShopIdAndColorGetAllInfo(int ShopId, String Color) {
		String sql = "select * from ShopColor where ShopId = ? and Color = ?";
		Object[] sz = {ShopId,Color};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShopColor> list = new ArrayList<ShopColor>();
		try {
			while(rs.next()){
				ShopColor sc = new ShopColor();
				sc.setB_img(rs.getString("b_img"));
				sc.setColor(rs.getString("color"));
				sc.setS_img(rs.getString("s_img"));
				sc.setShopid(rs.getInt("shopid"));
				list.add(sc);
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
	public boolean AddShopColorInfo(int ShopId, String Color, String S_Img,String B_Img) {
		String sql = "insert into ShopColor(ShopId,Color,S_Img,B_Img) values(?,?,?,?)";
		Object[] sz = {ShopId,Color,S_Img,B_Img,};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByShopIdGetShopCountInfo(int shopid) {
		String sql = "select count(*) as a from shopColor where shopid = ?";
		Object[] sz = {shopid};
		ResultSet rs = super.executeQuery(sql, sz);
		int row = 1;
		try {
			while(rs.next()){
				row = rs.getInt("a") + 1;
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
	public boolean ByShopIdDelInfo(int shopid) {
		String sql = "delete from shopColor where shopid = ?";
		Object[] sz = {shopid};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByShopIdGetCount(int shopid) {
		String sql = "select count(*) as c from ShopColor where shopid = ?";
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
				CloseAll(getConnection(), rs.getStatement(),null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return row;
	}

}
