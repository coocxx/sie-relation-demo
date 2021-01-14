package com.sie.iot.demo.model.dao.readonly;

import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO;
import com.sie.iot.demo.model.entities.readonly.DemoDeptUserRelEntity_RO_Second;
import com.siefw.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("demoDeptUserRelDAO_HI_RO_Second")
public class DemoDeptUserRelDAO_HI_RO_Second extends DynamicViewObjectImpl<DemoDeptUserRelEntity_RO_Second> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDeptUserRelDAO_HI_RO.class);

    public DemoDeptUserRelDAO_HI_RO_Second() {
        super();
    }

}


