package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.SHAddressDao;
import org.YaoLeGouDB.entity.SHAddress;
import org.YaoLeGouDB.utils.BaseDao;

public class SHAddressDaoImpl extends BaseDao implements SHAddressDao {

	@Override
	public List<SHAddress> GetAllInfo(String UserName) {
		String sql = "select * from SHAddress where UserName = ?";
		Object[] sz = {UserName};
		ResultSet rs = super.executeQuery(sql,sz);
		List<SHAddress> list = new ArrayList<SHAddress>();
		try {
			while(rs.next()){
				SHAddress sha = new SHAddress();
				sha.setAddress(rs.getString("address"));
				sha.setByphone(rs.getString("byphone"));
				sha.setCity(rs.getString("city"));
				sha.setDefaults(rs.getInt("default"));
				sha.setId(rs.getInt("id"));
				sha.setLable(rs.getString("lable"));
				sha.setName(rs.getString("name"));
				sha.setPhone(rs.getString("phone"));
				sha.setUsername(rs.getString("username"));
				list.add(sha);
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
	public boolean ByUserNameAndIdUpdateInfo(String UserName, int id) {
		String sql = "update SHAddress set `default` = 1 where UserName = ? and id = ?";
		Object[] sz = {UserName,id};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByUserNameAndDefaultAddressCloseUpdate(String UserName) {
		String sql = "update shaddress set `default` = 0 where UserName = ? and `default` = 1";
		Object[] sz = {UserName};
		return super.executeUpdate(sql, sz);

	}

	@Override
	public boolean AddSHAddressInfo(SHAddress sha) {
		
		String Byphone = "null,";
		if(sha.getByphone() == ""){
			Byphone = "null,";
		}else{
			Byphone = "'"+sha.getByphone()+"',";
		}
		
		String lable = "null,";
		if(sha.getLable() == ""){
			lable = "null,";
		}else{
			lable = "'"+sha.getLable()+"',";
		}
		
		
		
		String sql = "insert into SHAddress(UserName,`Name`,Phone,ByPhone,City,Address,Lable,`Default`) values(?,?,?,"+Byphone+"?,?,"+lable+"?)";
		Object[] sz = {sha.getUsername(),sha.getName(),sha.getPhone(),sha.getCity(),sha.getAddress(),sha.getDefaults()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByIdDelInfo(int id,String UserName) {
		String sql = "delete from SHAddress where id = ? and UserName = ?";
		Object[] sz = {id,UserName};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public SHAddress ByIdGetAllInfo(int id) {
		String sql = "select * from SHAddress where id = ?";
		Object[] sz = {id};
		ResultSet rs = super.executeQuery(sql,sz);
		SHAddress sha = null;
		try {
			while(rs.next()){
				sha = new SHAddress();
				sha.setAddress(rs.getString("address"));
				sha.setByphone(rs.getString("byphone"));
				sha.setCity(rs.getString("city"));
				sha.setDefaults(rs.getInt("default"));
				sha.setId(rs.getInt("id"));
				sha.setLable(rs.getString("lable"));
				sha.setName(rs.getString("name"));
				sha.setPhone(rs.getString("phone"));
				sha.setUsername(rs.getString("username"));
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
		return sha;
	}

}
