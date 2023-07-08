package org.YaoLeGouDB.entity;

public class LoginAdmin {
	
	private int id;
	private String username;
	private String password;
	private int shenfenid;
	private String name;
	private String phone;
	private String createtime;
	private int sexid;
	private String beizhu;
	private ShenFen sf;
	private int state;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public ShenFen getSf() {
		return sf;
	}
	public void setSf(ShenFen sf) {
		this.sf = sf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getShenfenid() {
		return shenfenid;
	}
	public void setShenfenid(int shenfenid) {
		this.shenfenid = shenfenid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getSexid() {
		return sexid;
	}
	public void setSexid(int sexid) {
		this.sexid = sexid;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	
}
