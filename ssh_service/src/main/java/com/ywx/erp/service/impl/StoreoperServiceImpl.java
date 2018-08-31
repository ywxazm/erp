package com.ywx.erp.service.impl;

import com.ywx.erp.dao.StoreoperDao;
import com.ywx.erp.entity.StoreoperDo;
import com.ywx.erp.service.StoreoperServie;

public class StoreoperServiceImpl extends BaseServiceImpl<StoreoperDo> implements StoreoperServie {

    private StoreoperDao storeoperDao;
    public void setStoreoperDao(StoreoperDao storeoperDao) {
        super.setBaseDao(storeoperDao);
        this.storeoperDao = storeoperDao;
    }

}
