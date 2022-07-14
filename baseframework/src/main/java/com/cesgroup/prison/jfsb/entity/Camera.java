package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
*    
* @projectName：prison   
* @ClassName：Camera   
* @Description：摄像机  
* @author：zhengke   
* @date：2017-12-01 09:48   
* @version        
*/
@Entity
@Table(name="DVC_CAMERA_BASE_DTLS")
public class Camera extends StringIDEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	* @Fields cbdCusNumber : 监所编号
	*/
	@NotNull
	private String cbdCusNumber;

	/**
	* @Fields cbdName : 摄像机名称
	*/
	@NotNull
	private String cbdName;

	/**
	* @Fields cbdTypeIndc : 摄像机类型1、球型，2、枪机，3、半球
	*/
	@NotNull
	private String cbdTypeIndc;

	/**
	* @Fields cbdBrandName : 摄像机品牌
	*/
	private String cbdBrandName;

	/**
	* @Fields cbdDvrIdnty : 摄像机所在的DVR
	*/
	private String cbdDvrIdnty;

	/**
	* @Fields cbdDvrChnnlIdnty : 摄像机所在DVR的通道号
	*/
	private String cbdDvrChnnlIdnty;

	/**
	* @Fields cbdMtrxIdnty : TODO
	*/
	private String cbdMtrxIdnty;

	/**
	* @Fields cbdMtrxChnnlIdnty : TODO
	*/
	private String cbdMtrxChnnlIdnty;

	/**
	* @Fields cbdStreamMediaIdnty : 流媒体服务标识
	*/
	private String cbdStreamMediaIdnty;

	/**
	* @Fields cbdPreNeme : TODO
	*/
	private String cbdPreNeme;

	/**
	* @Fields cbdAddrsCode : TODO
	*/
	private String cbdAddrsCode;

	/**
	* @Fields cbdIpAddrs : TODO
	*/
	private String cbdIpAddrs;

	/**
	* @Fields cbdDprtmnt : 所属部门
	*/
	private String cbdDprtmnt;

	/**
	* @Fields cbdAreaId : 区域ID
	*/
	private String cbdAreaId;

	/**
	* @Fields cbdArea : 所属区域
	*/
	private String cbdArea;

	private String cbdDprtmntSrno;

	/**
	* @Fields cbdSttsIndc : 摄像机状态（正常、视频丢失、设备不在线）
	*/
	@NotNull
	private String cbdSttsIndc;

	/**
	* @Fields cbdDvcAddrs : 摄像机设备地址
	*/
	private String cbdDvcAddrs;

	/**
	* @Fields cbdAnalyseChnnlIdnty : TODO
	*/
	private String cbdAnalyseChnnlIdnty;

	/**
	* @Fields cbdActionIndc : 操作状态:1 新增 2修改
	*/
	@NotNull
	private String cbdActionIndc;

	/**
	* @Fields cbdCrteTime : 创建时间
	*/
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cbdCrteTime;

	/**
	* @Fields cbdCrteUserId : 创建人
	*/
	@NotNull
	private String cbdCrteUserId;

	/**
	* @Fields cbdUpdtTime : 更新时间
	*/
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cbdUpdtTime;

	/**
	* @Fields cbdUpdtUserId : 更新人
	*/
	@NotNull
	private String cbdUpdtUserId;

	/**
	* @Fields cbdStreamType : 码流类型：0 主码流、1 辅码流
	*/
	private String cbdStreamType;

	/**
	* @Fields cbdUseLimit : 使用限制0、无限制，6500、省局
	*/
	@NotNull
	private String cbdUseLimit;

	/**
	* @Fields cbdPlatformIdnty : 平台索引编号
	*/
	private String cbdPlatformIdnty;

	/**
	* @Fields cbdPlatform : 所在平台
	*/
	private String cbdPlatform;

	/**
	* @Fields cbdVideoPlayIndc : 实时播放方式0直连 1 平台2 设备
	*/
	private String cbdVideoPlayIndc;

	/**
	* @Fields cbdVideoPlaybackIndc : 视频回放方式1 平台2 设备
	*/
	private String cbdVideoPlaybackIndc;

	/**
	* @Fields cbdOrder : TODO
	*/
	private Integer cbdOrder;

	//直连add by zk start 
	//摄像机端口
	private String cbdPort;
	//摄像机用户名
	private String cbdUserName;
	//摄像机密码
	private String cbdUserPassword;
	//摄像机通道号
	private String cbdChnnlIdnty;
	//直连add by zk end 
	
	private String cbdOutSide;
	//对讲编号
	private String cbdTalkbackId;
	//对讲名称
	private String cbdTalkbackName;

	//地址码,前期存储的是监舍号
    private String cbdAddrs;

    //设置时间是否有效
    private String cbdTimeValid;

    //摄像头报警有效的开始时间
    private String cbdValidStarttime;


    //摄像头报警有效的结束时间
    private String cbdValidEndtime;

	public String getCbdName() {
		return cbdName;
	}

	public void setCbdName(String cbdName) {
		this.cbdName = cbdName;
	}

	public String getCbdBrandName() {
		return cbdBrandName;
	}

	public void setCbdBrandName(String cbdBrandName) {
		this.cbdBrandName = cbdBrandName;
	}

	public String getCbdDvrIdnty() {
		return cbdDvrIdnty;
	}

	public void setCbdDvrIdnty(String cbdDvrIdnty) {
		this.cbdDvrIdnty = cbdDvrIdnty;
	}

	public String getCbdDvrChnnlIdnty() {
		return cbdDvrChnnlIdnty;
	}

	public void setCbdDvrChnnlIdnty(String cbdDvrChnnlIdnty) {
		this.cbdDvrChnnlIdnty = cbdDvrChnnlIdnty;
	}

	public String getCbdMtrxIdnty() {
		return cbdMtrxIdnty;
	}

	public void setCbdMtrxIdnty(String cbdMtrxIdnty) {
		this.cbdMtrxIdnty = cbdMtrxIdnty;
	}

	public String getCbdMtrxChnnlIdnty() {
		return cbdMtrxChnnlIdnty;
	}

	public void setCbdMtrxChnnlIdnty(String cbdMtrxChnnlIdnty) {
		this.cbdMtrxChnnlIdnty = cbdMtrxChnnlIdnty;
	}

	public String getCbdStreamMediaIdnty() {
		return cbdStreamMediaIdnty;
	}

	public void setCbdStreamMediaIdnty(String cbdStreamMediaIdnty) {
		this.cbdStreamMediaIdnty = cbdStreamMediaIdnty;
	}

	public String getCbdPreNeme() {
		return cbdPreNeme;
	}

	public void setCbdPreNeme(String cbdPreNeme) {
		this.cbdPreNeme = cbdPreNeme;
	}

	public String getCbdAddrsCode() {
		return cbdAddrsCode;
	}

	public void setCbdAddrsCode(String cbdAddrsCode) {
		this.cbdAddrsCode = cbdAddrsCode;
	}

	public String getCbdIpAddrs() {
		return cbdIpAddrs;
	}

	public void setCbdIpAddrs(String cbdIpAddrs) {
		this.cbdIpAddrs = cbdIpAddrs;
	}

	public String getCbdDprtmnt() {
		return cbdDprtmnt;
	}

	public void setCbdDprtmnt(String cbdDprtmnt) {
		this.cbdDprtmnt = cbdDprtmnt;
	}

	public String getCbdAreaId() {
		return cbdAreaId;
	}

	public void setCbdAreaId(String cbdAreaId) {
		this.cbdAreaId = cbdAreaId;
	}

	public String getCbdArea() {
		return cbdArea;
	}

	public void setCbdArea(String cbdArea) {
		this.cbdArea = cbdArea;
	}

	public String getCbdDvcAddrs() {
		return cbdDvcAddrs;
	}

	public void setCbdDvcAddrs(String cbdDvcAddrs) {
		this.cbdDvcAddrs = cbdDvcAddrs;
	}

	public String getCbdAnalyseChnnlIdnty() {
		return cbdAnalyseChnnlIdnty;
	}

	public void setCbdAnalyseChnnlIdnty(String cbdAnalyseChnnlIdnty) {
		this.cbdAnalyseChnnlIdnty = cbdAnalyseChnnlIdnty;
	}

	public Date getCbdCrteTime() {
		return cbdCrteTime;
	}

	public void setCbdCrteTime(Date cbdCrteTime) {
		this.cbdCrteTime = cbdCrteTime;
	}

	public String getCbdCrteUserId() {
		return cbdCrteUserId;
	}

	public void setCbdCrteUserId(String cbdCrteUserId) {
		this.cbdCrteUserId = cbdCrteUserId;
	}

	public Date getCbdUpdtTime() {
		return cbdUpdtTime;
	}

	public void setCbdUpdtTime(Date cbdUpdtTime) {
		this.cbdUpdtTime = cbdUpdtTime;
	}

	public String getCbdUpdtUserId() {
		return cbdUpdtUserId;
	}

	public void setCbdUpdtUserId(String cbdUpdtUserId) {
		this.cbdUpdtUserId = cbdUpdtUserId;
	}

	public String getCbdPlatformIdnty() {
		return cbdPlatformIdnty;
	}

	public void setCbdPlatformIdnty(String cbdPlatformIdnty) {
		this.cbdPlatformIdnty = cbdPlatformIdnty;
	}

	public String getCbdPlatform() {
		return cbdPlatform;
	}

	public void setCbdPlatform(String cbdPlatform) {
		this.cbdPlatform = cbdPlatform;
	}

	public Integer getCbdOrder() {
		return cbdOrder;
	}

	public String getCbdCusNumber() {
		return cbdCusNumber;
	}

	public void setCbdCusNumber(String cbdCusNumber) {
		this.cbdCusNumber = cbdCusNumber;
	}

	public String getCbdTypeIndc() {
		return cbdTypeIndc;
	}

	public void setCbdTypeIndc(String cbdTypeIndc) {
		this.cbdTypeIndc = cbdTypeIndc;
	}

	public String getCbdDprtmntSrno() {
		return cbdDprtmntSrno;
	}

	public void setCbdDprtmntSrno(String cbdDprtmntSrno) {
		this.cbdDprtmntSrno = cbdDprtmntSrno;
	}

	public String getCbdSttsIndc() {
		return cbdSttsIndc;
	}

	public void setCbdSttsIndc(String cbdSttsIndc) {
		this.cbdSttsIndc = cbdSttsIndc;
	}

	public String getCbdActionIndc() {
		return cbdActionIndc;
	}

	public void setCbdActionIndc(String cbdActionIndc) {
		this.cbdActionIndc = cbdActionIndc;
	}

	public String getCbdStreamType() {
		return cbdStreamType;
	}

	public void setCbdStreamType(String cbdStreamType) {
		this.cbdStreamType = cbdStreamType;
	}

	public String getCbdUseLimit() {
		return cbdUseLimit;
	}

	public void setCbdUseLimit(String cbdUseLimit) {
		this.cbdUseLimit = cbdUseLimit;
	}

	public String getCbdVideoPlayIndc() {
		return cbdVideoPlayIndc;
	}

	public void setCbdVideoPlayIndc(String cbdVideoPlayIndc) {
		this.cbdVideoPlayIndc = cbdVideoPlayIndc;
	}

	public String getCbdVideoPlaybackIndc() {
		return cbdVideoPlaybackIndc;
	}

	public void setCbdVideoPlaybackIndc(String cbdVideoPlaybackIndc) {
		this.cbdVideoPlaybackIndc = cbdVideoPlaybackIndc;
	}

	public void setCbdOrder(Integer cbdOrder) {
		this.cbdOrder = cbdOrder;
	}

	public String getCbdPort() {
		return cbdPort;
	}

	public void setCbdPort(String cbdPort) {
		this.cbdPort = cbdPort;
	}

	public String getCbdUserName() {
		return cbdUserName;
	}

	public void setCbdUserName(String cbdUserName) {
		this.cbdUserName = cbdUserName;
	}

	public String getCbdUserPassword() {
		return cbdUserPassword;
	}

	public void setCbdUserPassword(String cbdUserPassword) {
		this.cbdUserPassword = cbdUserPassword;
	}

	public String getCbdChnnlIdnty() {
		return cbdChnnlIdnty;
	}

	public void setCbdChnnlIdnty(String cbdChnnlIdnty) {
		this.cbdChnnlIdnty = cbdChnnlIdnty;
	}

	public String getCbdOutSide() {
		return cbdOutSide;
	}

	public void setCbdOutSide(String cbdOutSide) {
		this.cbdOutSide = cbdOutSide;
	}

	public String getCbdTalkbackId() {
		return cbdTalkbackId;
	}

	public void setCbdTalkbackId(String cbdTalkbackId) {
		this.cbdTalkbackId = cbdTalkbackId;
	}

	public String getCbdTalkbackName() {
		return cbdTalkbackName;
	}

	public void setCbdTalkbackName(String cbdTalkbackName) {
		this.cbdTalkbackName = cbdTalkbackName;
	}

    public String getCbdAddrs() {
        return cbdAddrs;
    }

    public void setCbdAddrs(String cbdAddrs) {
        this.cbdAddrs = cbdAddrs;
    }

    public String getCbdTimeValid() {
        return cbdTimeValid;
    }

    public void setCbdTimeValid(String cbdTimeValid) {
        this.cbdTimeValid = cbdTimeValid;
    }

    public String getCbdValidStarttime() {
        return cbdValidStarttime;
    }

    public void setCbdValidStarttime(String cbdValidStarttime) {
        this.cbdValidStarttime = cbdValidStarttime;
    }

    public String getCbdValidEndtime() {
        return cbdValidEndtime;
    }

    public void setCbdValidEndtime(String cbdValidEndtime) {
        this.cbdValidEndtime = cbdValidEndtime;
    }
}
