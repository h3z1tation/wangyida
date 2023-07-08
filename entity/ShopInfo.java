package org.YaoLeGouDB.entity;

public class ShopInfo {

	private int shopid;
	private String shopname;
	private String shopmiaoshu;
	private double shopprice;
	private String chicun;
	private String color;

	public String getShopmiaoshu() {
		return shopmiaoshu;
	}

	public void setShopmiaoshu(String shopmiaoshu) {
		this.shopmiaoshu = shopmiaoshu;
	}

	private String zhuRenUser;
	private int shoptypeid;
	private String image;
	private String context;
	private String createtime;
	
	private ShopType shoptype;
	private int xiajia;

	
	
	
	public int getXiajia() {
		return xiajia;
	}

	public void setXiajia(int xiajia) {
		this.xiajia = xiajia;
	}

	//·ÖÒ³×Ö¶Î
	private String sexfenlei;
	private String ma;
	private String banxing;
	private String s;
	private String b;
	private String typearray;
	
	private int xiaoliangcount;

	private UserInfo ui;

	
	public UserInfo getUi() {
		return ui;
	}

	public void setUi(UserInfo ui) {
		this.ui = ui;
	}

	public int getXiaoliangcount() {
		return xiaoliangcount;
	}

	public void setXiaoliangcount(int xiaoliangcount) {
		this.xiaoliangcount = xiaoliangcount;
	}

	public String getTypearray() {
		return typearray;
	}

	public void setTypearray(String typearray) {
		this.typearray = typearray;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getBanxing() {
		return banxing;
	}

	public void setBanxing(String banxing) {
		this.banxing = banxing;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getSexfenlei() {
		return sexfenlei;
	}

	public void setSexfenlei(String sexfenlei) {
		this.sexfenlei = sexfenlei;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public double getShopprice() {
		return shopprice;
	}

	public void setShopprice(double shopprice) {
		this.shopprice = shopprice;
	}

	public String getChicun() {
		return chicun;
	}

	public void setChicun(String chicun) {
		this.chicun = chicun;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public String getZhuRenUser() {
		return zhuRenUser;
	}

	public void setZhuRenUser(String zhuRenUser) {
		this.zhuRenUser = zhuRenUser;
	}

	public int getShoptypeid() {
		return shoptypeid;
	}

	public void setShoptypeid(int shoptypeid) {
		this.shoptypeid = shoptypeid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public ShopType getShoptype() {
		return shoptype;
	}

	public void setShoptype(ShopType shoptype) {
		this.shoptype = shoptype;
	}
	
	
}
