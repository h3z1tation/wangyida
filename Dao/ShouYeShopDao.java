package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.ShouYeShop;

public interface ShouYeShopDao {

	//根据id类型id获取商品信息
	public List<ShouYeShop> ByShopTyIdGetAllInfo(int shoptypeid);
	
	//根据id和类型 删除信息
	public boolean ByIdAndTypeIdDelInfo(String id,String typeid);
	
	//添加首页商品信息
	public boolean AddInfo(String typeid,String shopid);
}
