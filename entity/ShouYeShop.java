package org.YaoLeGouDB.entity;

public class ShouYeShop {

	private int id;
	private int shopid;
	private int shoptypeid;
	
	private ShopInfo si;
	
	
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
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public int getShoptypeid() {
		return shoptypeid;
	}
	public void setShoptypeid(int shoptypeid) {
		this.shoptypeid = shoptypeid;
	}
}
