package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.Banner;

/**
 * 轮播广告的接口
 * @author lenovo
 *
 */
public interface BannerDao {

	/**
	 * 获得全部广告信息
	 */
	
	public List<Banner> GetAllInfo();
	
	//根据BannerId修改已启用
	public boolean ByBannerIdUpdateIsTingYong0(int BannerId);
	
	//根据BannerId修改停用
	public boolean ByBannerIdUpdateIsTingYong1(int BannerId);
	
	//添加Banner图
	public boolean AddBannerInfo(Banner banner);
	
	//根据BannerId删除Banner图
	public boolean ByBannerIdDelBanner(String BannerId);
	
	//根据BannerId查询URL连接
	public String ByBannerIdGetImageURL(String BannerId);
}
