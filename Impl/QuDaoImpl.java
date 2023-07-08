package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.QuDao;
import org.YaoLeGouDB.entity.Qu;
import org.YaoLeGouDB.utils.BaseDao;

public class QuDaoImpl extends BaseDao implements QuDao {

	@Override
	public List<Qu> ByCityIdGetAllInfo(int CityId) {
		String sql = "select * from qu where cityid = ?";
		Object[] sz = {CityId};
		ResultSet rs = super.executeQuery(sql, sz);
		List<Qu> list = new ArrayList<Qu>();
		try {
			while(rs.next()){
				Qu q = new Qu();
				q.setCityid(rs.getInt("cityid"));
				q.setQuid(rs.getInt("quid"));
				q.setQuname(rs.getString("quname"));
				list.add(q);
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

}
