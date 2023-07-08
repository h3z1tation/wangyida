package org.YaoLeGouDB.entity;

public class DingDanXiangQing {
	private int id;
	private String dingdannumber;
	private int shopid;
	private String chicui;
	private String color;
	private int count;
	
	private String username;
	private ShopInfo si;
	private UserInfo ui;
	private String myuser;
	private String createtime;
	private String state;
	private DingDanState dds;
	private int stateid;
	
	
	
	
	public int getStateid() {
		return stateid;
	}
	public void setStateid(int stateid) {
		this.stateid = stateid;
	}
	public DingDanState getDds() {
		return dds;
	}
	public void setDds(DingDanState dds) {
		this.dds = dds;
	}
	public String getMyuser() {
		return myuser;
	}
	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ShopInfo getSi() {
		return si;
	}
	public void setSi(ShopInfo si) {
		this.si = si;
	}
	public UserInfo getUi() {
		return ui;
	}
	public void setUi(UserInfo ui) {
		this.ui = ui;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDingdannumber() {
		return dingdannumber;
	}
	public void setDingdannumber(String dingdannumber) {
		this.dingdannumber = dingdannumber;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getChicui() {
		return chicui;
	}
	public void setChicui(String chicui) {
		this.chicui = chicui;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
