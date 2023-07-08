package org.YaoLeGouDB.entity;

public class XiaoXiTongZhi {

	private int id;
	private String dingdannumber;
	private String username;
	private String title;
	private String context;
	private int state;
	private String createtime;
	
	private DingDanXiangQing ddxq;
	private ShopInfo si;
	private String myuser;
	
	
	
	
	public String getMyuser() {
		return myuser;
	}
	public void setMyuser(String myuser) {
		this.myuser = myuser;
	}
	public DingDanXiangQing getDdxq() {
		return ddxq;
	}
	public void setDdxq(DingDanXiangQing ddxq) {
		this.ddxq = ddxq;
	}
	public ShopInfo getSi() {
		return si;
	}
	public void setSi(ShopInfo si) {
		this.si = si;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
