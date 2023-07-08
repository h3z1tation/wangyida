package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.Qu;

public interface QuDao {

	
	public List<Qu> ByCityIdGetAllInfo(int CityId);
}
