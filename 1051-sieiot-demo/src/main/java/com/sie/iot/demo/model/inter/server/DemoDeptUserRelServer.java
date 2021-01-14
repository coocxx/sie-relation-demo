package com.sie.iot.demo.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import com.sie.iot.common.bean.OrderByBean;
import com.sie.iot.demo.model.dao.readonly.DemoDeptUserRelDAO_HI_RO;
import com.sie.iot.demo.model.dao.readonly.DemoDeptUserRelDAO_HI_RO_Second;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO_Second;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sie.iot.common.util.SaafToolUtils;
import com.siefw.base.utils.SToolUtils;
import com.siefw.hibernate.core.paging.Pagination;
import com.sie.iot.demo.model.entities.DemoDeptUserRelEntity_HI;
import com.siefw.hibernate.core.dao.ViewObject;
import com.sie.iot.demo.model.inter.IDemoDeptUserRel;
import com.sie.iot.common.model.inter.server.BaseCommonServer;

@Component("demoDeptUserRelServer")
public class DemoDeptUserRelServer extends BaseCommonServer<DemoDeptUserRelEntity_HI> implements IDemoDeptUserRel{
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptUserRelServer.class);

	@Autowired
	private ViewObject<DemoDeptUserRelEntity_HI> demoDeptUserRelDAO_HI;
	@Autowired
	private DemoDeptUserRelDAO_HI_RO demoDeptUserRelDAO_HI_RO;
	@Autowired
	private DemoDeptUserRelDAO_HI_RO_Second demoDeptUserRelDAO_HI_RO_Second;

	public DemoDeptUserRelServer() {
		super();
	}

	@Override
	public Object save(DemoDeptUserRelEntity_HI demoDeptUserRelEntity_HI) {
		return demoDeptUserRelDAO_HI.save(demoDeptUserRelEntity_HI);
	}
	@Override
	public void update(DemoDeptUserRelEntity_HI demoDeptUserRelEntity_HI) {
		demoDeptUserRelDAO_HI.saveOrUpdate(demoDeptUserRelEntity_HI);
	}
	@Override
	public DemoDeptUserRelEntity_HI findById(Long id) {
		DemoDeptUserRelEntity_HI demoDeptUserRelEntity_HI = this.getById(id);
		return demoDeptUserRelEntity_HI;
	}
	@Override
	public List<DemoDeptUserRelEntity_HI> findList(JSONObject jsonObject, OrderByBean orderByBean) {
		Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
		StringBuffer querySQLSB = new StringBuffer(" from DemoDeptUserRelEntity_HI where 1=1 ");
		//f(jsonObject.containsKey("unitName")){
		//  querySQLSB.append("and unitName like :%unitName% ");
		//}
		//if(jsonObject.containsKey("useStatus")){
		//  querySQLSB.append("and useStatus = :useStatus ");
		//}
		SaafToolUtils.sortUtil(orderByBean, querySQLSB);
		List<DemoDeptUserRelEntity_HI> demoDeptUserRelEntity_HIs = demoDeptUserRelDAO_HI.findList(querySQLSB.toString(), paramsMap);
		return demoDeptUserRelEntity_HIs;
	}
	@Override
	public Pagination<DemoDeptUserRelEntity_HI> findPagination(JSONObject jsonObject, Integer pageIndex, Integer pageRows, OrderByBean orderByBean) {
		Map<String, Object> paramsMap = SToolUtils.fastJsonObj2Map(jsonObject);
		StringBuffer querySQLSB = new StringBuffer(" from DemoDeptUserRelEntity_HI where 1=1 ");
		//if(jsonObject.containsKey("unitName")){
		//    querySQLSB.append("and unitName like :%unitName% ");
		//}
		//if(jsonObject.containsKey("useStatus")){
		//    querySQLSB.append("and useStatus = :useStatus ");
		//}
		SaafToolUtils.sortUtil(orderByBean, querySQLSB);
		Pagination<DemoDeptUserRelEntity_HI> demoDeptUserRelEntity_HIs = demoDeptUserRelDAO_HI.findPagination(querySQLSB.toString(), paramsMap, pageIndex, pageRows);
		return demoDeptUserRelEntity_HIs;
	}

	@Override
	public List<DemoDeptUserRelEntity_RO> findUserListByDeptId(Long deptId) {
		return demoDeptUserRelDAO_HI_RO.findList(DemoDeptUserRelEntity_RO.QUERY_SQL.replace("/*and*/", "and r.dept_id = ? "), deptId);
	}
	@Override
	public List<DemoDeptUserRelEntity_RO_Second> findDeptByUserId(Long UserId) {
		return demoDeptUserRelDAO_HI_RO_Second.findList(DemoDeptUserRelEntity_RO_Second.QUERY_SQL_USERBYDEPT.replace("/*and*/", "and r.user_id = ? "), UserId);
	}

}
