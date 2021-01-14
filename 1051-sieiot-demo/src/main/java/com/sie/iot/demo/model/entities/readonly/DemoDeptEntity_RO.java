package com.sie.iot.demo.model.entities.readonly;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class DemoDeptEntity_RO implements Serializable {

    public static final String QUERY_SQL = " SELECT\n" +
            "\td.*,\n" +
            "\t( SELECT count( 1 ) FROM demo_dept_user_rel r WHERE r.dept_id = d.dept_id ) userCount \n" +
            "FROM\n" +
            "\tdemo_dept d \n" +
            "WHERE\n" +
            "\tdelete_flag = 0 ";

    public static final String QUERY_DEPT = "SELECT\n" +
            "\tu.user_id,\n" +
            "\tu.user_name,\n" +
            "\tu.user_mobile,\n" +
            "\tu.user_address,\n" +
            "\tu.user_birthdate \n" +
            "FROM\n" +
            "\tdemo_user u\n" +
            "\tLEFT JOIN demo_dept_user_rel r ON r.user_id = u.user_id \n" +
            "\tLEFT JOIN demo_dept d on d.dept_id = r.dept_id\n" +
            "WHERE\n" +
            "\td.delete_flag = 0 ";


    public static void main(String[] args) {
        System.out.println(QUERY_SQL);
    }

    private Long deptId; //主键
    private String deptName; //部门名称
    private String deptCode; //部门编码
    private String deptAddress; //部门地址
    private String deptContactName; //部门联系人
    private Long deptContactId; //部门联系人ID
    private String deptContactMoblie; //联系电话
    private int userCount; //部门人员

    private Long userId;//人员id
    private String userName;//人员名称
    private String userMobile;//联系方式
    private String userAddress;//地址
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date userBirthdate;//生日

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getDeptContactName() {
        return deptContactName;
    }

    public void setDeptContactName(String deptContactName) {
        this.deptContactName = deptContactName;
    }

    public Long getDeptContactId() {
        return deptContactId;
    }

    public void setDeptContactId(Long deptContactId) {
        this.deptContactId = deptContactId;
    }

    public String getDeptContactMoblie() {
        return deptContactMoblie;
    }

    public void setDeptContactMoblie(String deptContactMoblie) {
        this.deptContactMoblie = deptContactMoblie;
    }

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }
}
