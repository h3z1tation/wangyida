package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.YaoLeGouDB.Dao.TotalPirceDao;
import org.YaoLeGouDB.utils.BaseDao;

public class TotalPirceDaoImpl extends BaseDao implements TotalPirceDao {

	@Override
	public boolean AddMoney(int Money) {
		String sql = "update TotalPirce set Price = Price + ?";
		Object[] sz = {Money};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public int GetMoneyCount() {
		String sql = "select Price from TotalPirce";
		int Money = 0;
		ResultSet rs = super.executeQuery(sql, null);
		try {
			while(rs.next()){
				Money = rs.getInt("Price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Money;
	}

}
