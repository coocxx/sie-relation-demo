package com.sie.iot.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sie.iot.common.bean.ReqeustCommonDataBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("人员")
public class DemoUserBean extends ReqeustCommonDataBean {
    @ApiModelProperty(value = "主键id")
    private Long userId; //主键
    @ApiModelProperty(value = "用户名")
    private String userName; //用户名
    @ApiModelProperty(value = "用户生日")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date userBirthdate; //用户生日
    @ApiModelProperty(value = "用户地址")
    private String userAddress; //用户地址
    @ApiModelProperty(value = "联系方式")
    private String userMobile; //联系方式

    private Long operatorUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Long operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
