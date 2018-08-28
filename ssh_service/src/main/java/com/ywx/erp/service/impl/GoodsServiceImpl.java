package com.ywx.erp.service.impl;

import com.ywx.erp.dao.GoodsDao;
import com.ywx.erp.entity.GoodsDo;
import com.ywx.erp.service.GoodsService;

public class GoodsServiceImpl extends BaseServiceImpl<GoodsDo> implements GoodsService {

    private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        super.setBaseDao(goodsDao);
        this.goodsDao = goodsDao;
    }

}
