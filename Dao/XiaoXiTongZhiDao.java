package org.YaoLeGouDB.Dao;

import java.util.List;

import org.YaoLeGouDB.entity.XiaoXiTongZhi;

/**
 * 消息通知接口
 * @author lenovo
 *
 */
public interface XiaoXiTongZhiDao {

	
	//根据UserName查询消息通知的数量
	public int GetXiaoXiTongZhiCount(String UserName);
	
	//根据UserName查询消息通知
	public List<XiaoXiTongZhi> ByUserNameGetAllInfo(String UserName);
	
	//根据订单号和自己的账号，查询订单消息
	public XiaoXiTongZhi ByDingDanNumberAndUserNameGetAllInfo(String DingDanNumber,String UserName);
	
	//根据订单编号和自己的账户修改消息通知的数量为已阅
	public boolean UpdateXiaoXiXiongZhiWei0(String DingDanNumber,String UserName);
	
	//添加消息通知
	public boolean AddXiaoXiTongZhiInfo(XiaoXiTongZhi xxtz);
}
