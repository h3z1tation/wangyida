package org.YaoLeGouDB.entity;

public class DaoHangTiao {

	private int id;
	private String daohangtiaoname;
	private String href;
	private int istingyong;
	private String createtime;
	
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDaohangtiaoname() {
		return daohangtiaoname;
	}
	public void setDaohangtiaoname(String daohangtiaoname) {
		this.daohangtiaoname = daohangtiaoname;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getIstingyong() {
		return istingyong;
	}
	public void setIstingyong(int istingyong) {
		this.istingyong = istingyong;
	}
	
}
