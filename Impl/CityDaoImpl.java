package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.CityDao;
import org.YaoLeGouDB.entity.City;
import org.YaoLeGouDB.utils.BaseDao;

public class CityDaoImpl extends BaseDao implements CityDao {

	@Override
	public List<City> ByShengIdGetAllInfo(int ShengId) {
		String sql = "select * from city where ShengId = ?";
		Object[] sz = {ShengId};
		ResultSet rs = super.executeQuery(sql, sz);
		List<City> list = new ArrayList<City>();
		try {
			while(rs.next()){
				City c = new City();
				c.setCityid(rs.getInt("cityid"));
				c.setCityname(rs.getString("cityname"));
				c.setShengid(rs.getInt("shengid"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public City ByCityNameGetId(String CityName) {
		String sql = "select * from city where CityName = ?";
		Object[] sz = {CityName};
		ResultSet rs = super.executeQuery(sql, sz);
		City city = null;
		try {
			while(rs.next()){
				city = new City();
				city.setCityid(rs.getInt("cityid"));
				city.setCityname(rs.getString("cityname"));
				city.setShengid(rs.getInt("shengid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return city;
	}

}
