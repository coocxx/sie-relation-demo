package com.sie.iot.demo.model.dao.readonly;

import com.sie.iot.demo.model.entities.readonly.DemoDeptEntity_RO;
import com.sie.iot.demo.model.entities.readonly.DemoUserEntity_RO;
import com.siefw.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoUserDAO_HI_RO extends DynamicViewObjectImpl<DemoUserEntity_RO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserDAO_HI_RO.class);

    public DemoUserDAO_HI_RO() {
        super();
    }
}
