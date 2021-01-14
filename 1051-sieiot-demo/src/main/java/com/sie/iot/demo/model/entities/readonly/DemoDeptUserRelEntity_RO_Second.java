package com.sie.iot.demo.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class DemoDeptUserRelEntity_RO_Second implements Serializable {


    public static final String QUERY_SQL_USERBYDEPT = " SELECT\n" +
            "\tr.id,\n" +
            "\tr.user_id,\n" +
            "\tr.dept_id,\n" +
            "\td.dept_name \n" +
            "FROM\n" +
            "\tdemo_dept d\n" +
            "\tLEFT JOIN demo_dept_user_rel r ON d.dept_id = r.dept_id \n" +
            "WHERE\n" +
            "\tr.delete_flag = 0 /*and*/\n" +
            "ORDER BY\n" +
            "\tr.id DESC ";

    public static void main(String[] args) {
        System.out.println(QUERY_SQL_USERBYDEPT);
    }

    private Long Id; //主键
    private Long userId; //部门Id
    private Long deptId; //部门Id
    private String deptName; //部门名称

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
