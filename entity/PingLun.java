package org.YaoLeGouDB.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class PingLun {

	private int id;
	private int shopid;
	private String username;
	private String context;
	private String myuser;
	@JSONField(format="yyyy-MM-dd")
	private String createtime;
	private int myuserstate;
	private UserInfo ui;
	
	
	public UserInfo getUi() {
		return ui;
	}
	public void setUi(UserInfo ui) {
		this.ui = ui;
	}
	public int getMyuserstate() {
		return myuserstate;
	}
	public void setMyuserstate(int myuserstate) {
		this.myuserstate = myuserstate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMyuser() {
		return myuser;
	}
	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}
	
	
}
