package org.YaoLeGouDB.Dao;
/**
 * 省的Dao接口
 */
import java.util.List;

import org.YaoLeGouDB.entity.Sheng;

public interface ShengDao {
	
	/**
	 * 查询全部信息
	 * @return
	 */
	public List<Sheng> GetAllInfo();
	
	/**
	 * 根据省的名称查找省的id
	 * @param ShengName
	 * @return
	 */
	public Sheng ByNameGatInfo(String ShengName);
}
