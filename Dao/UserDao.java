package org.YaoLeGouDB.Dao;

import org.YaoLeGouDB.entity.User;

public interface UserDao {
	
	public User ClickLogin(User u);
	
	public boolean AddUserNameInfo(User u);
	
	//更改状态为0启用
	public boolean UpdateStateWei0(String UserName);
	
	//更改状态为1停用
	public boolean UpdateStateWei1(String UserName);
	
	//根据UserName查询停用状态
	public int ByUserNameGetStateInfo(String UserName);
	
	//查询商城总用户数
	public int Count();
	
	//根据UserName删除用户信息
	public boolean ByUserNameDelUserInfo(String UserName);
	
	//根据UserName修改密码
	public boolean ByUserNameUpdatePass(String UserName,String Pass);
}
