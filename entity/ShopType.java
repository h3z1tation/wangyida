package org.YaoLeGouDB.entity;

public class ShopType {

	private int shoptypeid;
	private String shoptypename;
	private ShopInfo si;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ShopInfo getSi() {
		return si;
	}
	public void setSi(ShopInfo si) {
		this.si = si;
	}
	public int getShoptypeid() {
		return shoptypeid;
	}
	public void setShoptypeid(int shoptypeid) {
		this.shoptypeid = shoptypeid;
	}
	public String getShoptypename() {
		return shoptypename;
	}
	public void setShoptypename(String shoptypename) {
		this.shoptypename = shoptypename;
	}
}
