package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.QuanXianDao;
import org.YaoLeGouDB.entity.LoginAdmin;
import org.YaoLeGouDB.entity.QuanXian;
import org.YaoLeGouDB.utils.BaseDao;

public class QuanXianDaoImpl extends BaseDao implements QuanXianDao {

	@Override
	public QuanXian ByLoginAdminGetInfo(String LoginAdmin) {
		String sql = "select * from QuanXian where AdminUser = ?";
		Object[] sz = {LoginAdmin};
		ResultSet rs = super.executeQuery(sql, sz);
		QuanXian qx = null;
		try {
			while(rs.next()){
				qx = new QuanXian();
				qx.setAdminuser(rs.getString("adminuser"));
				qx.setAdmin(rs.getInt("admin"));
				qx.setJibenadmin(rs.getInt("jibenadmin"));
				qx.setShopadmin(rs.getInt("shopadmin"));
				qx.setShouyeadmin(rs.getInt("shouyeadmin"));
				qx.setTongjiadmin(rs.getInt("tongjiadmin"));
				qx.setUseradmin(rs.getInt("useradmin"));
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
		return qx;
	}

	@Override
	public boolean ByAdminAddInfo(String UserName,int guanli) {
		String sql = "insert into QuanXian values(?,'0','0','0',?,'0','0')";
		Object[] sz = {UserName,guanli};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public List<QuanXian> GetAllInfo() {
		String sql = "select * from QuanXian as qx inner join loginAdmin as la on(la.UserName = qx.adminUser)";
		ResultSet rs = super.executeQuery(sql, null);
		List<QuanXian> list = new ArrayList<QuanXian>();
		try {
			while(rs.next()){
				QuanXian qx = new QuanXian();
				qx.setAdminuser(rs.getString("qx.adminuser"));
				qx.setAdmin(rs.getInt("qx.admin"));
				qx.setJibenadmin(rs.getInt("qx.jibenadmin"));
				qx.setShopadmin(rs.getInt("qx.shopadmin"));
				qx.setShouyeadmin(rs.getInt("qx.shouyeadmin"));
				qx.setTongjiadmin(rs.getInt("qx.tongjiadmin"));
				qx.setUseradmin(rs.getInt("qx.useradmin"));
				
				LoginAdmin la = new LoginAdmin();
				la.setBeizhu(rs.getString("la.beizhu"));
				la.setCreatetime(rs.getString("la.createtime"));
				la.setId(rs.getInt("la.id"));
				la.setName(rs.getString("la.name"));
				la.setPassword(rs.getString("la.password"));
				la.setPhone(rs.getString("la.phone"));
				la.setSexid(rs.getInt("la.sexid"));
				la.setShenfenid(rs.getInt("la.shenfenid"));
				la.setUsername(rs.getString("la.username"));
				qx.setLa(la);
				
				list.add(qx);
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
	public boolean ByUserNameUpdateInfo(String UserName, String weizhi,String stateid) {
		String sql = null;
		if(weizhi.equals("admin")){
			sql = "update quanxian set admin = ? where AdminUser = ?";
		}else if(weizhi.equals("shouyeadmin")){
			sql = "update quanxian set shouyeadmin = ? where AdminUser = ?";
		}else if(weizhi.equals("shopadmin")){
			sql = "update quanxian set shopadmin = ? where AdminUser = ?";
		}else if(weizhi.equals("useradmin")){
			sql = "update quanxian set useradmin = ? where AdminUser = ?";
		}else if(weizhi.equals("tongjiadmin")){
			sql = "update quanxian set tongjiadmin = ? where AdminUser = ?";
		}else if(weizhi.equals("jibenadmin")){
			sql = "update quanxian set jibenadmin = ? where AdminUser = ?";
		}
		int state = 0;
		if(stateid.equals("0")){
			state = 1;
		}
		
		Object[] sz = {state,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNameDelInfo(String UserName){
		String sql = "delete from quanxian where AdminUser = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}
	
}
