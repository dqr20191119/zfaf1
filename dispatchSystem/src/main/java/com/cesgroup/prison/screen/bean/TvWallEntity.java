package com.cesgroup.prison.screen.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by DELL on 2019/5/21.
 */
@Entity
@Table(name="T_TV_WALL")
public class TvWallEntity extends StringIDEntity{


        private static final long serialVersionUID = 9053326548613703894L;
        private String cusNumber;
        private String code;
        private String name;
        private String state;
        private String createUserId;

        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        private String updateUserId;

        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private  Date updateTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCusNumber() {
            return cusNumber;
        }

        public void setCusNumber(String cusNumber) {
            this.cusNumber = cusNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }
}
