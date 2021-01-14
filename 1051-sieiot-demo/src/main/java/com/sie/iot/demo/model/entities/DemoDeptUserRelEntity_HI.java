package com.sie.iot.demo.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * DemoDeptUserRelEntity_HI Entity Object
 * Mon Jul 06 15:31:11 CST 2020  Auto Generate
 */
@Entity
@Table(name="demo_dept_user_rel")
public class DemoDeptUserRelEntity_HI implements Serializable{
    private Long id;
    private Long userId; //用户ID
    private Long deptId; //部门ID
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date creationDate; //创建时间
	private Long createdBy; //创建人
	private Long lastUpdatedBy; //更新人
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate; //更新时间
	private Long lastUpdateLogin; //最后登录ID
	private Integer deleteFlag = 0; //删除标识 0表示未删除 1表示删除
	private Integer versionNum; //版本号
	private Long operatorUserId;




	public void setId(Long id) {
		this.id = id;
	}

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="id", nullable=false, length=20)	
	public Long getId() {
		return id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="user_id", nullable=true, length=20)	
	public Long getUserId() {
		return userId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Column(name="dept_id", nullable=true, length=20)	
	public Long getDeptId() {
		return deptId;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="creation_date", nullable=true, length=0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_by", nullable=true, length=19)
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="last_updated_by", nullable=true, length=19)
	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name="last_update_date", nullable=true, length=0)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name="last_update_login", nullable=true, length=19)
	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name="delete_flag", nullable=true, length=10)
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name="version_num", nullable=true, length=10)
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setOperatorUserId(Long operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Long getOperatorUserId() {
		return operatorUserId;
	}
}
