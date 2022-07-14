package com.cesgroup.prison.wghf.wgzrfp.entiy;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "T_WGGL_PEOPLE" , schema = "PRISON")
public class Wggl extends StringIDEntity{
		private String id;
		/* 监狱编号 */
		private String jybh;
		/* 网格id */
		private String wgid;
		/* 管理格格长 */
		private String glggz;
		/* 区域格格长 */
		private String qyggz;
		/* 基础格格长 */
		private String jcggz;
		/* 时间 */
		private Date time;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getJybh() {
			return jybh;
		}
		public void setJybh(String jybh) {
			this.jybh = jybh;
		}
		public String getWgid() {
			return wgid;
		}
		public void setWgid(String wgid) {
			this.wgid = wgid;
		}
		public String getGlggz() {
			return glggz;
		}
		public void setGlggz(String glggz) {
			this.glggz = glggz;
		}
		public String getQyggz() {
			return qyggz;
		}
		public void setQyggz(String qyggz) {
			this.qyggz = qyggz;
		}
		public String getJcggz() {
			return jcggz;
		}
		public void setJcggz(String jcggz) {
			this.jcggz = jcggz;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}
		
		
}
