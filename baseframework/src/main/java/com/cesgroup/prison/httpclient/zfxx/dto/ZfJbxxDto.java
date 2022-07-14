package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 罪犯基本信息DTO
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ZfJbxxDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 罪犯编号
	 */
	private String CZfbh;
	/**
	 * 罪犯标识
	 */
	private String CId;
	/**
	 * 罪犯姓名
	 */
	private String CXm;
	/**
	 * 真实用名
	 */
	private String CZsym;
	/**
	 * 性别
	 */
	private String CXb;
	/**
	 * 出生日期
	 */
	private String DCsrq;
	/**
	 * 民族
	 */
	private String CMz;
	/**
	 * 捕前政治面貌
	 */
	private String CBqzzmm;
	/**
	 * 捕前文化程度
	 */
	private String CBqwhcd;
	/**
	 * 捕前婚姻状况
	 */
	private String CBqhyzk;
	/**
	 * 捕前所学专业
	 */
	private String CSxzy;
	/**
	 * 捕前职业
	 */
	private String CBqzy;
	/**
	 * 捕前职业分类
	 */
	private String CBqzylb;
	/**
	 * 捕前职级
	 */
	private String CBqzj;
	
	
	/**
	 * 没收财产履行情况
	 */
	private String CMsccLxqk;
	/**
	 * 罚金累计缴纳金额
	 */
	private String NFjjnZe;
	/**
	 * 民事赔偿累计缴纳金额
	 */
	private String NMspcjnZe;
	/**
	 * 追缴累计缴纳金额
	 */
	private String NZjjnZe;
	/**
	 * 责令退赔累计缴纳金额
	 */
	private String NZltpjnZe;
	/**
	 * 档案号
	 */
	private String CZydah;
	/**
	 * 副档号
	 */
	private String CFdah;
	/**
	 * 所在监狱
	 */
	private String CSzjy;
	/**
	 * 所在监区
	 */
	private String CSzjq;
	/**
	 * 分押类别
	 */
	private String CFylb;
	/**
	 * 工种
	 */
	private String CGz;
	/**
	 * 目前文化程度
	 */
	private String CMqwhcd;
	/**
	 * 互监组号
	 */
	private String CHjzh;
	/**
	 * 监舍号
	 */
	private String CJsh;
	/**
	 * 床位号
	 */
	private String CCwh;
	/**
	 * 监管干警名称
	 */
	private String CJggjMc;
	/**
	 * 分管等级
	 */
	private String CFgdj;
	/**
	 * 送押机关
	 */
	private String CSyjg;
	/**
	 * 入监备注
	 */
	private String CRjbz;
	/**
	 * 入监日期
	 */
	private String DRjrq;
	/**
	 * 正面照缩略图
	 */
	private String CZmzSlt;
	
	
	/**
	 * 籍贯
	 */
	private String CJg;
	/**
	 * 家庭住址
	 */
	private String CJtzz;
	/**
	 * 户籍地址
	 */
	private String CHjdz;
	/**
	 * 原判刑种
	 */
	private String CYpXz;
	/**
	 * 原判刑期
	 */
	private String CYpXq;
	
	
	/**
	 * 原判刑期起日
	 */
	private String DYpXqqr;
	/**
	 * 原判刑期止日
	 */
	private String DYpXqzr;
	/**
	 * 原剥政类别
	 */
	private String CYpBzlb;
	/**
	 * 现刑种
	 */
	private String CXxqZxxz;
	/**
	 * 现刑期
	 */
	private String CXxq;
	
	/**
	 * 现刑期起日
	 */
	private String DXxqQr;
	/**
	 * 现刑期止日
	 */
	private String DXxqZr;
	
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	private String DUrlTime;
	/**
	 * 罪犯照片信息DtoList
	 */
	private List<ZfTafDto> zfTafDtoList;
	/**
	 * 罪犯照片信息DtoList
	 */
	private List<ZfPhotoDto> zfPhotoDtoList;
	/**
	 * 罪犯离监信息Dtolist
	 */
	private List<ZfLjDto> zfLjDtoList;
	/**
	 * 罪犯收押信息Dtolist
	 */
	private List<ZfSyDto> zfSyDtoList;
	
	public String getCZfbh() {
		return CZfbh;
	}
	public void setCZfbh(String cZfbh) {
		CZfbh = cZfbh;
	}
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getCXm() {
		return CXm;
	}
	public void setCXm(String cXm) {
		CXm = cXm;
	}
	public String getCZsym() {
		return CZsym;
	}
	public void setCZsym(String cZsym) {
		CZsym = cZsym;
	}
	public String getCXb() {
		return CXb;
	}
	public void setCXb(String cXb) {
		CXb = cXb;
	}
	public String getDCsrq() {
		return DCsrq;
	}
	public void setDCsrq(String dCsrq) {
		DCsrq = dCsrq;
	}
	public String getCMz() {
		return CMz;
	}
	public void setCMz(String cMz) {
		CMz = cMz;
	}
	public String getCBqzzmm() {
		return CBqzzmm;
	}
	public void setCBqzzmm(String cBqzzmm) {
		CBqzzmm = cBqzzmm;
	}
	public String getCBqwhcd() {
		return CBqwhcd;
	}
	public void setCBqwhcd(String cBqwhcd) {
		CBqwhcd = cBqwhcd;
	}
	public String getCBqhyzk() {
		return CBqhyzk;
	}
	public void setCBqhyzk(String cBqhyzk) {
		CBqhyzk = cBqhyzk;
	}
	public String getCSxzy() {
		return CSxzy;
	}
	public void setCSxzy(String cSxzy) {
		CSxzy = cSxzy;
	}
	public String getCBqzy() {
		return CBqzy;
	}
	public void setCBqzy(String cBqzy) {
		CBqzy = cBqzy;
	}
	public String getCBqzylb() {
		return CBqzylb;
	}
	public void setCBqzylb(String cBqzylb) {
		CBqzylb = cBqzylb;
	}
	public String getCBqzj() {
		return CBqzj;
	}
	public void setCBqzj(String cBqzj) {
		CBqzj = cBqzj;
	}
	public String getCMsccLxqk() {
		return CMsccLxqk;
	}
	public void setCMsccLxqk(String cMsccLxqk) {
		CMsccLxqk = cMsccLxqk;
	}
	public String getNFjjnZe() {
		return NFjjnZe;
	}
	public void setNFjjnZe(String nFjjnZe) {
		NFjjnZe = nFjjnZe;
	}
	public String getNMspcjnZe() {
		return NMspcjnZe;
	}
	public void setNMspcjnZe(String nMspcjnZe) {
		NMspcjnZe = nMspcjnZe;
	}
	public String getNZjjnZe() {
		return NZjjnZe;
	}
	public void setNZjjnZe(String nZjjnZe) {
		NZjjnZe = nZjjnZe;
	}
	public String getNZltpjnZe() {
		return NZltpjnZe;
	}
	public void setNZltpjnZe(String nZltpjnZe) {
		NZltpjnZe = nZltpjnZe;
	}
	public String getCZydah() {
		return CZydah;
	}
	public void setCZydah(String cZydah) {
		CZydah = cZydah;
	}
	public String getCFdah() {
		return CFdah;
	}
	public void setCFdah(String cFdah) {
		CFdah = cFdah;
	}
	public String getCSzjy() {
		return CSzjy;
	}
	public void setCSzjy(String cSzjy) {
		CSzjy = cSzjy;
	}
	public String getCSzjq() {
		return CSzjq;
	}
	public void setCSzjq(String cSzjq) {
		CSzjq = cSzjq;
	}
	public String getCFylb() {
		return CFylb;
	}
	public void setCFylb(String cFylb) {
		CFylb = cFylb;
	}
	public String getCGz() {
		return CGz;
	}
	public void setCGz(String cGz) {
		CGz = cGz;
	}
	public String getCMqwhcd() {
		return CMqwhcd;
	}
	public void setCMqwhcd(String cMqwhcd) {
		CMqwhcd = cMqwhcd;
	}
	public String getCHjzh() {
		return CHjzh;
	}
	public void setCHjzh(String cHjzh) {
		CHjzh = cHjzh;
	}
	public String getCJsh() {
		return CJsh;
	}
	public void setCJsh(String cJsh) {
		CJsh = cJsh;
	}
	public String getCCwh() {
		return CCwh;
	}
	public void setCCwh(String cCwh) {
		CCwh = cCwh;
	}
	public String getCJggjMc() {
		return CJggjMc;
	}
	public void setCJggjMc(String cJggjMc) {
		CJggjMc = cJggjMc;
	}
	public String getCFgdj() {
		return CFgdj;
	}
	public void setCFgdj(String cFgdj) {
		CFgdj = cFgdj;
	}
	public String getCSyjg() {
		return CSyjg;
	}
	public void setCSyjg(String cSyjg) {
		CSyjg = cSyjg;
	}
	public String getCRjbz() {
		return CRjbz;
	}
	public void setCRjbz(String cRjbz) {
		CRjbz = cRjbz;
	}
	public String getDRjrq() {
		return DRjrq;
	}
	public void setDRjrq(String dRjrq) {
		DRjrq = dRjrq;
	}
	public String getCZmzSlt() {
		return CZmzSlt;
	}
	public void setCZmzSlt(String cZmzSlt) {
		CZmzSlt = cZmzSlt;
	}
	
		
	public String getCJg() {
		return CJg;
	}
	public void setCJg(String cJg) {
		CJg = cJg;
	}
	public String getCJtzz() {
		return CJtzz;
	}
	public void setCJtzz(String cJtzz) {
		CJtzz = cJtzz;
	}
	public String getCHjdz() {
		return CHjdz;
	}
	public void setCHjdz(String cHjdz) {
		CHjdz = cHjdz;
	}
	public String getCYpXz() {
		return CYpXz;
	}
	public void setCYpXz(String cYpXz) {
		CYpXz = cYpXz;
	}
	public String getCYpXq() {
		return CYpXq;
	}
	public void setCYpXq(String cYpXq) {
		CYpXq = cYpXq;
	}
	public String getDYpXqqr() {
		return DYpXqqr;
	}
	public void setDYpXqqr(String dYpXqqr) {
		DYpXqqr = dYpXqqr;
	}
	public String getDYpXqzr() {
		return DYpXqzr;
	}
	public void setDYpXqzr(String dYpXqzr) {
		DYpXqzr = dYpXqzr;
	}
	public String getCYpBzlb() {
		return CYpBzlb;
	}
	public void setCYpBzlb(String cYpBzlb) {
		CYpBzlb = cYpBzlb;
	}
	public String getCXxqZxxz() {
		return CXxqZxxz;
	}
	public void setCXxqZxxz(String cXxqZxxz) {
		CXxqZxxz = cXxqZxxz;
	}
	public String getCXxq() {
		return CXxq;
	}
	public void setCXxq(String cXxq) {
		CXxq = cXxq;
	}
	public String getDXxqQr() {
		return DXxqQr;
	}
	public void setDXxqQr(String dXxqQr) {
		DXxqQr = dXxqQr;
	}
	public String getDXxqZr() {
		return DXxqZr;
	}
	public void setDXxqZr(String dXxqZr) {
		DXxqZr = dXxqZr;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
	public List<ZfTafDto> getZfTafDtoList() {
		return zfTafDtoList;
	}
	public void setZfTafDtoList(List<ZfTafDto> zfTafDtoList) {
		this.zfTafDtoList = zfTafDtoList;
	}
	public List<ZfPhotoDto> getZfPhotoDtoList() {
		return zfPhotoDtoList;
	}
	public void setZfPhotoDtoList(List<ZfPhotoDto> zfPhotoDtoList) {
		this.zfPhotoDtoList = zfPhotoDtoList;
	}
	public List<ZfLjDto> getZfLjDtoList() {
		return zfLjDtoList;
	}
	public void setZfLjDtoList(List<ZfLjDto> zfLjDtoList) {
		this.zfLjDtoList = zfLjDtoList;
	}
	public List<ZfSyDto> getZfSyDtoList() {
		return zfSyDtoList;
	}
	public void setZfSyDtoList(List<ZfSyDto> zfSyDtoList) {
		this.zfSyDtoList = zfSyDtoList;
	}
}
