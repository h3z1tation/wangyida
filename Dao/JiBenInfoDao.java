package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.JiBenInfo;

/**
 * 基本信息接口
 * @author lenovo
 *
 */
public interface JiBenInfoDao {

	/**
	 * 查询全部的网站上的基本信息
	 * @return
	 */
	public List<JiBenInfo> GetAllInfo();
	
	//根据key值更改value
	public boolean ByKeyUpdateContext(String key,String value);
	
	//修改统计
	public boolean UpdateTongJi(String key,String value,String yuan);
	
}