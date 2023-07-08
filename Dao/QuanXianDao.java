package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.QuanXian;

public interface QuanXianDao{

	
	//根据后台账号查询权限
	public QuanXian ByLoginAdminGetInfo(String LoginAdmin);
	
	//根据后台管理员添加权限
	public boolean ByAdminAddInfo(String UserName,int guanli);
	
	//查询所有后台账号的权限
	public List<QuanXian> GetAllInfo();
	
	//根据后台账号更改状态信息
	public boolean ByUserNameUpdateInfo(String UserName,String weizhi,String stateid);
	
	//根据UserName删除信息
	public boolean ByUserNameDelInfo(String UserName);
}
