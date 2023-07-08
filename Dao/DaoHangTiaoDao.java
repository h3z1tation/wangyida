package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.DaoHangTiao;

/**
 * 导航条接口
 * @author lenovo
 *
 */
public interface DaoHangTiaoDao {
	
	/**
	 * 查询全部导航条
	 * @return
	 */
	public List<DaoHangTiao> GetAllInfo();
	
	//根据导航条id修改已启用
	public boolean ByidUpdateStateWei0(int id);
	
	//根据导航条id修改已停用
	public boolean ByidUpdateStateWei1(int id);
	
	//添加导航条
	public boolean AddInfo(DaoHangTiao dht);
	
	//根据Id删除导航条信息
	public boolean ByIdDelInfo(int id);
	
	//根据Id查询导航信息
	public DaoHangTiao ByIdGetAllInfo(int id);
	
	//根据Id更新导航信息
	public boolean ByIdUpdateInfo(DaoHangTiao dht);
}
