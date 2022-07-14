package com.cesgroup.prison.linkage.service;

import java.util.List;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.linkage.dto.DoorMessageDto;

public interface DoorCtrlService {

	/**
	* @methodName: openDoor
	* @Description: 开关门
	* @param doorIds 门禁id集合
	* @param action 动作  1 开门 2禁止开门 3恢复开门
	* @param time 常闭操作有效时间 1-254（分钟）
	* @return String
	* @throws  
	*/
	public AjaxMessage openDoor(List<String> doorIds, String action, String time);

	/**
	 * 开关门Demo
	 * @param pcIp
	 * @param doorMessageDto
	 * @return
	 */
	public AjaxResult openDoorDemo(String pcIp, DoorMessageDto doorMessageDto);
}
