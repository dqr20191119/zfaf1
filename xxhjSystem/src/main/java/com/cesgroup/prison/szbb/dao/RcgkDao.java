package com.cesgroup.prison.szbb.dao;

import java.util.List;
import java.util.Map;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.szbb.entity.Rcgk;

/**
 * 数字冰雹数据对接Dao
 * 1.日常管控 
 * @author zhou.jian
 * @date 2019-2-18
 */
public interface RcgkDao extends BaseDao<Rcgk,String>{

	/**
	 * 1.1.今日值班信息接口 - 当日值班长信息
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrzbzList(Map<String,Object> param);
	
	/**
	 * 1.2.今日值班信息接口 - 当日值班主任信息
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrzbzrList(Map<String,Object> param);
	
	/**
	 * 1.3.今日值班信息接口 - 当日指挥中心值班员信息
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrzhzxzbyList(Map<String,Object> param);
	
	/**
	 * 1.4.今日值班信息接口 - 当日值班特警信息 
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrzbtjList(Map<String,Object> param);
	/**
	 * 1.5.今日值班信息接口 - 当日其他值班人员信息
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrqtzbryList(Map<String,Object> param);
	
	
	/**
	 * 1.6.今日值班信息接口 - 当日监区执勤警力信息 
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrjqzqjlList(Map<String,Object> param);
	
	/**
	 * 2.1今日概况信息接口 - 今日防控等级
	 * @return 结果集
	 */
	public String getJrfkdj(Map<String,Object> param);
	/**
	 * 2.2今日概况信息接口 - 今日监狱执勤警力人数
	 * @return 结果集
	 */
	public int getJrjyzqjlrs(Map<String,Object> param);
	
	/**
	 * 2.3今日概况信息接口 - 在册罪犯人数
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrzczfrs(Map<String,Object> param);
	
	/**
	 * 2.4今日概况罪犯时间轴
	 * @return 结果集
	 */
	public List<Map<String,Object>> getJrgkzfsjzList(Map<String,Object> param);
	
	/**
	 * 3.区域管控信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getQygkList(Map<String,Object> param);
	
	/**
	 * 4.1.重控犯信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getZdzfxxList(Map<String,Object> param);
	
	/**
	 * 4.2.重控犯信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getZkfxxList(Map<String,Object> param);
	
	/**
	 * 5.1.大门出入管控信息接口 - 车辆进入数量
	 * @return 结果集
	 */
	public Integer getCljrList(Map<String,Object> param);
	
	/**
	 * 5.2.大门出入管控信息接口 - 车辆驶出数量
	 * @return 结果集
	 */
	public Integer getClscList(Map<String,Object> param);
	
	/**
	 * 5.3.大门出入管控信息接口 - 人员进入数量
	 * @return 结果集
	 */
	public Integer getRyjrList(Map<String,Object> param);
	
	/**
	 * 5.4.大门出入管控信息接口 - 人员离开数量
	 * @return 结果集
	 */
	public Integer getRylkList(Map<String,Object> param);
	
	/**
	 * 5.5.大门出入管控信息接口 - 设备检测集合
	 * @return 结果集
	 */
	public List<Map<String,Object>> getSbjcList(Map<String,Object> param);
	
	/**
	 * 6.人脸识别信息接口
	 * @return 结果集
	 */
	public Map<String,Object> getRlsb(Map<String,Object> param);
	
	/**
	 * 7.周界安防信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getZjafList(Map<String,Object> param);
	
	/**
	 * 8.手机管控信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getSjgkList(Map<String,Object> param);
	
	/**
	 * 9.1.狱外押解任务信息接口 - 解押任务罪犯姓名
	 * @return 结果集
	 */
	public String getZfxm(Map<String,Object> param);
	
	/**
	 * 9.2.狱外押解任务信息接口 - 解押任务目的地
	 * @return 结果集
	 */
	public String getMdd(Map<String,Object> param);
	
	/**
	 * 9.3.狱外押解任务信息接口 - 民警信息
	 * @return 结果集
	 */
	public List<Map<String,Object>> getMjxxList(Map<String,Object> param);
	
	/**
	 * 9.4.狱外押解任务信息接口 - 规划路线
	 * @return 结果集
	 */
	public List<Map<String,Object>> getGhlxList(Map<String,Object> param);
	
	/**
	 * 10.狱外押解车辆实时位置信息接口
	 * @return 结果集
	 */
	public List<Map<String,Object>> getYwyjclwzList(Map<String,Object> param);
	
}
