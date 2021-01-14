package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.common.model.inter.server.BaseCommonServer;
import com.sie.iot.common.util.SaafToolUtils;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import com.sie.iot.demo.model.inter.IDemoUser;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.dao.ViewObject;
import com.siefw.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("demoUserServer")
public class DemoUserServer extends BaseCommonServer<DemoUserEntity_HI> implements IDemoUser {
    @Autowired
    private ViewObject<DemoUserEntity_HI> demoUserDAO_HI;

    public DemoUserServer() {super();}

    @Override
    public DemoUserEntity_HI findById(Long id) {
        return this.getById(id);
    }

    @Override
    public Pagination<DemoUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
        Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
        StringBuffer querySQLSB = new StringBuffer(" from DemoUserEntity_HI where deleteFlag=0 ");
        SaafToolUtils.parperParam(jsonObject, "user_name", "userName", querySQLSB, paramsMap, "like");



        if(orderByBean==null){
            OrderByBean orderByBeanDefault = new OrderByBean();
            orderByBeanDefault.setAttributeName("creation_date");
            orderByBeanDefault.setSortType("desc");
            orderByBean = orderByBeanDefault;
        }

        SaafToolUtils.sortUtil(orderByBean, querySQLSB);

        return demoUserDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
    }

    @Override
    public boolean checkUserName(String userName) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("userName", userName);
        List<DemoUserEntity_HI> list = demoUserDAO_HI.findByProperty(condition);
        return list!=null&&list.size()>0;
    }

    @Override
    public Object save(DemoUserEntity_HI demoUserEntity_HI) { return demoUserDAO_HI.save(demoUserEntity_HI);}

    @Override
    public void update(DemoUserEntity_HI demoUserEntity_HI) { demoUserDAO_HI.saveOrUpdate(demoUserEntity_HI);}
}
