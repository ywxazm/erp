package com.ywx.erp.service.impl;

import com.ywx.erp.dao.StoreDao;
import com.ywx.erp.entity.StoreDo;
import com.ywx.erp.service.StoreService;

public class StoreServiceImpl extends BaseServiceImpl<StoreDo> implements StoreService {

    private StoreDao storeDao;
    public void setStoreDao(StoreDao storeDao) {
        super.setBaseDao(storeDao);
        this.storeDao = storeDao;
    }

}
