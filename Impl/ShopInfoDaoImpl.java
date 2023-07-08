package org.YaoLeGouDB.Impl;
/**
 * 商品实现类
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.ShopInfoDao;
import org.YaoLeGouDB.entity.ShopInfo;
import org.YaoLeGouDB.entity.ShopType;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class ShopInfoDaoImpl extends BaseDao implements ShopInfoDao {

	@Override
	public List<ShopInfo> GetUserInfoAllShop(int pageNo,int pageSize,String UserName,ShopInfo sis) {
		String sql = "select * from ShopInfo as si inner join _ShopType as st on(si.shoptypeid = st.shoptypeid) where si.ZhuRenUser = ? " + append(sis) +" order by si.CreateTime, si.xiajia asc "+" Limit ?,? ";
 		Object[] sz = {UserName,(pageNo-1)*pageSize,pageSize};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			while(rs.next()){
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
				si.setXiajia(rs.getInt("si.xiajia"));
				
				ShopType st = new ShopType();
				st.setShoptypeid(rs.getInt("st.shoptypeid"));
				st.setShoptypename(rs.getString("st.shoptypename"));
				si.setShoptype(st);
				list.add(si);
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

	public String append(ShopInfo si){
		//声明超级字符串
		StringBuffer sb = new StringBuffer();
		if(si.getShopname() != null && !si.getShopname().equals("")){
			sb.append(" and si.ShopName like '%"+si.getShopname()+"%'");
		}
		if(si.getShoptypeid() != 0){
			sb.append(" and si.ShopTypeId = "+si.getShoptypeid());
		}
		if(si.getSexfenlei() != null && !si.getSexfenlei().equals("全")){
			sb.append(" and si.shopname like '%"+si.getSexfenlei()+"%'");
		}
		if(si.getMa() != null && !si.getMa().equals("全部品牌")){
			sb.append(" and si.shopname like '%"+si.getMa()+"%'");
		}
		if(si.getBanxing() != null && !si.getBanxing().equals("全部")){
			sb.append(" and si.shopname like '%"+si.getBanxing()+"%'");
		}
		if(si.getB() != null && !si.getB().equals("") && si.getS() != null 
				&& !si.getS().equals("")){
			sb.append(" and si.shopprice > "+si.getS()+" and shopprice < "+si.getB());
		}
		if(si.getShopname() != null && !si.getShopname().equals("")){
			sb.append(" and si.shopname like '%"+si.getShopname()+"%'");
		}
		if(si.getTypearray() != null && !si.getTypearray().equals("")){
			sb.append(" and si.ShopTypeId = '"+si.getTypearray()+"'");
		}
		//返回一个字符串类型的
		return sb.toString();
	}
	
	@Override
	public int ByUserInfoGetShopCount(String UserName) {
		String sql = "select count(*) as s from ShopInfo where ZhuRenUser = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		int row = 0;
		try {
			if(rs.next()){
				row = rs.getInt("s");
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
	public List<ShopInfo> ByShopTypeGetAllInfo(int pageNo,int pageSize,ShopInfo sis) {
		String sql = "select * from ShopInfo as si inner join _ShopType as st on(si.shoptypeid = st.shoptypeid) where si.xiajia = 1 and 1=1 "+append(sis)+" order by CreateTime desc" + " Limit ?,? ";
		Object[] sz = {(pageNo-1)*pageSize,pageSize};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			while(rs.next()){
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
				si.setXiajia(rs.getInt("si.xiajia"));
				
				ShopType st = new ShopType();
				st.setShoptypeid(rs.getInt("st.shoptypeid"));
				st.setShoptypename(rs.getString("st.shoptypename"));
				si.setShoptype(st);
				list.add(si);
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
	public ShopInfo ByShopIdGetAllInfo(int ShopId) {
		String sql = "select * from ShopInfo as si inner join _ShopType as st on(si.shoptypeid = st.shoptypeid) where si.xiajia = 1 and si.shopid = ?";
		Object[] sz = {ShopId};
		ResultSet rs = super.executeQuery(sql, sz);
		ShopInfo si = null;
		try {
			while(rs.next()){
				si = new ShopInfo();
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
				si.setXiajia(rs.getInt("si.xiajia"));
				
				ShopType st = new ShopType();
				st.setShoptypeid(rs.getInt("st.shoptypeid"));
				st.setShoptypename(rs.getString("st.shoptypename"));
				si.setShoptype(st);
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
		return si;
	}

	@Override
	public boolean AddInfoInfo(ShopInfo si) {
		String sql = "insert into ShopInfo(ShopName,ShopMiaoShu,ShopPrice,ChiCun,Color,ZhuRenUser,ShopTypeId,Image,Context,CreateTime,XiaoLiangCount,xiajia)"
				+ " values(?,?,?,?,?,?,?,?,?,now(),0,1)";
		Object[] sz = {si.getShopname(),si.getShopmiaoshu(),si.getShopprice(),si.getChicun(),si.getColor(),si.getZhuRenUser(),si.getShopid(),si.getImage(),si.getContext()};
		return executeUpdate(sql, sz);
	}

	@Override
	public int GetShopId(String shopname, String ShopMiaoShu, String ZhuRenUser) {
		String sql = "select * from ShopInfo where shopname = ? and shopmiaoshu = ? and ZhuRenUser = ?";
		Object[] sz = {shopname,ShopMiaoShu,ZhuRenUser};
		ResultSet rs = super.executeQuery(sql, sz);
		int id = 0;
		try {
			while(rs.next()){
				id = rs.getInt("ShopId");
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
		return id;
	}
	
	@Override
	public boolean ByIdUpdateShopImage(int shopid,String Image) {
		String sql = "update ShopInfo set Image = ? where ShopId = ?";
		Object[] sz = {Image,shopid};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdAndUserNameDelShopInfo(int id, String UserName) {
		String sql = "delete from ShopInfo where shopid = ? and ZhuRenUser = ?";
		Object[] sz = {id,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByShopTypeGetCount(ShopInfo sis) {
		String sql = "select count(*) as c from shopinfo as si where si.xiajia = 1 and 1=1 "+append(sis);
		ResultSet rs = super.executeQuery(sql, null);
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
	public boolean ByShopIdAddCount(int shopid,int count) {
		String sql = "update shopinfo set xiaoliangcount = xiaoliangcount + ? where shopid = ?";
		Object[] sz = {count,shopid};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public List<ShopInfo> GetAllInfo(ShopInfo sis) {
		String sql = "select * from ShopInfo as si inner join _ShopType as st on(si.shoptypeid = st.shoptypeid) "+append(sis);
		ResultSet rs = super.executeQuery(sql, null);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			while(rs.next()){
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
				si.setXiajia(rs.getInt("si.xiajia"));
				
				ShopType st = new ShopType();
				st.setShoptypeid(rs.getInt("st.shoptypeid"));
				st.setShoptypename(rs.getString("st.shoptypename"));
				si.setShoptype(st);
				list.add(si);
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
	public List<ShopInfo> GetBigShopXiaoLing() {
		String sql = "select * from shopinfo as si inner join UserInfo as ui on(si.zhurenuser = ui.username) order by si.xiaoliangcount desc limit 0,20";
		ResultSet rs = super.executeQuery(sql, null);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			while(rs.next()){
				
				ShopInfo si = new ShopInfo();
				si.setXiaoliangcount(rs.getInt("si.xiaoliangcount"));
				si.setShopid(rs.getInt("si.shopid"));
				si.setShopname(rs.getString("si.shopname"));
				si.setImage(rs.getString("si.image"));
				si.setXiajia(rs.getInt("si.xiajia"));
				
				UserInfo ui = new UserInfo();
				ui.setUsername(rs.getString("ui.username"));
				ui.setUsernick(rs.getString("ui.usernick"));
				
				si.setUi(ui);
				list.add(si);
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
	public List<ShopInfo> ByTypeIdGetBigShopXiaoLing(int typeid) {
		String sql = "select * from shopinfo as si inner join UserInfo as ui on(si.zhurenuser = ui.username) where si.xiajia = 1 and si.ShopTypeId = ? order by si.xiaoliangcount desc limit 0,5";
		Object[] sz = {typeid};
		ResultSet rs = super.executeQuery(sql, sz);
		List<ShopInfo> list = new ArrayList<ShopInfo>();
		try {
			while(rs.next()){
				
				ShopInfo si = new ShopInfo();
				si.setXiaoliangcount(rs.getInt("si.xiaoliangcount"));
				si.setShopid(rs.getInt("si.shopid"));
				si.setShopname(rs.getString("si.shopname"));
				si.setImage(rs.getString("si.image"));
				si.setShopprice(rs.getShort("si.shopprice"));
				si.setXiajia(rs.getInt("si.xiajia"));
				
				UserInfo ui = new UserInfo();
				ui.setUsername(rs.getString("ui.username"));
				ui.setUsernick(rs.getString("ui.usernick"));
				
				si.setUi(ui);
				list.add(si);
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
	public boolean ByShopidUpdateXiaJia(int shopid, int state) {
		String sql = "update shopinfo set xiajia = ? where shopid = ?";
		Object[] sz = {state,shopid};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int Count() {
		String sql = "select count(*) as c from shopinfo";
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
