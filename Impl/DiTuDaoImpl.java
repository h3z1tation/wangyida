package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.DiTuDao;
import org.YaoLeGouDB.entity.DiTu;
import org.YaoLeGouDB.utils.BaseDao;

public class DiTuDaoImpl extends BaseDao implements DiTuDao {

	@Override
	public List<DiTu> GetAllInfo() {
		String sql = "select * from ditu";
		ResultSet rs = super.executeQuery(sql, null);
		List<DiTu> list = new ArrayList<DiTu>();
		try {
			while(rs.next()){
				DiTu dt = new DiTu();
				dt.setId(rs.getInt("id"));
				dt.setMapname(rs.getString("mapname"));
				list.add(dt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean AddInfo(String address) {
		String sql = "insert into ditu(MapName) values(?)";
		Object[] sz = {address};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean Isexsts(String address) {
		String sql = "select * from ditu where MapName = ?";
		Object[] sz = {address};
		ResultSet rs = super.executeQuery(sql, sz);
		boolean is = true;
		try {
			while(rs.next()){
				is = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

}
