package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.JiBenInfoDao;
import org.YaoLeGouDB.entity.JiBenInfo;
import org.YaoLeGouDB.utils.BaseDao;

public class JiBenInfoDaoImpl extends BaseDao implements JiBenInfoDao {

	@Override
	public List<JiBenInfo> GetAllInfo() {
		String sql = "select * from jibeninfo";
		ResultSet rs = super.executeQuery(sql, null);
		List<JiBenInfo> list = new ArrayList<JiBenInfo>();
		try {
			while(rs.next()){
				JiBenInfo jbi = new JiBenInfo();
				jbi.setId(rs.getInt("id"));
				jbi.setKey(rs.getString("key"));
				jbi.setValue(rs.getString("value"));
				list.add(jbi);
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
	public boolean ByKeyUpdateContext(String key, String value) {
		String sql = "update jibeninfo set `value` = ? where `key` = ?";
		Object[] sz = {value,key};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean UpdateTongJi(String key, String value, String yuan) {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public boolean UpdateTongJi(String key, String value, String yuan) {
		String sql = "update jibeninfo set `value` = ? where `key` = ?";
		Object[] sz = {value,key};
		return super.executeUpdate(sql, sz);
	}*/

}
