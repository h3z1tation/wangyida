package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.ShengDao;
import org.YaoLeGouDB.entity.Sheng;
import org.YaoLeGouDB.utils.BaseDao;

public class ShengDaoImpl extends BaseDao implements ShengDao {

	@Override
	public List<Sheng> GetAllInfo() {
		String sql = "select * from sheng";
		ResultSet rs = super.executeQuery(sql, null);
		List<Sheng> list = new ArrayList<Sheng>();
		try {
			while(rs.next()){
				Sheng sheng = new Sheng();
				sheng.setShengid(rs.getInt("shengid"));
				sheng.setShengname(rs.getString("shengname"));
				list.add(sheng);
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
	public Sheng ByNameGatInfo(String ShengName) {
		String sql = "select * from sheng where shengname = ?";
		Object[] sz = {ShengName};
		ResultSet rs = super.executeQuery(sql, sz);
		Sheng sheng = null;
		try {
			while(rs.next()){
				sheng = new Sheng();
				sheng.setShengid(rs.getInt("shengid"));
				sheng.setShengname(rs.getString("shengname"));
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
		return sheng;
	}

}
