package com.sie.iot.demo.model.dao.readonly;

import com.sie.iot.demo.model.entities.readonly.DemoDeptEntity_RO;
import com.siefw.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("demoDeptDAO_HI_RO")
public class DemoDeptDAO_HI_RO extends DynamicViewObjectImpl<DemoDeptEntity_RO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptDAO_HI_RO.class);

	public DemoDeptDAO_HI_RO() {
		super();
	}

}
