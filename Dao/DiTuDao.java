package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.DiTu;

public interface DiTuDao {

	
	//查询所有地图用户
	public List<DiTu> GetAllInfo();
	
	//增加地图信息
	public boolean AddInfo(String address);
	
	//判断是否存在
	public boolean Isexsts(String address);
}
