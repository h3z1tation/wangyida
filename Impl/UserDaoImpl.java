package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.YaoLeGouDB.Dao.UserDao;
import org.YaoLeGouDB.entity.Sex;
import org.YaoLeGouDB.entity.User;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public User ClickLogin(User u) {
		String sql = "select * from `User` as u inner join UserInfo as ui on(u.UserName = ui.`UserName`) inner join _Sex as s on(ui.`SexId` = s.`SexId`) where ui.`Phone` = ? and u.`PassWord` = ? or ui.`Email` = ? and u.`PassWord` = ? or ui.`UserName` = ? and u.`PassWord` = ?";
		Object[] sz = {u.getUsername(),u.getPassword(),u.getUsername(),u.getPassword(),u.getUsername(),u.getPassword()};
		ResultSet rs = super.executeQuery(sql, sz);
		User user = null;
		try {
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("u.id"));
				user.setUsername(rs.getString("u.username"));
				user.setPassword(rs.getString("u.password"));
				user.setState(rs.getInt("u.state"));
				user.setCreatetime(rs.getString("u.createtime"));
				
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
				
				Sex s = new Sex();
				s.setSexid(rs.getInt("s.sexid"));
				s.setSexname(rs.getString("s.sexname"));
				ui.setSex(s);
				user.setUserinfo(ui);
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
		return user;
	}

	@Override
	public boolean AddUserNameInfo(User u) {
		String sql = "insert into `User`(UserName,`PassWord`,State,CreateTime) values(?,?,'0',default)";
		Object[] sz = {u.getUsername(),u.getPassword()};
		boolean is = super.executeUpdate(sql, sz);
		sql = "insert into UserInfo values(?,?,null,null,null,null,null,?,3,0,'0.00','0.00','attached/TouXiang/default.gif',date_format(now(),'%Y-%m-%d'))";
		Object[] sz2 = {u.getUsername(),u.getUserinfo().getUsernick(),u.getUserinfo().getPhone()};
		is = super.executeUpdate(sql, sz2);
		return is;
	}

	@Override
	public boolean UpdateStateWei0(String UserName) {
		String sql = "update user set state = 0 where UserName = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean UpdateStateWei1(String UserName) {
		String sql = "update user set state = 1 where UserName = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int ByUserNameGetStateInfo(String UserName) {
		String sql = "select state from user where username = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		int state = 0;
		try {
			while(rs.next()){
				state = rs.getInt("state");
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
		return state;
	}

	@Override
	public int Count() {
		String sql = "select count(*) as c from `User`";
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
	public boolean ByUserNameDelUserInfo(String UserName) {
		String sql = "delete from `User` where UserName = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNameUpdatePass(String UserName, String Pass) {
		String sql = "update `User` set password = ? where username = ?";
		Object[] sz = {Pass,UserName};
		return super.executeUpdate(sql, sz);
	}

}
