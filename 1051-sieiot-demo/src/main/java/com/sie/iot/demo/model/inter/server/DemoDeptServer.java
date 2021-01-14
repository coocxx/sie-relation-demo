package com.sie.iot.demo.model.inter.server;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.dao.readonly.DemoDeptDAO_HI_RO;
import com.sie.iot.demo.model.entities.DemoDeptUserRelEntity_HI;
import com.sie.iot.demo.model.entities.readonly.DemoDeptEntity_RO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sie.iot.common.util.SaafToolUtils;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptEntity_HI;
import com.siefw.hibernate.core.dao.ViewObject;
import com.sie.iot.demo.model.inter.IDemoDept;
import com.sie.iot.common.model.inter.server.BaseCommonServer;

@Component("demoDeptServer")
public class DemoDeptServer extends BaseCommonServer<DemoDeptEntity_HI> implements IDemoDept{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptServer.class);

	@Autowired
	private ViewObject<DemoDeptEntity_HI> demoDeptDAO_HI;
	@Autowired
	private ViewObject<DemoDeptUserRelEntity_HI> demoDeptUserRelDAO_HI;
	@Autowired
	private DemoDeptDAO_HI_RO demoDeptDAO_HI_RO;

	public DemoDeptServer() {
		super();
	}

	@Override
	public Object save(DemoDeptEntity_HI demoDeptEntity_HI) {
		return demoDeptDAO_HI.save(demoDeptEntity_HI);
	}
	@Override
	public void update(DemoDeptEntity_HI demoDeptEntity_HI) {
		demoDeptDAO_HI.saveOrUpdate(demoDeptEntity_HI);
	}
	@Override
	public DemoDeptEntity_HI findById(Long id) {
		DemoDeptEntity_HI demoDeptEntity_HI = this.getById(id);
		return demoDeptEntity_HI;
	}

	@Override
	public Pagination<DemoDeptEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
		Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
		StringBuffer querySQLSB = new StringBuffer(" from DemoDeptEntity_HI where deleteFlag=0 ");

		SaafToolUtils.parperParam(jsonObject, "dept_name", "deptName", querySQLSB, paramsMap, "like");

//		if(jsonObject.get("acAssetStatuss")!=null){
//			List<String> acAssetStatuss =  JSONObject.parseArray(JSONObject.toJSONString(jsonObject.get("acAssetStatuss")),String.class);
//			paramsMap.put("acAssetStatuss", acAssetStatuss);
//			querySQLSB.append(" and acAssetStatus in ( :acAssetStatuss )");
//		}else{
//			paramsMap.remove("acAssetStatuss");
//		}


		if(orderByBean==null){
			OrderByBean orderByBeanDefault = new OrderByBean();
			orderByBeanDefault.setAttributeName("creation_date");
			orderByBeanDefault.setSortType("desc");
			orderByBean = orderByBeanDefault;
		}
		SaafToolUtils.sortUtil(orderByBean, querySQLSB);
		Pagination<DemoDeptEntity_HI> demoDeptEntity_HIs = demoDeptDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
		return demoDeptEntity_HIs;
	}

	@Override
	public Pagination<DemoDeptEntity_RO> findRoPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
		StringBuffer sql = new StringBuffer(DemoDeptEntity_RO.QUERY_SQL);
		Map<String,Object> params = new HashMap<>();
		if(ObjectUtil.isNotEmpty(jsonObject.get("deptId"))){
			sql.append(" AND d.dept_id = " + jsonObject.get("deptId"));
		}
		if(StrUtil.isNotEmpty(jsonObject.getString("deptName"))){
			sql.append(" AND d.dept_name like  '%" + jsonObject.get("deptName") +"%'");
		}
		if(orderByBean==null){
			OrderByBean orderByBeanDefault = new OrderByBean();
			orderByBeanDefault.setAttributeName("creation_date");
			orderByBeanDefault.setSortType("desc");
			orderByBean = orderByBeanDefault;
		}
		SaafToolUtils.sortUtil(orderByBean, sql);
		return demoDeptDAO_HI_RO.findPagination(sql, params, pageIndex, pageRows);
	}

	@Override
    public boolean checkDeptName(String deptName) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("deptName",deptName);
		List<DemoDeptEntity_HI> list = demoDeptDAO_HI.findByProperty(condition);
		return list!=null&&list.size()>0;
	}

    @Override
    public void save(DemoDeptEntity_HI demoDeptEntity_hi, List<Long> userIds) {
        this.save(demoDeptEntity_hi);
        //demoDeptEntity_hi   deptId
        List<DemoDeptUserRelEntity_HI> rels = new ArrayList<>();
		Optional.ofNullable(userIds).ifPresent(ids->ids.stream().forEach(userId->{
			DemoDeptUserRelEntity_HI rel = new DemoDeptUserRelEntity_HI();
			rel.setDeptId(demoDeptEntity_hi.getDeptId());
			rel.setOperatorUserId(demoDeptEntity_hi.getOperatorUserId());
			rel.setUserId(userId);
			rels.add(rel);

		}));
		demoDeptUserRelDAO_HI.save(rels);
    }

	@Override
	public void update(DemoDeptEntity_HI demoDeptEntity_hi, List<Long> userIds) {
		this.update(demoDeptEntity_hi);
		//删除原来部门和人员的关联关系
		demoDeptUserRelDAO_HI.executeSqlUpdate(" delete from demo_dept_user_rel where dept_id = " + demoDeptEntity_hi.getDeptId());

		//保存
		List<DemoDeptUserRelEntity_HI> rels = new ArrayList<>();
		Optional.ofNullable(userIds).ifPresent(ids->ids.stream().forEach(userId->{
			DemoDeptUserRelEntity_HI rel = new DemoDeptUserRelEntity_HI();
			rel.setDeptId(demoDeptEntity_hi.getDeptId());
			rel.setOperatorUserId(demoDeptEntity_hi.getOperatorUserId());
			rel.setUserId(userId);
			rels.add(rel);
		}));
		demoDeptUserRelDAO_HI.save(rels);
	}
}
