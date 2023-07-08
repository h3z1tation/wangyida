package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.DingDanXiangQing;

/**
 * 订单详情接口
 * @author lenovo
 *
 */
public interface DingDanXiangQingDao {

	
	//添加订单详情商品记录
	public boolean AddDingDanXingQingInfo(DingDanXiangQing ddxq);
	
	//根据订单编号查询订单商品记录
	public List<DingDanXiangQing> GetAllInfo(String DingDanNumber);
	
	//根据UserName查询待付款的数量
	public int ByUserNameGetCount(String UserName);
	
	//根据根据MyUser查询所有订单未支付的信息
	public List<DingDanXiangQing> ByMyUserGeAllInfo(String MyUser);
	
	//根据订单编号查询 订单信息
	public List<DingDanXiangQing> ByUserNameAndDingDanNumberGetInfo(String UserName,String DingDanNumber);
	
	//根据MyUser和UserName获得所有信息
	public List<DingDanXiangQing> ByMyUserAndUserNameGetAllInfo(String MyUser,String DingDanNumber);
	
	//根据订单账号修改待付款状态
	public boolean ByDingDanNumberUpdateStateInfo(String DingDanNumber);
	
	//修改订单详情状态id为等待发货
	public boolean ByDingDanNumberUpdateStateidWei1(String DingDanNumber);
	
	//修改订单详情状态id为正在发货
	public boolean ByDingDanNumberUpdateStateidWei2(String DingDanNumber,String UserName);
	
	//修改订单详情状态id为交易完成
	public boolean ByDingDanNumberUpdateStateidWei3(String DingDanNumber);
	
	//根据订单号查询订单表里是否这条内容
	public boolean ByDingDanNumberGetInfo(String DingDanNumber);
	
	//待发货数量
	public int DaiFaHuoCount();
	
	//待付款数量
	public int DaiFuKuanCount();
}
