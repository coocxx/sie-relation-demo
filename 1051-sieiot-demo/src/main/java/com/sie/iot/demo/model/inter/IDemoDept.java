package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.entities.readonly.DemoDeptEntity_RO;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.sie.iot.common.model.inter.IBaseCommon;

public interface IDemoDept extends IBaseCommon<DemoDeptEntity_HI>{

	DemoDeptEntity_HI findById(Long id);
	Pagination<DemoDeptEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);

	Pagination<DemoDeptEntity_RO> findRoPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);

    boolean checkDeptName(String deptName);

    void save(DemoDeptEntity_HI demoDeptEntity_hi, List<Long> userIds);

    void update(DemoDeptEntity_HI demoDeptEntity_hi, List<Long> userIds);

}
