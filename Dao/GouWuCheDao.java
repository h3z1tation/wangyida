package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.GouWuChe;

public interface GouWuCheDao {

	//加入购物车
	public boolean AddGouWuCheInfo(GouWuChe gwc);
	
	//根据UserName查询购物车信息
	public List<GouWuChe> GetAllGouWuCheInfo(String UserName);
	
	//根据账号信息查询购物车总数量
	public int GetSize(String UserName);
	
	//根据UserName删除购物车信息
	
	public boolean ByUserNameDelInfo(String UserName,int id);
	
	//根据用户id和商品id查询 该商品是否是收藏的
	public GouWuChe ByShopIdAndUserNameIsExist(String UserName,int ShopId,String Chicun,String Color);
	
	//更新商品信息
	public boolean UpdateInfo(String chicun,String color,int count,String username,int shopid,int id);
	
	//根据id、UserName、商品id增加购物车的数量
	public boolean ByIdUserNameAndShopIdUpdateCountInfojia(int id,String UserName,int ShopId,int count);
	
	//根据id、UserName、商品id减去购物车的数量
	public boolean ByIdUserNameAndShopIdUpdateCountInfojian(int id,String UserName,int ShopId,int count);
	
	//根据id和UserName删除购物车信息
	public boolean ByIdUserDeleteInfo(int id,String UserName);
}
