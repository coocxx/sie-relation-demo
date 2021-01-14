package com.sie.iot.demo.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="demo_user")
public class DemoUserEntity_HI implements Serializable {
    private Long userId; //主键
    private String userName; //用户名
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date userBirthdate; //用户生日
    private String userAddress; //用户地址
    private String userMobile; //联系方式
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate; //创建时间
    private Long createdBy; //创建人
    private Long lastUpdatedBy; //更新人
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate; //更新时间
    private Long lastUpdateLogin; //最后登录id
    private Integer deleteFlag = 0; // 删除标识 0未删除 1删除
    private Integer versionNum; // 版本号

//    private List<DemoDeptEntity_HI> demoDeptEntity_his;

    private Long operatorUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false, length=20)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name="user_name", nullable=true, length=20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="user_birthdate", nullable=true, length=0)
    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    @Column(name="user_address", nullable=true, length=255)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Column(name="user_mobile", nullable=true, length=20)
    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    @Column(name="creation_date", nullable=true, length=0)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name="created_by", nullable=true, length=19)
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name="last_updated_by", nullable=true, length=19)
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name="last_update_date", nullable=true, length=0)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name="last_update_login", nullable=true, length=19)
    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name="delete_flag", nullable=true, length=10)
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Version
    @Column(name="version_num", nullable=true, length=10)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Transient
    public Long getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Long operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "demo_dept_user_rel",//中间表名
//            joinColumns = {@JoinColumn(name = "user_id")},//当前对象id在中间表的列名
//            inverseJoinColumns = {@JoinColumn(name = "dept_id")}//另一张表在中间表的列名
//    )
//    @JsonIgnore
//    public List<DemoDeptEntity_HI> getDemoDeptEntity_his() {
//        return demoDeptEntity_his;
//    }
//
//    public void setDemoDeptEntity_his(List<DemoDeptEntity_HI> demoDeptEntity_his) {
//        this.demoDeptEntity_his = demoDeptEntity_his;
//    }
}
