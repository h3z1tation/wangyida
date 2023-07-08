package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.SexDao;
import org.YaoLeGouDB.entity.Sex;
import org.YaoLeGouDB.utils.BaseDao;

public class SexDaoImpl extends BaseDao implements SexDao {

	@Override
	public List<Sex> TJSexCount() {
		String sql = "select count(*) as c,ui.sexid,s.sexname from userinfo as ui inner join _Sex as s on(ui.sexid = s.sexid) group by ui.sexid";
		ResultSet rs = super.executeQuery(sql, null);
		List<Sex> list = new ArrayList<Sex>();
		try {
			while(rs.next()){
				Sex s = new Sex();
				s.setCount(rs.getInt("c"));
				s.setSexname(rs.getString("s.sexname"));
				list.add(s);
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
