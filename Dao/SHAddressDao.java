package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.SHAddress;

/**
 * 收货地址接口
 * @author lenovo
 *
 */
public interface SHAddressDao {

	
	//根据UserName查询全部收货地址
	public List<SHAddress> GetAllInfo(String UserName);
	
	//根据UserName更新默认地址
	public boolean ByUserNameAndIdUpdateInfo(String UserName,int id);
	
	//根据UserName和默认地址取消更新
	public boolean ByUserNameAndDefaultAddressCloseUpdate(String UserName);
	
	//添加收货地址
	public boolean AddSHAddressInfo(SHAddress sha);
	
	//删除收货地址
	public boolean ByIdDelInfo(int id,String UserName);
	
	//根据收货id查询收货信息
	public SHAddress ByIdGetAllInfo(int id);
}
