package com.ywx.erp.service.impl;

import com.ywx.erp.dao.DepDao;
import com.ywx.erp.entity.DepDo;
import com.ywx.erp.service.DepService;

public class DepServiceImpl extends BaseServiceImpl<DepDo> implements DepService {

    private DepDao depDao;

    public void setDepDao(DepDao depDao) {
        super.setBaseDao(depDao);
        this.depDao = depDao;
    }
}
