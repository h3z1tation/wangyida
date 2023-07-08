package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.ShouYeShopDao;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.ShouYeShop;
import org.YaoLeGouDB.utils.BaseDao;

public class ShouYeShopDaoImpl extends BaseDao implements ShouYeShopDao {

	@Override
	public List<ShouYeShop> ByShopTyIdGetAllInfo(int shoptypeid) {
		String sql = "select * from shouyeshop as sys inner join shopinfo as si on(sys.shopid = si.shopid) where si.xiajia = 1 and sys.shoptypeid = ?";
		Object[] sz = {shoptypeid};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShouYeShop> list = new ArrayList<ShouYeShop>();
		try {
			while(rs.next()){
				ShouYeShop sys = new ShouYeShop();
				sys.setId(rs.getInt("sys.id"));
				sys.setShopid(rs.getInt("sys.shopid"));
				sys.setShoptypeid(rs.getInt("sys.shoptypeid"));
				
				ShopInfo si = new ShopInfo();
				si.setShopid(rs.getInt("si.shopid"));
				si.setImage(rs.getString("si.image"));
				si.setShopname(rs.getString("si.shopname"));
				si.setShopprice(rs.getShort("si.shopprice"));
				sys.setSi(si);
				list.add(sys);
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
	public boolean ByIdAndTypeIdDelInfo(String id, String typeid) {
		String sql = "delete from shouyeshop where ShopId = ? and ShopTypeid = ?";
		Object[] sz = {id,typeid};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean AddInfo(String typeid, String shopid) {
		String sql = "insert into ShouYeShop(ShopId,ShopTypeid) values(?,?)";
		Object[] sz = {shopid,typeid};
		return super.executeUpdate(sql, sz);
	}

}
