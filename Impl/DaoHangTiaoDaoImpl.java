package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.DaoHangTiaoDao;
import org.YaoLeGouDB.entity.DaoHangTiao;
import org.YaoLeGouDB.utils.BaseDao;

public class DaoHangTiaoDaoImpl extends BaseDao implements DaoHangTiaoDao {
	
	@Override
	public List<DaoHangTiao> GetAllInfo() {
		String sql = "select * from daohangtiao";
		ResultSet rs = super.executeQuery(sql, null);
		List<DaoHangTiao> list = new ArrayList<DaoHangTiao>();
		try {
			while(rs.next()){
				DaoHangTiao dht = new DaoHangTiao();
				dht.setId(rs.getInt("id"));
				dht.setDaohangtiaoname(rs.getString("daohangtiaoname"));
				dht.setHref(rs.getString("href"));
				dht.setIstingyong(rs.getInt("istingyong"));
				dht.setCreatetime(rs.getString("createtime"));
				list.add(dht);
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
	public boolean ByidUpdateStateWei0(int id) {
		String sql = "update daohangtiao set istingyong = 0 where id = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByidUpdateStateWei1(int id) {
		String sql = "update daohangtiao set istingyong = 1 where id = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean AddInfo(DaoHangTiao dht) {
		String sql = "insert into DaoHangTiao(DaoHangTIaoName,Href,IsTingYong,CreateTime) values(?,?,'0',now())";
		Object[] sz = {dht.getDaohangtiaoname(),dht.getHref()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdDelInfo(int id) {
		String sql = "delete from daohangtiao where id = ?";
		Object[] sz = {id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public DaoHangTiao ByIdGetAllInfo(int id) {
		String sql = "select * from daohangtiao where id = ?";
		Object[] sz = {id};
		ResultSet rs = super.executeQuery(sql, sz);
		DaoHangTiao dht = null;
		try {
			while(rs.next()){
				dht = new DaoHangTiao();
				dht.setId(rs.getInt("id"));
				dht.setDaohangtiaoname(rs.getString("daohangtiaoname"));
				dht.setHref(rs.getString("href"));
				dht.setIstingyong(rs.getInt("istingyong"));
				dht.setCreatetime(rs.getString("createtime"));
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
		return dht;
	}

	@Override
	public boolean ByIdUpdateInfo(DaoHangTiao dht) {
		String sql = "update daohangtiao set DaoHangTiaoName = ? , href = ? where id = ?";
		Object[] sz = {dht.getDaohangtiaoname(),dht.getHref(),dht.getId()};
		return super.executeUpdate(sql, sz);
	}
	
	
}
