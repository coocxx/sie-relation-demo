package com.sie.iot.demo.model.entities.readonly;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class DemoUserEntity_RO implements Serializable {
    public static final String QUERY_USER = "SELECT\n" +
            "\td.dept_id,\n" +
            "\td.dept_name,\n" +
            "\td.dept_address,\n" +
            "\td.dept_contact_moblie \n" +
            "FROM\n" +
            "\tdemo_dept d\n" +
            "\tLEFT JOIN demo_dept_user_rel r ON d.dept_id = r.dept_id\n" +
            "\tLEFT JOIN demo_user u ON r.user_id = u.user_id\n" +
            "WHERE\n" +
            "\t u.delete_flag = 0";

    public static void main(String[] args) {
        System.out.println(QUERY_USER);
    }

    private Long deptId; //主键
    private String deptName; //部门名称
    private String deptAddress; //部门地址
    private String deptContactMoblie; //联系电话

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

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getDeptContactMoblie() {
        return deptContactMoblie;
    }

    public void setDeptContactMoblie(String deptContactMoblie) {
        this.deptContactMoblie = deptContactMoblie;
    }
}
