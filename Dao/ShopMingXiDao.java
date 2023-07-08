package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.ShopMingXi;

/**
 * 商品明细接口
 * @author lenovo
 *
 */
public interface ShopMingXiDao {

	public List<ShopMingXi> ByShopIdGetShopImgInfo(int ShopId);
	
	/**
	 * 往商品明细表里面插入数据
	 */
	public boolean AddShopMingXiInfo(int ShopId,String SImg,String BImg);
	
	//根据商品id查询商品明细表里面的数量
	public int ByShopIdGetInfoCount(int shopid);
	
	//向明细表里面插入数据
	public boolean AddMingXiInfo(ShopMingXi smx);
	
	//根据商品id删除信息
	public boolean ByShopIdDelInfo(int id);
}
