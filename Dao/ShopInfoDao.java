package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.ShopInfo;

/**
 * 商品接口类
 * @author lenovo
 *
 */
public interface ShopInfoDao {

	public List<ShopInfo> GetUserInfoAllShop(int pageNo,int pageSize,String UserName,ShopInfo si);
	
	//查询 商品数量
	public int ByUserInfoGetShopCount(String UserName);
	
	//根据衣服类型查找所有衣服
	public List<ShopInfo> ByShopTypeGetAllInfo(int pageNo,int pageSize,ShopInfo si);
	
	//根据衣服类型查找该商品类型的数量
	public int ByShopTypeGetCount(ShopInfo sis);
	
	//根据商品id查找商品信息
	public ShopInfo ByShopIdGetAllInfo(int ShopId);
	
	/**
	 * 添加商品
	 */
	public boolean AddInfoInfo(ShopInfo si);
	
	//查询商品id
	public int GetShopId(String shopname,String ShopMiaoShu,String ZhuRenUser);
	
	//更改商品封面
	public boolean ByIdUpdateShopImage(int shopid,String Image);
	
	//根据id和UserName删除商品信息
	public boolean ByIdAndUserNameDelShopInfo(int id,String UserName);
	
	//增加销量
	public boolean ByShopIdAddCount(int shopid,int count);
	
	//查询全部商品
	public List<ShopInfo> GetAllInfo(ShopInfo si);
	
	//查询商品销量最多的
	public List<ShopInfo> GetBigShopXiaoLing();
	
	//根据类型查询商品销量最多的
	public List<ShopInfo> ByTypeIdGetBigShopXiaoLing(int typeid);
	
	//更该商品是否下架的状态
	public boolean ByShopidUpdateXiaJia(int shopid,int state);
	
	//获得全部商品数量
	public int Count();
}
