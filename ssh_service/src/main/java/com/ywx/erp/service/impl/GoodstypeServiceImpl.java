package com.ywx.erp.service.impl;

import com.ywx.erp.dao.GoodstypeDao;
import com.ywx.erp.entity.GoodstypeDo;
import com.ywx.erp.service.GoodstypeService;

public class GoodstypeServiceImpl extends BaseServiceImpl<GoodstypeDo> implements GoodstypeService {

    private GoodstypeDao goodstypeDao;
    public void setGoodstypeDao(GoodstypeDao goodstypeDao) {
        super.setBaseDao(goodstypeDao);
        this.goodstypeDao = goodstypeDao;
    }
}
