package org.YaoLeGouDB.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {
	
	private String username;
	private String usernick;
	private String name;
	private String jianjie;
	private String shenfenzheng;
	private String address;
	private String email;
	private String phone;
	private int sexid;
	private int isdianpu;
	private double money;
	private double dianpumoney;
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;

	private String image;
	private Sex sex;
	private String createtime;
	private User u;
	private IsDianPu idp;
	
	
	
	public IsDianPu getIdp() {
		return idp;
	}
	public void setIdp(IsDianPu idp) {
		this.idp = idp;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernick() {
		return usernick;
	}
	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJianjie() {
		return jianjie;
	}
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	public String getShenfenzheng() {
		return shenfenzheng;
	}
	public void setShenfenzheng(String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSexid() {
		return sexid;
	}
	public void setSexid(int sexid) {
		this.sexid = sexid;
	}
	public int getIsdianpu() {
		return isdianpu;
	}
	public void setIsdianpu(int isdianpu) {
		this.isdianpu = isdianpu;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getDianpumoney() {
		return dianpumoney;
	}
	public void setDianpumoney(double dianpumoney) {
		this.dianpumoney = dianpumoney;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
}
