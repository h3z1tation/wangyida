package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.XiaoXiTongZhiDao;
import org.YaoLeGouDB.entity.DingDanXiangQing;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.XiaoXiTongZhi;
import org.YaoLeGouDB.utils.BaseDao;

public class XiaoXiTongZhiDaoImpl extends BaseDao implements XiaoXiTongZhiDao {

	@Override
	public int GetXiaoXiTongZhiCount(String MyUser) {
		String sql = "select count(*) as c from xiaoxitongzhi where UserName = ?";
		Object[] sz = {MyUser};
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
	public List<XiaoXiTongZhi> ByUserNameGetAllInfo(String UserName) {
		String sql = "select * from xiaoxitongzhi as xxtz "+
					"inner join DingDanXiangQing as ddxq on(xxtz.DingDanNumber = ddxq.DingDanNumber)"+
					"inner join ShopInfo as si on(ddxq.shopid = si.shopid) "+
					"where xxtz.UserName = ? group by xxtz.dingdannumber";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		List<XiaoXiTongZhi> list = new ArrayList<XiaoXiTongZhi>();
		try {
			while(rs.next()){
				XiaoXiTongZhi xxtz = new XiaoXiTongZhi();
				xxtz.setContext(rs.getString("xxtz.context"));
				xxtz.setCreatetime(rs.getString("xxtz.createtime"));
				xxtz.setDingdannumber(rs.getString("xxtz.dingdannumber"));
				xxtz.setId(rs.getInt("xxtz.id"));
				xxtz.setMyuser(rs.getString("xxtz.myuser"));
				xxtz.setState(rs.getInt("xxtz.state"));
				xxtz.setTitle(rs.getString("xxtz.title"));
				xxtz.setUsername(rs.getString("xxtz.username"));
				
				
				list.add(xxtz);
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
	public XiaoXiTongZhi ByDingDanNumberAndUserNameGetAllInfo(String DingDanNumber, String UserName) {
		String sql = "select * from xiaoxitongzhi as xxtz "+
				"inner join DingDanXiangQing as ddxq on(xxtz.DingDanNumber = ddxq.DingDanNumber)"+
				"inner join ShopInfo as si on(ddxq.shopid = si.shopid) "+
				"where xxtz.DingDanNumber = ? and xxtz.UserName = ?";
		Object[] sz = {DingDanNumber,UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		XiaoXiTongZhi xxtz = null;
		try {
			while(rs.next()){
				xxtz = new XiaoXiTongZhi();
				xxtz.setContext(rs.getString("xxtz.context"));
				xxtz.setCreatetime(rs.getString("xxtz.createtime"));
				xxtz.setDingdannumber(rs.getString("xxtz.dingdannumber"));
				xxtz.setId(rs.getInt("xxtz.id"));
				xxtz.setMyuser(rs.getString("xxtz.myuser"));
				xxtz.setState(rs.getInt("xxtz.state"));
				xxtz.setTitle(rs.getString("xxtz.title"));
				xxtz.setUsername(rs.getString("xxtz.username"));
				
				DingDanXiangQing ddxq = new DingDanXiangQing();
				ddxq.setChicui(rs.getString("ddxq.chicui"));
				ddxq.setColor(rs.getString("ddxq.color"));
				ddxq.setCount(rs.getInt("ddxq.count"));
				ddxq.setDingdannumber(rs.getString("ddxq.dingdannumber"));
				ddxq.setShopid(rs.getInt("ddxq.shopid"));
				ddxq.setId(rs.getInt("ddxq.id"));
				ddxq.setMyuser(rs.getString("ddxq.myuser"));
				ddxq.setCreatetime(rs.getString("ddxq.createtime"));
				ddxq.setState(rs.getString("ddxq.state"));
				xxtz.setDdxq(ddxq);
				
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
				xxtz.setSi(si);
				
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
		return xxtz;
	}

	@Override
	public boolean UpdateXiaoXiXiongZhiWei0(String DingDanNumber,String UserName) {
		String sql = "update xiaoxitongzhi set state = 0 where dingdannumber = ? and UserName = ?";
		Object[] sz = {DingDanNumber,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean AddXiaoXiTongZhiInfo(XiaoXiTongZhi xxtz) {
		String sql = "insert into XiaoXiTongZhi(DingDanNumber,UserName,Title,Context,State,CreateTime,MyUser) values(?,?,?,?,'1',now(),?)";
		Object[] sz = {xxtz.getDingdannumber(),xxtz.getUsername(),xxtz.getTitle(),xxtz.getContext(),xxtz.getMyuser()};
		return super.executeUpdate(sql, sz);
	}
}
