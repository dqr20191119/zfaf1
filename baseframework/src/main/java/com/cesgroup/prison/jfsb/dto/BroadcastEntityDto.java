
package com.cesgroup.prison.jfsb.dto;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.broadcastPlay.dto.BroadcastRecordDto;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**      
* @projectName：prison   
* @ClassName：BroadcastEntityDto
* @Description：   广播
* @author：Tao.xu   
* @date：2017年12月20日 下午3:28:52   
* @version        
*/
public class BroadcastEntityDto implements Serializable {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -8126579597285425189L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 设备所属监狱编号
	 */
	private String bbdCusNumber;
	/**
	 * 设备索引编号
	 */
	private String bbdIdnty;
	/**
	 * 设备名称
	 */
	private String bbdName;
	/**
	 * 设备品牌
	 */
	private String bbdBrand;
	/**
	 * 设备状态
	 */
	private String bbdSttsIndc;
	/**
	 * 关联的摄像头编号(多个摄像机之间以英文逗号分隔)
	 */
	private String bbdCameraId;
	/**
	 * 关联的摄像头名称(多个摄像机之间以英文逗号分隔)
	 */
	private String bbdCameraName;
	/**
     * 广播设备最近的一次播放记录
	 */
	private BroadcastRecordDto bbdLatestRecord;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBbdCusNumber() {
		return bbdCusNumber;
	}

	public void setBbdCusNumber(String bbdCusNumber) {
		this.bbdCusNumber = bbdCusNumber;
	}

	public String getBbdIdnty() {
		return bbdIdnty;
	}

	public void setBbdIdnty(String bbdIdnty) {
		this.bbdIdnty = bbdIdnty;
	}

	public String getBbdName() {
		return bbdName;
	}

	public void setBbdName(String bbdName) {
		this.bbdName = bbdName;
	}

	public String getBbdBrand() {
		return bbdBrand;
	}

	public void setBbdBrand(String bbdBrand) {
		this.bbdBrand = bbdBrand;
	}

	public String getBbdSttsIndc() {
		return bbdSttsIndc;
	}

	public void setBbdSttsIndc(String bbdSttsIndc) {
		this.bbdSttsIndc = bbdSttsIndc;
	}

	public String getBbdCameraId() {
		return bbdCameraId;
	}

	public void setBbdCameraId(String bbdCameraId) {
		this.bbdCameraId = bbdCameraId;
	}

	public String getBbdCameraName() {
		return bbdCameraName;
	}

	public void setBbdCameraName(String bbdCameraName) {
		this.bbdCameraName = bbdCameraName;
	}

	public BroadcastRecordDto getBbdLatestRecord() {
		return bbdLatestRecord;
	}

	public void setBbdLatestRecord(BroadcastRecordDto bbdLatestRecord) {
		this.bbdLatestRecord = bbdLatestRecord;
	}
}
