package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO;
import com.siefw.hibernate.core.paging.Pagination;

public interface IDemoUser extends IBaseCommon<DemoUserEntity_HI> {
    DemoUserEntity_HI findById(Long id);
    Pagination<DemoUserEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);
//    Pagination<DemoDeptUserRelEntity_RO> findRoPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);

    boolean checkUserName(String userName);
}
