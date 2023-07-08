package org.YaoLeGouDB.entity;

public class DingDanBiao {
	
	private int id;
	private String dingdannumber;
	private String zhurenuser;
	private double price;
	private int stateid;
	private String createtime;
	private String username;
	private int zhifuid;
	private int addressid;
	
	private DingDanState dingdanstate;
	private ZhiFuType zhifutype;
	private SHAddress shaddress;
	private DingDanXiangQing ddxq;
	private ShopInfo si;
	private XiaoXiTongZhi xxtz;
	private UserInfo ui;
	
	
	
	public UserInfo getUi() {
		return ui;
	}
	public void setUi(UserInfo ui) {
		this.ui = ui;
	}
	public XiaoXiTongZhi getXxtz() {
		return xxtz;
	}
	public void setXxtz(XiaoXiTongZhi xxtz) {
		this.xxtz = xxtz;
	}
	public ShopInfo getSi() {
		return si;
	}
	public void setSi(ShopInfo si) {
		this.si = si;
	}
	public DingDanXiangQing getDdxq() {
		return ddxq;
	}
	public void setDdxq(DingDanXiangQing ddxq) {
		this.ddxq = ddxq;
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
	public String getZhurenuser() {
		return zhurenuser;
	}
	public void setZhurenuser(String zhurenuser) {
		this.zhurenuser = zhurenuser;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStateid() {
		return stateid;
	}
	public void setStateid(int stateid) {
		this.stateid = stateid;
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
	public int getZhifuid() {
		return zhifuid;
	}
	public void setZhifuid(int zhifuid) {
		this.zhifuid = zhifuid;
	}
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public DingDanState getDingdanstate() {
		return dingdanstate;
	}
	public void setDingdanstate(DingDanState dingdanstate) {
		this.dingdanstate = dingdanstate;
	}
	public ZhiFuType getZhifutype() {
		return zhifutype;
	}
	public void setZhifutype(ZhiFuType zhifutype) {
		this.zhifutype = zhifutype;
	}
	public SHAddress getShaddress() {
		return shaddress;
	}
	public void setShaddress(SHAddress shaddress) {
		this.shaddress = shaddress;
	}
	
}
