package com.cesgroup.prison.jfsb.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description: 门禁基本控制信息表实体类
 * @author lincoln
 *
 */
@Entity
@Table(name = "DVC_DOOR_CONTROL_BASE_DTLS")
public class DoorEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String dcbCusNumber;
	/**
	 * 门禁编号
	 */
	private String dcbIdnty;
	/**
	 * 门禁控制器编号
	 */
	private String dcbCtrlIdnty;
	/**
	 * 通道编号
	 */
	private String dcbChannelIdnty;
	/**
	 * 门禁名称
	 */
	private String dcbName;
	/**
	 * 门禁名称前缀
	 */
	private String dcbPreName;
	/**
	 * 门禁类型(0-门禁控制器;1-安检门;2-大旗监舍平移门)
	 */
	private String dcbTypeIndc;
	/**
	 * 门禁品牌(1-振讯;2-精工;3-微耕;4-纽贝尔)
	 */
	private String dcbBrandIndc;
	/**
	 * 地址
	 */
	private String dcbAddrs;
	/**
	 * 图层（暂时保留）
	 */
	private String dcbNodeAddrs;
	/**
	 * 序列号
	 */
	private String dcbSnId;
	/**
	 * 频道
	 */
	private String dcbChnnlAddrs;
	/**
	 * 区域编号
	 */
	private String dcbAreaId;
	/**
	 * 区域
	 */
	private String dcbArea;
	/**
	 * 部门编号
	 */
	private String dcbDprtmntId;
	/**
	 * 部门名称
	 */
	private String dcbDprtmnt;
	/**
	 * 设备状态(0:正常;1:故障;2:常闭;3:常开)
	 */
	private String dcbSttsIndc;
	/**
	 * 操作状态(1 新增 2 修改 3 删除)
	 */
	private String dcbActionIndc;
	/**
	 * 备注
	 */
	private String dcbRemark;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dcbCrteTime;
	/**
	 * 创建人
	 */
	private String dcbCrteUserId;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dcbUpdtTime;
	/**
	 * 更新人
	 */
	private String dcbUpdtUserId;
	/**
	 * 端口号
	 */
	private String dcbPort;
	/**
	 * 是否控制(0:否; 1:是)
	 */
	private String dcbWhetherCtrl;
	/**
	 * 关联的摄像头编号(多个摄像机之间以英文逗号分隔)
	 */
	private String dcbCameraId;
	/**
	 * 关联的摄像头名称(多个摄像机之间以英文逗号分隔)
	 */
	private String dcbCameraName;
	/**
	 * 房间编号
	 */
	private String dcbRoomId;
	/**
	 * 房间名称
	 */
	private String dcbRoom;
	/**
	 * 是否是进出监狱的门，新增字段，用来判断在监民警
	 */
	private String dcbIsAb;

	public String getDcbIsAb() {
		return dcbIsAb;
	}

	public void setDcbIsAb(String dcbIsAb) {
		this.dcbIsAb = dcbIsAb;
	}

	public String getDcbCusNumber() {
		return dcbCusNumber;
	}

	public void setDcbCusNumber(String dcbCusNumber) {
		this.dcbCusNumber = dcbCusNumber;
	}

	public String getDcbIdnty() {
		return dcbIdnty;
	}

	public void setDcbIdnty(String dcbIdnty) {
		this.dcbIdnty = dcbIdnty;
	}

	public String getDcbCtrlIdnty() {
		return dcbCtrlIdnty;
	}

	public void setDcbCtrlIdnty(String dcbCtrlIdnty) {
		this.dcbCtrlIdnty = dcbCtrlIdnty;
	}

	public String getDcbChannelIdnty() {
		return dcbChannelIdnty;
	}

	public void setDcbChannelIdnty(String dcbChannelIdnty) {
		this.dcbChannelIdnty = dcbChannelIdnty;
	}

	public String getDcbName() {
		return dcbName;
	}

	public void setDcbName(String dcbName) {
		this.dcbName = dcbName;
	}

	public String getDcbPreName() {
		return dcbPreName;
	}

	public void setDcbPreName(String dcbPreName) {
		this.dcbPreName = dcbPreName;
	}

	public String getDcbTypeIndc() {
		return dcbTypeIndc;
	}

	public void setDcbTypeIndc(String dcbTypeIndc) {
		this.dcbTypeIndc = dcbTypeIndc;
	}

	public String getDcbBrandIndc() {
		return dcbBrandIndc;
	}

	public void setDcbBrandIndc(String dcbBrandIndc) {
		this.dcbBrandIndc = dcbBrandIndc;
	}

	public String getDcbAddrs() {
		return dcbAddrs;
	}

	public void setDcbAddrs(String dcbAddrs) {
		this.dcbAddrs = dcbAddrs;
	}

	public String getDcbNodeAddrs() {
		return dcbNodeAddrs;
	}

	public void setDcbNodeAddrs(String dcbNodeAddrs) {
		this.dcbNodeAddrs = dcbNodeAddrs;
	}

	public String getDcbSnId() {
		return dcbSnId;
	}

	public void setDcbSnId(String dcbSnId) {
		this.dcbSnId = dcbSnId;
	}

	public String getDcbChnnlAddrs() {
		return dcbChnnlAddrs;
	}

	public void setDcbChnnlAddrs(String dcbChnnlAddrs) {
		this.dcbChnnlAddrs = dcbChnnlAddrs;
	}

	public String getDcbAreaId() {
		return dcbAreaId;
	}

	public void setDcbAreaId(String dcbAreaId) {
		this.dcbAreaId = dcbAreaId;
	}

	public String getDcbArea() {
		return dcbArea;
	}

	public void setDcbArea(String dcbArea) {
		this.dcbArea = dcbArea;
	}

	public String getDcbDprtmnt() {
		return dcbDprtmnt;
	}

	public void setDcbDprtmnt(String dcbDprtmnt) {
		this.dcbDprtmnt = dcbDprtmnt;
	}

	public String getDcbSttsIndc() {
		return dcbSttsIndc;
	}

	public void setDcbSttsIndc(String dcbSttsIndc) {
		this.dcbSttsIndc = dcbSttsIndc;
	}

	public String getDcbActionIndc() {
		return dcbActionIndc;
	}

	public void setDcbActionIndc(String dcbActionIndc) {
		this.dcbActionIndc = dcbActionIndc;
	}

	public String getDcbRemark() {
		return dcbRemark;
	}

	public void setDcbRemark(String dcbRemark) {
		this.dcbRemark = dcbRemark;
	}

	public Date getDcbCrteTime() {
		return dcbCrteTime;
	}

	public void setDcbCrteTime(Date dcbCrteTime) {
		this.dcbCrteTime = dcbCrteTime;
	}

	public String getDcbCrteUserId() {
		return dcbCrteUserId;
	}

	public void setDcbCrteUserId(String dcbCrteUserId) {
		this.dcbCrteUserId = dcbCrteUserId;
	}

	public Date getDcbUpdtTime() {
		return dcbUpdtTime;
	}

	public void setDcbUpdtTime(Date dcbUpdtTime) {
		this.dcbUpdtTime = dcbUpdtTime;
	}

	public String getDcbUpdtUserId() {
		return dcbUpdtUserId;
	}

	public void setDcbUpdtUserId(String dcbUpdtUserId) {
		this.dcbUpdtUserId = dcbUpdtUserId;
	}

	public String getDcbPort() {
		return dcbPort;
	}

	public void setDcbPort(String dcbPort) {
		this.dcbPort = dcbPort;
	}

	public String getDcbWhetherCtrl() {
		return dcbWhetherCtrl;
	}

	public void setDcbWhetherCtrl(String dcbWhetherCtrl) {
		this.dcbWhetherCtrl = dcbWhetherCtrl;
	}

	public String getDcbDprtmntId() {
		return dcbDprtmntId;
	}

	public void setDcbDprtmntId(String dcbDprtmntId) {
		this.dcbDprtmntId = dcbDprtmntId;
	}

	public String getDcbCameraId() {
		return dcbCameraId;
	}

	public void setDcbCameraId(String dcbCameraId) {
		this.dcbCameraId = dcbCameraId;
	}

    public String getDcbCameraName() {
        return dcbCameraName;
    }

    public void setDcbCameraName(String dcbCameraName) {
        this.dcbCameraName = dcbCameraName;
    }

	public String getDcbRoomId() {
		return dcbRoomId;
	}

	public void setDcbRoomId(String dcbRoomId) {
		this.dcbRoomId = dcbRoomId;
	}

	public String getDcbRoom() {
		return dcbRoom;
	}

	public void setDcbRoom(String dcbRoom) {
		this.dcbRoom = dcbRoom;
	}
}
