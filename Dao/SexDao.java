package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.Sex;

public interface SexDao {

	//统计男女人数
	public List<Sex> TJSexCount();
}
