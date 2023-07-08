package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.LoginAdmin;

/**
 * 后台管理验证表
 * @author lenovo
 *
 */
public interface LoginAdminDao {

	
	//根据后台账号和密码验证后台账号是否正确
	public LoginAdmin ByUserNameAndPassClickIsOk(String UserName,String Pass);
	
	//获得全部管理员信息
	public List<LoginAdmin> GetAllInfo();
	
	//更改状态为启用
	public boolean UpdateStateWei0(String UserName);
	
	//更改状态为禁用
	public boolean UpdateStateWei1(String UserName);
	
	//添加管理员信息
	public boolean AddAdminInfo(LoginAdmin la);
	
	//根据id获得管理员信息
	public LoginAdmin ByIdGetAllInfo(String UserName);
	
	//根据管理员UserName更新信息
	public boolean ByUserNameUpdateInfo(LoginAdmin la);
	
	//根据管理员账号删除信息
	public boolean ByUserNamedelInfo(String UserName);
	
	//根据UserName更改密码
	public boolean ByUserNameUpdatePass(String UserName,String pass);
}
