package com.sie.iot.demo.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO_Second;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptUserRelEntity_HI;
import com.sie.iot.common.model.inter.IBaseCommon;

public interface IDemoDeptUserRel extends IBaseCommon<DemoDeptUserRelEntity_HI>{

	DemoDeptUserRelEntity_HI findById(Long id);
	List<DemoDeptUserRelEntity_HI> findList(JSONObject jsonObject, OrderByBean orderByBean);
	Pagination<DemoDeptUserRelEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean);

	/**
	 * 通过deptId获取所属人员信息
	 * @param deptId
	 * @return
	 */
	List<DemoDeptUserRelEntity_RO> findUserListByDeptId(Long deptId);
	/**
	 * 通过UserId获取所属部门信息
	 * @param UserId
	 * @return
	 */
	List<DemoDeptUserRelEntity_RO_Second> findDeptByUserId(Long UserId);



}
