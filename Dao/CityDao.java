package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.City;

/**
 * 市接口
 * @author lenovo
 *
 */
public interface CityDao {

	public List<City> ByShengIdGetAllInfo(int ShengId);
	
	/**
	 * 根据市名获取id
	 * @param CityName
	 * @return
	 */
	public City ByCityNameGetId(String CityName);
}
