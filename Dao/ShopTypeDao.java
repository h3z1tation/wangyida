package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.ShopType;

public interface ShopTypeDao {

	
	public List<ShopType> GetAllShopTypeInfo();
	
	//添加商品分类
	public boolean AddShopTypeInfo(String TypeName);
	
	//根据类型id删除信息
	public boolean ByTypeIdDelInfo(String id);
	
	//根据TypeId获得信息
	public ShopType ByTypeIDGetAllInfo(String id);
	
	//根据id修改信息
	public boolean ByIdUpdateInfo(String id,String name);
	
	//统计商品那种分类用的最多
	public List<ShopType> TJTypeInfo();
}
