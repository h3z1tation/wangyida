package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.DingDanBiao;

/**
 * 订单表接口
 * @author lenovo
 *
 */
public interface DingDanBiaoDao {

	
	//增加订单
	public boolean AddDingDanInfo(DingDanBiao ddb);
	
	//查询已付款的订单数量
	public int ByMyUserGetCount(String MyUser);
	
	//根据自己的账号id查询单个的订单信息
	public List<DingDanBiao> ByZhuRenUserGetInfo(String ZhuRenUser);
	
	//根据自己的账号id查询 所有 待收货或者 发货状态的商品信息
	public List<DingDanBiao> ByZhuRenUserGetAllFaHuoStateInfo(String ZhuRenUser);
	
	//获得订单数量
	public int Count();
	
	
	
	
	//根据UserName查询订货通知的数量
	public int ByUserNameGetDingHuoTongZhiCount(String UserName);
	
	//根据自己的UserName查询订货的信息
	public List<DingDanBiao> ByUserNameGetInfo(String UserName);
	
	//根据自己的UserName查询 所有 订货信息
	public List<DingDanBiao> ByUserGetAllStateInfo(String DingDanNumber,String UserName);
	
	//确认发货
	public boolean ByDingDanNumberUpdateFaHuoInfo(String DingDanNumber);
	
	//查询已完成的订货订单信息数量
	public int ByUserNameGetYiWanChengInfoCount(String UserName);
	
	//确认收货
	public boolean ByDingDanNumberQueRenShouHuo(String DingDanNumber);
	
	//根据自己的UserName查询已完成的订单信息
	public List<DingDanBiao> ByUserNameGetYiWanChengInfo(String UserName);
	
	//根据订单编号查询 所有已完成的订单信息
	public List<DingDanBiao> ByZhuRenUserGetAllYiWanChengStateInfo(String DingDanNumber);

	//增加金额
	public boolean ByUserNameAndMoneyAddMoney(String Money,String UserName);
	
	//获得所有订单表里面的数据
	public List<DingDanBiao> GetAllInfo();
	
	//修改收货地址
	public boolean UpdateAddressInfo(int address,String DingDanNuber);
	
	//获得 已完成订单数量
	public int GetAllCount();
}
