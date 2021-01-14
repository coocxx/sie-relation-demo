package com.sie.iot.demo.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class DemoDeptUserRelEntity_RO implements Serializable {

    public static final String QUERY_SQL = " SELECT\n" +
            "\tu.user_id,\n" +
            "\tu.user_name,\n" +
            "\tu.user_birthdate,\n" +
            "\tu.user_address \n" +
            "FROM\n" +
            "\tdemo_dept_user_rel r\n" +
            "\tLEFT JOIN demo_user u ON r.user_id = u.user_id \n" +
            "WHERE\n" +
            "\tr.delete_flag = 0 /*and*/\n" +
            "ORDER BY\n" +
            "\tr.id DESC ";


    public static void main(String[] args) {
        System.out.println(QUERY_SQL);
    }

    private Long userId; //主键
    private String userName; //用户名称
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date userBirthdate; //用户生日
    private String userAddress; //用户地址

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
}
