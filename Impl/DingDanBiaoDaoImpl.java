package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.DingDanBiaoDao;
import org.YaoLeGouDB.entity.DingDanBiao;
import org.YaoLeGouDB.entity.DingDanState;
import org.YaoLeGouDB.entity.DingDanXiangQing;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.entity.XiaoXiTongZhi;
import org.YaoLeGouDB.entity.ZhiFuType;
import org.YaoLeGouDB.utils.BaseDao;

public class DingDanBiaoDaoImpl extends BaseDao implements DingDanBiaoDao {

	@Override
	public boolean AddDingDanInfo(DingDanBiao ddb) {
		String sql = "insert into DingDanBiao(DingDanNumber,ZhuRenUser,Price,StateId,CreateTime,UserName,ZhiFuId,AddressId) values(?,?,?,1,default,?,?,?)";
		Object[] sz = {ddb.getDingdannumber(),ddb.getZhurenuser(),ddb.getPrice(),ddb.getUsername(),ddb.getZhifuid(),ddb.getAddressid()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByMyUserGetCount(String MyUser) {
		String sql = "select DingDanNumber from DingDanBiao where ZhuRenUser = ? and (stateid = 1 or stateid = 2) group by DingDanNumber";
		Object[] sz = {MyUser};
		ResultSet rs = super.executeQuery(sql, sz);
		int row= 0;
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
	public List<DingDanBiao> ByZhuRenUserGetInfo(String ZhuRenUser) {
		String sql = "select ddb.DingDanNumber,ddb.createtime,shad.city,shad.address,ddxq.stateid from DingDanBiao as ddb inner join shaddress as shad on(ddb.AddressId = shad.id) inner join dingdanxiangqing as ddxq on(ddb.dingdannumber = ddxq.dingdannumber) where ZhuRenUser = ? and ddb.stateid = 1 or ddb.stateid = 2 group by DingDanNumber";
		Object[] sz = {ZhuRenUser};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				DingDanXiangQing ddxq = new DingDanXiangQing();
				ddxq.setStateid(rs.getInt("ddxq.stateid"));
				ddb.setDdxq(ddxq);
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shad.city")+" "+rs.getString("shad.address"));
				ddb.setShaddress(sha);
				list.add(ddb);
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
	public List<DingDanBiao> ByZhuRenUserGetAllFaHuoStateInfo(String ZhuRenUser) {
		String sql = "select * from dingdanxiangqing as ddxq "+
					"inner join ShopInfo as si on(ddxq.shopid = si.shopid) "+
					"inner join DingDanBiao as ddb on(ddb.DingDanNumber = ddxq.DingDanNumber) "+
					"inner join _ZhiFuType as zft on(ddb.zhifuid = zft.ZhiFuId) "+
					"inner join _DingDanState as dds on(ddxq.StateId = dds.StateId) "+
					"inner join SHAddress as shd on(ddb.AddressId = shd.Id) "+
					"where ddxq.DingDanNumber = ? and (ddxq.stateid = 1 or ddxq.stateid = 2) group by ddxq.id";
		Object[] sz = {ZhuRenUser};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setAddressid(rs.getInt("ddb.addressid"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setId(rs.getInt("ddb.id"));
				ddb.setPrice(rs.getDouble("ddb.price"));
				ddb.setStateid(rs.getInt("ddb.stateid"));
				ddb.setUsername(rs.getString("ddb.username"));
				ddb.setZhifuid(rs.getInt("ddb.zhifuid"));
				ddb.setZhurenuser(rs.getString("ddb.zhurenuser"));
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				ddb.setDingdanstate(dds);
				
				ZhiFuType zft = new ZhiFuType();
				zft.setZhifuid(rs.getInt("zft.zhifuid"));
				zft.setZhifuname(rs.getString("zft.zhifuname"));
				ddb.setZhifutype(zft);
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shd.address"));
				sha.setByphone(rs.getString("shd.byphone"));
				sha.setCity(rs.getString("shd.city"));
				sha.setDefaults(rs.getInt("shd.default"));
				sha.setId(rs.getInt("shd.id"));
				sha.setLable(rs.getString("shd.lable"));
				sha.setName(rs.getString("shd.name"));
				sha.setPhone(rs.getString("shd.phone"));
				sha.setUsername(rs.getString("shd.username"));
				ddb.setShaddress(sha);
				
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
				ddb.setDdxq(ddxq);
				
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
				ddb.setSi(si);
				
				list.add(ddb);
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
	public int ByUserNameGetDingHuoTongZhiCount(String UserName) {
		String sql = "select ddb.DingDanNumber,ddb.createtime,shad.city,shad.address from DingDanBiao as ddb inner join shaddress as shad on(ddb.AddressId = shad.id) where ddb.UserName = ? group by DingDanNumber";
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
	public List<DingDanBiao> ByUserNameGetInfo(String UserName) {
		String sql = "select ddb.DingDanNumber,ddb.createtime,shad.city,shad.address,dds.stateid,dds.statename from DingDanBiao as ddb inner join shaddress as shad on(ddb.AddressId = shad.id) inner join _DingDanState as dds on(ddb.StateId = dds.StateId) where ddb.UserName = ? group by DingDanNumber";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				ddb.setDingdanstate(dds);
				ddb.setStateid(rs.getInt("stateid"));
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shad.city")+" "+rs.getString("shad.address"));
				ddb.setShaddress(sha);
				list.add(ddb);
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
	public List<DingDanBiao> ByUserGetAllStateInfo(String DingDanNumber,String UserName) {
		String sql = "select * from dingdanxiangqing as ddxq "+
					"inner join ShopInfo as si on(ddxq.shopid = si.shopid) "+
					"inner join _DingDanState as dds on(ddxq.StateId = dds.StateId) "+
					"inner join DingDanBiao as ddb on(ddxq.DingDanNumber = ddb.DingDanNumber) "+
					"inner join _ZhiFuType as zft on(ddb.zhifuid = zft.ZhiFuId) "+
					"inner join SHAddress as shd on(ddb.AddressId = shd.Id) "+
					"inner join xiaoxitongzhi as xxtz on(ddxq.dingdannumber = xxtz.dingdannumber) "+
					"where ddxq.DingDanNumber = ? and ddxq.UserName = ? group by ddxq.id";
		Object[] sz = {DingDanNumber,UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setAddressid(rs.getInt("ddb.addressid"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setId(rs.getInt("ddb.id"));
				ddb.setPrice(rs.getDouble("ddb.price"));
				ddb.setStateid(rs.getInt("ddb.stateid"));
				ddb.setUsername(rs.getString("ddb.username"));
				ddb.setZhifuid(rs.getInt("ddb.zhifuid"));
				ddb.setZhurenuser(rs.getString("ddb.zhurenuser"));
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				ddb.setDingdanstate(dds);
				
				ZhiFuType zft = new ZhiFuType();
				zft.setZhifuid(rs.getInt("zft.zhifuid"));
				zft.setZhifuname(rs.getString("zft.zhifuname"));
				ddb.setZhifutype(zft);
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shd.address"));
				sha.setByphone(rs.getString("shd.byphone"));
				sha.setCity(rs.getString("shd.city"));
				sha.setDefaults(rs.getInt("shd.default"));
				sha.setId(rs.getInt("shd.id"));
				sha.setLable(rs.getString("shd.lable"));
				sha.setName(rs.getString("shd.name"));
				sha.setPhone(rs.getString("shd.phone"));
				sha.setUsername(rs.getString("shd.username"));
				ddb.setShaddress(sha);
				
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
				ddb.setDdxq(ddxq);
				
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
				ddb.setSi(si);
				
				XiaoXiTongZhi xxtz = new XiaoXiTongZhi();
				xxtz.setContext(rs.getString("xxtz.context"));
				xxtz.setCreatetime(rs.getString("xxtz.createtime"));
				xxtz.setDingdannumber(rs.getString("xxtz.dingdannumber"));
				xxtz.setId(rs.getInt("xxtz.id"));
				xxtz.setMyuser(rs.getString("xxtz.myuser"));
				xxtz.setState(rs.getInt("xxtz.state"));
				xxtz.setTitle(rs.getString("xxtz.title"));
				xxtz.setUsername(rs.getString("xxtz.username"));
				ddb.setXxtz(xxtz);
				
				list.add(ddb);
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
	public boolean ByDingDanNumberUpdateFaHuoInfo(String DingDanNumber) {
		String sql = "update dingdanbiao set stateid = 2 where dingdannumber = ?";
		Object[] sz = {DingDanNumber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByUserNameGetYiWanChengInfoCount(String UserName) {
		String sql = "select ddb.DingDanNumber,ddb.createtime,shad.city,shad.address from DingDanBiao as ddb inner join shaddress as shad on(ddb.AddressId = shad.id) where ddb.ZhuRenUser = ? and ddb.stateid = 3 group by DingDanNumber";
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
	public boolean ByDingDanNumberQueRenShouHuo(String DingDanNumber) {
		String sql = "update dingdanbiao set stateid = 3 where dingdannumber = ?";
		Object[] sz = {DingDanNumber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public List<DingDanBiao> ByUserNameGetYiWanChengInfo(String UserName) {
		String sql = "select ddb.DingDanNumber,ddb.createtime,shad.city,shad.address from DingDanBiao as ddb inner join shaddress as shad on(ddb.AddressId = shad.id) where ddb.ZhuRenUser = ? and ddb.stateid = 3 group by DingDanNumber";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shad.city")+" "+rs.getString("shad.address"));
				ddb.setShaddress(sha);
				list.add(ddb);
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
	public List<DingDanBiao> ByZhuRenUserGetAllYiWanChengStateInfo(String ZhuRenUser) {
		String sql = "select * from dingdanbiao as ddb "+
				"inner join _DingDanState as dds on(ddb.StateId = dds.StateId)" +
				"inner join _ZhiFuType as zft on(ddb.zhifuid = zft.ZhiFuId)" +
				"inner join DingDanXiangQing as ddxq on(ddb.DingDanNumber = ddxq.DingDanNumber)"+
				"inner join SHAddress as shd on(ddb.AddressId = shd.Id)"+
				"inner join ShopInfo as si on(ddxq.shopid = si.shopid)"+
				"where ddb.DingDanNumber = ? and ddb.stateid = 3 group by si.shopid";
		Object[] sz = {ZhuRenUser};
		ResultSet rs = super.executeQuery(sql, sz);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setAddressid(rs.getInt("ddb.addressid"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setId(rs.getInt("ddb.id"));
				ddb.setPrice(rs.getDouble("ddb.price"));
				ddb.setStateid(rs.getInt("ddb.stateid"));
				ddb.setUsername(rs.getString("ddb.username"));
				ddb.setZhifuid(rs.getInt("ddb.zhifuid"));
				ddb.setZhurenuser(rs.getString("ddb.zhurenuser"));
				
				DingDanState dds = new DingDanState();
				dds.setStateid(rs.getInt("dds.stateid"));
				dds.setStatename(rs.getString("dds.statename"));
				ddb.setDingdanstate(dds);
				
				ZhiFuType zft = new ZhiFuType();
				zft.setZhifuid(rs.getInt("zft.zhifuid"));
				zft.setZhifuname(rs.getString("zft.zhifuname"));
				ddb.setZhifutype(zft);
				
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("shd.address"));
				sha.setByphone(rs.getString("shd.byphone"));
				sha.setCity(rs.getString("shd.city"));
				sha.setDefaults(rs.getInt("shd.default"));
				sha.setId(rs.getInt("shd.id"));
				sha.setLable(rs.getString("shd.lable"));
				sha.setName(rs.getString("shd.name"));
				sha.setPhone(rs.getString("shd.phone"));
				sha.setUsername(rs.getString("shd.username"));
				ddb.setShaddress(sha);
				
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
				ddb.setDdxq(ddxq);
				
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
				ddb.setSi(si);
				
				list.add(ddb);
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
	public boolean ByUserNameAndMoneyAddMoney(String Money, String UserName) {
		String sql = "update UserInfo set DianPuMoney = DianPuMoney + ? where UserName = ?";
		Object[] sz = {Money,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int Count() {
		String sql = "select count(*) as c from dingdanbiao";
		ResultSet rs = super.executeQuery(sql, null);
		int row = 0;
		try {
			while(rs.next()){
				row = rs.getInt("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<DingDanBiao> GetAllInfo() {
		String sql = "select * from dingdanbiao as ddb inner join UserInfo as ui on(ddb.ZhuRenUser = ui.UserName) inner join _zhifutype as zft on(ddb.zhifuid = zft.zhifuid)";
		ResultSet rs = super.executeQuery(sql, null);
		List<DingDanBiao> list = new ArrayList<DingDanBiao>();
		try {
			while(rs.next()){
				DingDanBiao ddb = new DingDanBiao();
				ddb.setAddressid(rs.getInt("ddb.addressid"));
				ddb.setCreatetime(rs.getString("ddb.createtime"));
				ddb.setDingdannumber(rs.getString("ddb.dingdannumber"));
				ddb.setId(rs.getInt("ddb.id"));
				ddb.setPrice(rs.getDouble("ddb.price"));
				ddb.setStateid(rs.getInt("ddb.stateid"));
				ddb.setUsername(rs.getString("ddb.username"));
				ddb.setZhifuid(rs.getInt("ddb.zhifuid"));
				ddb.setZhurenuser(rs.getString("ddb.zhurenuser"));
				
				ZhiFuType zft = new ZhiFuType();
				zft.setZhifuid(rs.getInt("zft.zhifuid"));
				zft.setZhifuname(rs.getString("zft.zhifuname"));
				ddb.setZhifutype(zft);
				
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
				ddb.setUi(ui);
				
				list.add(ddb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean UpdateAddressInfo(int address, String DingDanNuber) {
		String sql = "update dingdanbiao set addressid = ? where dingdannumber = ?";
		Object[] sz = {address,DingDanNuber};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int GetAllCount() {
		String sql = "select count(*) as c from dingdanbiao where stateid = 3";
		ResultSet rs = super.executeQuery(sql, null);
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
