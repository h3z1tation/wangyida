package org.YaoLeGouDB.Impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.UserInfoDao;
import org.YaoLeGouDB.entity.IsDianPu;
import org.YaoLeGouDB.entity.Sex;
import org.YaoLeGouDB.entity.User;
import org.YaoLeGouDB.entity.UserInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {

	@Override
	public UserInfo ByUserNameGetInfo(String UserName) {
		String sql = "select * from UserInfo as ui inner join _Sex as s on(ui.`SexId` = s.`SexId`) inner join User as u on(ui.username = u.username) where ui.UserName = ? ";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql, sz);
		UserInfo ui = null;
		try {
			while(rs.next()){
				
				ui = new UserInfo();
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
				
				Sex s = new Sex();
				s.setSexid(rs.getInt("s.sexid"));
				s.setSexname(rs.getString("s.sexname"));
				ui.setSex(s);
				
				User u = new User();
				u.setCreatetime(rs.getString("u.createtime"));
				ui.setU(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ui;
	}

	@Override
	public boolean UpdateInfo(UserInfo ui,String address2) {
		
		//处理业务是否为空
		
		//头像
		String image;
		if(ui.getImage() != null && !ui.getImage().equals("")){
			image = ",Image = '"+ui.getImage()+"'";
		}else{
			image = " ";
		}
		
		String Name;
		if(ui.getName() != null && !ui.getName().equals("")){
			Name=",`Name` = '"+ui.getName()+"'";
		}else{
			Name = " ";
		}
		
		String Email;
		if(ui.getEmail() != null && !ui.getEmail().equals("")){
			Email = ",Email = '"+ui.getEmail()+"'";
		}else{
			Email = " ";
		}
		
		/*Date ChuShengRiQi;
		if(ui.getBirthday() != null && !ui.getBirthday().equals("")){
			ChuShengRiQi = ",birthday = '"+ui.getBirthday()+"'";
		}else{
			ChuShengRiQi = " ";
		}*/
		
		
		String Address;
		if(ui.getAddress() != null && !ui.getAddress().equals("")){
			Address = ",Address = '"+ui.getAddress() + ";" + address2+"'";
		}else{
			
			Address = " ";
		}
		
		String content1;
		if(ui.getJianjie() != null && !ui.getJianjie().equals("")){
			content1 = ",JianJie = '"+ui.getJianjie()+"'";
		}else{
			content1 = ",JianJie = '这个人很懒，什么留言也没有。'";
		}
		
		String sql = "update UserInfo set UserNick = ?"+Name+content1+",ShenFenZheng = ?"+Address+Email+",Phone = ?,SexId = ?"+image+" where UserName = ?";
		Object[] sz = {ui.getUsernick(),ui.getShenfenzheng(),ui.getPhone(),ui.getSexid(),ui.getUsername()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean KaiTongDianPu(String shenfen,String username) {
		String sql = "update userinfo set IsDianPu = 1,ShenFenZheng = ?,Money = Money - 1500 where username = ?";
		Object[] sz = {shenfen,username};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public UserInfo IsShenFenExist(String shenfen) {
		/**
		 * 改成单表查询 身份证号
		 */
		String sql = "select * from UserInfo where ShenFenZheng = ? ";
		Object[] sz = {shenfen};
		ResultSet rs = super.executeQuery(sql, sz);
		UserInfo ui = null;
		try {
			while(rs.next()){
				ui = new UserInfo();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ui;
	}

	@Override
	public boolean ByIdIsMoney(String username) {
		String sql = "select * from userinfo where UserName = ?";
		Object[] sz = {username};
		ResultSet rs = super.executeQuery(sql, sz);
		int Money = 0;
		try {
			while(rs.next()){
				Money = rs.getInt("Money");
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
		boolean is = false;
		System.out.println(Money);
		if(Money >= 1500){
			is = true;
		}else{
			is = false;
		}
		return is;
	}

	@Override
	public List<UserInfo> GetAllInfo() {
		String sql = "select * from UserInfo as ui inner join _Sex as s on(ui.`SexId` = s.`SexId`) inner join User as u on(ui.username = u.username) inner join _IsDianPu as idp on(ui.isdianpu = idp.dianpuid)";
		ResultSet rs = super.executeQuery(sql, null);
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			while(rs.next()){
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
				
				Sex s = new Sex();
				s.setSexid(rs.getInt("s.sexid"));
				s.setSexname(rs.getString("s.sexname"));
				ui.setSex(s);
				
				User u = new User();
				u.setCreatetime(rs.getString("u.createtime"));
				u.setState(rs.getInt("u.state"));
				ui.setU(u);
				
				IsDianPu idp = new IsDianPu();
				idp.setDianpuid(rs.getInt("idp.dianpuid"));
				idp.setDianpuname(rs.getString("idp.DingPuName"));
				ui.setIdp(idp);
				
				list.add(ui);
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
	public boolean ByUserNameDelUserInfo(String UserName) {
		String sql = "delete from UserInfo where UserName = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNameAddMoney(String UserName) {
		String sql = "update userinfo set money = money + 2500 where username = ?";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByPhoneGetAllInfo(String Phone) {
		String sql = "select * from userinfo where phone = ?";
		Object[] sz = {Phone};
		ResultSet rs = super.executeQuery(sql, sz);
		boolean is = true;
		try {
			while(rs.next()){
				is = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	

}
