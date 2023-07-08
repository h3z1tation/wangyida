package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.PingLun;

/**
 * 评论接口
 * @author lenovo
 *
 */
public interface PingLunDao {

	
	//根据ShopId查询改件商品的评论
	public List<PingLun> ByShopIdGetAllInfo(int shopid);
	
	//新增评论
	public boolean AddPingLunInfo(PingLun pl);
	
	//获得全部评论
	public List<PingLun> GetAllInfo(PingLun pl);
	
	//根据id删除信息
	public boolean ByIdDelInfo(int id);
	
	//获得评论数量
	public int Count();
}
