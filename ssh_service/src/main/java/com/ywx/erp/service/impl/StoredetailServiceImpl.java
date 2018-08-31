package com.ywx.erp.service.impl;

import com.ywx.erp.dao.StoredetailDao;
import com.ywx.erp.entity.StoredetailDo;
import com.ywx.erp.service.StoredetailService;

public class StoredetailServiceImpl extends BaseServiceImpl<StoredetailDo> implements StoredetailService {

    private StoredetailDao storedetailDao;
    public void setStoredetailDao(StoredetailDao storedetailDao) {
        super.setBaseDao(storedetailDao);
        this.storedetailDao = storedetailDao;
    }
}
