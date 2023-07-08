package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.UserInfo;

public interface UserInfoDao {
	
	public UserInfo ByUserNameGetInfo(String UserName);
	
	public boolean UpdateInfo(UserInfo ui,String address2);
	
	/**
	 * 开通店铺
	 */
	
	public boolean KaiTongDianPu(String shenfen,String username);
	/**
	 * 判断 改身份证是否存在
	 */
	
	public UserInfo IsShenFenExist(String shenfen);
	
	/**
	 * 判断开通店铺金额是否足够
	 */
	public boolean ByIdIsMoney(String username);
	
	//获得所有用户
	public List<UserInfo> GetAllInfo();
	
	//根据UserName删除用户信息
	public boolean ByUserNameDelUserInfo(String UserName);
	
	//根据UserName添加金额
	public boolean ByUserNameAddMoney(String UserName);
	
	//查询手机号是否存在
	public boolean ByPhoneGetAllInfo(String Phone);
}
