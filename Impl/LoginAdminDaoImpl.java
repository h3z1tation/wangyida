package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.LoginAdminDao;
import org.YaoLeGouDB.entity.LoginAdmin;
import org.YaoLeGouDB.entity.Sex;
import org.YaoLeGouDB.entity.ShenFen;
import org.YaoLeGouDB.utils.BaseDao;

public class LoginAdminDaoImpl extends BaseDao implements LoginAdminDao {

	@Override
	public LoginAdmin ByUserNameAndPassClickIsOk(String UserName, String Pass) {
		String sql = "select * from LoginAdmin as la where UserName = ? and password = ?";
		Object[] sz = {UserName,Pass};
		ResultSet rs = super.executeQuery(sql, sz);
		LoginAdmin la = null;
		try {
			while(rs.next()){
				la = new LoginAdmin();
				la.setBeizhu(rs.getString("la.beizhu"));
				la.setCreatetime(rs.getString("la.createtime"));
				la.setId(rs.getInt("la.id"));
				la.setName(rs.getString("la.name"));
				la.setPassword(rs.getString("la.password"));
				la.setPhone(rs.getString("la.phone"));
				la.setSexid(rs.getInt("la.sexid"));
				la.setShenfenid(rs.getInt("la.shenfenid"));
				la.setUsername(rs.getString("la.username"));
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
		return la;
	}

	@Override
	public List<LoginAdmin> GetAllInfo() {
		String sql = "select * from LoginAdmin as la inner join _shenfen as sf on(la.shenfenid = sf.shenfenid)";
		ResultSet rs = super.executeQuery(sql, null);
		List<LoginAdmin> list = new ArrayList<LoginAdmin>();
		try {
			while(rs.next()){
				LoginAdmin la = new LoginAdmin();
				la.setBeizhu(rs.getString("beizhu"));
				la.setCreatetime(rs.getString("createtime"));
				la.setId(rs.getInt("id"));
				la.setName(rs.getString("name"));
				la.setPassword(rs.getString("password"));
				la.setPhone(rs.getString("phone"));
				la.setSexid(rs.getInt("sexid"));
				la.setShenfenid(rs.getInt("shenfenid"));
				la.setUsername(rs.getString("username"));
				la.setState(rs.getInt("state"));
				
				ShenFen sf = new ShenFen();
				sf.setShenfenid(rs.getInt("shenfenid"));
				sf.setShenfenname(rs.getString("shenfenname"));
				la.setSf(sf);
				
				list.add(la);
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
	public boolean UpdateStateWei0(String id) {
		String sql = "update loginadmin set state = 0 where id = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean UpdateStateWei1(String id) {
		String sql = "update loginadmin set state = 1 where id = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean AddAdminInfo(LoginAdmin la) {
		String sql = "insert into LoginAdmin(UserName,`PassWord`,ShenfenId,`Name`,Phone,CreateTime,sexid,beizhu,State) values(?,?,?,?,?,default,?,?,0)";
		Object[] sz = {la.getUsername(),la.getPassword(),la.getShenfenid(),la.getName(),la.getPhone(),la.getSexid(),la.getBeizhu()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public LoginAdmin ByIdGetAllInfo(String UserName) {
		String sql = "select * from LoginAdmin as la where UserName = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		LoginAdmin la = null;
		try {
			while(rs.next()){
				la = new LoginAdmin();
				la.setBeizhu(rs.getString("la.beizhu"));
				la.setCreatetime(rs.getString("la.createtime"));
				la.setId(rs.getInt("la.id"));
				la.setName(rs.getString("la.name"));
				la.setPassword(rs.getString("la.password"));
				la.setPhone(rs.getString("la.phone"));
				la.setSexid(rs.getInt("la.sexid"));
				la.setShenfenid(rs.getInt("la.shenfenid"));
				la.setUsername(rs.getString("la.username"));
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
		return la;
	}

	@Override
	public boolean ByUserNameUpdateInfo(LoginAdmin la){
		String sql = "update loginadmin set UserName = ?,Password = ?,ShenFenId = ?,name = ?,phone = ?,sexid = ?,beizhu = ? where id = ?";
		Object[] sz = {la.getUsername(),la.getPassword(),la.getShenfenid(),la.getName(),la.getPhone(),la.getSexid(),la.getBeizhu(),la.getId()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNamedelInfo(String UserName) {
		String sql = "delete from loginadmin where UserName = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNameUpdatePass(String UserName, String pass) {
		String sql = "update LoginAdmin set `password` = ? where username = ?";
		Object[] sz = {pass,UserName};
		return super.executeUpdate(sql, sz);
	}

}
