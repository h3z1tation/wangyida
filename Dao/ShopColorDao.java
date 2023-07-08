package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.ShopColor;

/**
 * 商品颜色接口
 * @author lenovo
 *
 */
public interface ShopColorDao {

	/**
	 * 根据 商品 id  和 颜色 参数 查找大小图
	 */
	public List<ShopColor> ByShopIdAndColorGetAllInfo(int ShopId,String Color);
	
	/**
	 * 往商品颜色表里面插入颜色 图片 数据
	 */
	public boolean AddShopColorInfo(int ShopId,String Color,String S_Img,String B_Img);
	
	//根据商品id查询商品数量
	public int ByShopIdGetShopCountInfo(int shopid);
	
	//根据商品id删除商品信息
	public boolean ByShopIdDelInfo(int shopid);
	
	//根据商品颜色表里面的商品id查询颜色数量
	public int ByShopIdGetCount(int shopid);
}
