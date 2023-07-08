package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.ZhiFuTypeDao;
import org.YaoLeGouDB.entity.ZhiFuType;
import org.YaoLeGouDB.utils.BaseDao;

public class ZhiFuTypeDaoImpl extends BaseDao implements ZhiFuTypeDao {

	@Override
	public List<ZhiFuType> GetAllInfo() {
		String sql = "select * from _ZhiFuType";
		ResultSet rs = super.executeQuery(sql, null);
		List<ZhiFuType> list = new ArrayList<ZhiFuType>();
		try {
			while(rs.next()){
				ZhiFuType zft = new ZhiFuType();
				zft.setZhifuid(rs.getInt("zhifuid"));
				zft.setZhifuname(rs.getString("zhifuname"));
				list.add(zft);
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
