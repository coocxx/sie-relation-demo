package com.sie.iot.demo.model.dao;

import com.sie.iot.common.model.dao.BaseCommonDAO_HI;
import com.sie.iot.demo.model.entities.DemoDeptUserRelEntity_HI;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("demoDeptUserRelDAO_HI")
public class DemoDeptUserRelDAO_HI extends BaseCommonDAO_HI<DemoDeptUserRelEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptUserRelDAO_HI.class);

	public DemoDeptUserRelDAO_HI() {
		super();
	}

}
