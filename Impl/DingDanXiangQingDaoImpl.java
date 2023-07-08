package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.DingDanXiangQingDao;
import org.YaoLeGouDB.entity.DingDanState;
import org.YaoLeGouDB.entity.DingDanXiangQing;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class DingDanXiangQingDaoImpl extends BaseDao implements DingDanXiangQingDao {

	@Override
	public boolean AddDingDanXingQingInfo(DingDanXiangQing ddxq) {
		String sql = "insert into DingDanXiangQing(DingDanNumber,ShopId,ChiCui,Color,`Count`,UserName,MyUser,CreateTime,State,stateid) values(?,?,?,?,?,?,?,now(),'待付款',0)";
		Object[] sz = {ddxq.getDingdannumber(),ddxq.getShopid(),ddxq.getChicui(),ddxq.getColor(),ddxq.getCount(),ddxq.getUsername(),ddxq.getMyuser()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public List<DingDanXiangQing> GetAllInfo(String DingDanNumber) {
		String sql = "select * from DingDanXiangQing as ddxq inner join _DingDanState as dds on(ddxq.stateid = dds.stateid) inner join UserInfo as ui on(ddxq.UserName = ui.UserName) inner join ShopInfo as si on(ddxq.shopid = si.shopid) where ddxq.DingDanNumber = ? and ddxq.State = '待付款'";
		Object[] sz = {DingDanNumber};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanXiangQing> list = new ArrayList<DingDanXiangQing>();
		try {
			while(rs.next()){
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
				
				UserInfo ui = new UserInfo();
				ui.setAddress(rs.getString("ui.address"));
				ui.setDianpumoney(rs.getDouble("ui.dianpumoney"));
				ui.setEmail(rs.getString("ui.email"));
				ui.setImage(rs.getString("ui.image"));
				ui.setIsdianpu(rs.getInt("ui.isdianpu"));
				ui.setJianjie(rs.getString("ui.jianjie"));
				ui.setMoney(rs.getDouble("ui.money"));
				ui.setName(rs.getString("ui.name"));
				ui.setPhone(rs.getString("ui.phone"));
				ui.setSexid(rs.getInt("ui.sexid"));
				ui.setShenfenzheng(rs.getString("ui.shenfenzheng"));
				ui.setUsername(rs.getString("ui.username"));
				ui.setUsernick(rs.getString("ui.usernick"));
				ui.setBirthday(rs.getDate("ui.birthday"));
				
				ddxq.setUi(ui);
				
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
				
				ddxq.setSi(si);
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				
				ddxq.setDds(dds);
				
				list.add(ddxq);
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
	public int ByUserNameGetCount(String UserName) {
		String sql = "select * from DingDanXiangQing where MyUser = ? and State = '待付款' group by DingDanNumber";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		int row = 0;
		try {
			while(rs.next()){
				row++;
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
	public List<DingDanXiangQing> ByMyUserGeAllInfo(String MyUser) {
		String sql = "select DingDanNumber,Createtime from DingDanXiangQing where MyUser = ? and State = '待付款' group by DingDanNumber";
		Object[] sz = {MyUser};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanXiangQing> list = new ArrayList<DingDanXiangQing>();
		try {
			while(rs.next()){
				DingDanXiangQing ddxq = new DingDanXiangQing();
				ddxq.setDingdannumber(rs.getString("dingdannumber"));
				ddxq.setCreatetime(rs.getString("Createtime"));
				list.add(ddxq);
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
	public List<DingDanXiangQing> ByUserNameAndDingDanNumberGetInfo(String UserName, String DingDanNumber) {
		String sql = "select * from DingDanXiangQing as ddxq inner join _DingDanState as dds on(ddxq.stateid = dds.stateid) inner join UserInfo as ui on(ddxq.UserName = ui.UserName) inner join ShopInfo as si on(ddxq.Shopid = si.shopid) where ddxq.MyUser = ? and DingDanNumber = ?";
		Object[] sz = {UserName,DingDanNumber};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanXiangQing> list = new ArrayList<DingDanXiangQing>();
		try {
			while(rs.next()){
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
				ddxq.setUsername(rs.getString("username"));
				
				UserInfo ui = new UserInfo();
				ui.setAddress(rs.getString("ui.address"));
				ui.setDianpumoney(rs.getDouble("ui.dianpumoney"));
				ui.setEmail(rs.getString("ui.email"));
				ui.setImage(rs.getString("ui.image"));
				ui.setIsdianpu(rs.getInt("ui.isdianpu"));
				ui.setJianjie(rs.getString("ui.jianjie"));
				ui.setMoney(rs.getDouble("ui.money"));
				ui.setName(rs.getString("ui.name"));
				ui.setPhone(rs.getString("ui.phone"));
				ui.setSexid(rs.getInt("ui.sexid"));
				ui.setShenfenzheng(rs.getString("ui.shenfenzheng"));
				ui.setUsername(rs.getString("ui.username"));
				ui.setUsernick(rs.getString("ui.usernick"));
				ui.setBirthday(rs.getDate("ui.birthday"));
				
				ddxq.setUi(ui);
				
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
				
				ddxq.setSi(si);
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				
				ddxq.setDds(dds);
				list.add(ddxq);
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
	public List<DingDanXiangQing> ByMyUserAndUserNameGetAllInfo(String MyUser,String DingDanNumber) {
		String sql = "select * from DingDanXiangQing as ddxq inner join _DingDanState as dds on(ddxq.stateid = dds.stateid)  inner join UserInfo as ui on(ddxq.UserName = ui.UserName) inner join ShopInfo as si on(ddxq.Shopid = si.shopid) where ddxq.MyUser = ? and ddxq.DingDanNumber = ?";
		Object[] sz = {MyUser,DingDanNumber};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanXiangQing> list = new ArrayList<DingDanXiangQing>();
		try {
			while(rs.next()){
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
				ddxq.setUsername(rs.getString("username"));
				
				UserInfo ui = new UserInfo();
				ui.setAddress(rs.getString("ui.address"));
				ui.setDianpumoney(rs.getDouble("ui.dianpumoney"));
				ui.setEmail(rs.getString("ui.email"));
				ui.setImage(rs.getString("ui.image"));
				ui.setIsdianpu(rs.getInt("ui.isdianpu"));
				ui.setJianjie(rs.getString("ui.jianjie"));
				ui.setMoney(rs.getDouble("ui.money"));
				ui.setName(rs.getString("ui.name"));
				ui.setPhone(rs.getString("ui.phone"));
				ui.setSexid(rs.getInt("ui.sexid"));
				ui.setShenfenzheng(rs.getString("ui.shenfenzheng"));
				ui.setUsername(rs.getString("ui.username"));
				ui.setUsernick(rs.getString("ui.usernick"));
				ui.setBirthday(rs.getDate("ui.birthday"));
				
				ddxq.setUi(ui);
				
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
				
				ddxq.setSi(si);
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				
				ddxq.setDds(dds);
				list.add(ddxq);
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
	public boolean ByDingDanNumberUpdateStateInfo(String DingDanNumber) {
		String sql = "update DingDanXiangQing set State = '已付款' where DingDanNumber = ?";
		Object[] sz = {DingDanNumber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByDingDanNumberUpdateStateidWei1(String DingDanNumber) {
		String sql = "update DingDanXiangQing set stateid = 1 where DingDanNumber = ?";
		Object[] sz = {DingDanNumber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByDingDanNumberUpdateStateidWei3(String DingDanNumber) {
		String sql = "update DingDanXiangQing set stateid = 3 where DingDanNumber = ?";
		Object[] sz = {DingDanNumber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByDingDanNumberUpdateStateidWei2(String DingDanNumber,String UserName) {
		String sql = "update DingDanXiangQing set stateid = 2 where DingDanNumber = ? and UserName = ?";
		Object[] sz = {DingDanNumber,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByDingDanNumberGetInfo(String DingDanNumber) {
		String sql = "select * from dingdanxiangqing where DingDanNumber = ?";
		Object[] sz = {DingDanNumber};
		ResultSet rs = super.executeQuery(sql, sz);
		boolean is = true;
		try {
			if(rs.next()){
				is = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	@Override
	public int DaiFaHuoCount() {
		String sql = "select count(*) as c from dingdanxiangqing where stateid = 1";
		ResultSet rs = super.executeQuery(sql,null);
		int count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int DaiFuKuanCount() {
		String sql = "select count(*) as c from dingdanxiangqing where stateid = 0";
		ResultSet rs = super.executeQuery(sql,null);
		int count = 0;
		try {
			while(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
