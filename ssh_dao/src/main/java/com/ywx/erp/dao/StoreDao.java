package com.ywx.erp.dao;

import com.ywx.erp.entity.StoreAlertDo;
import com.ywx.erp.entity.StoreDo;

import java.util.List;

public interface StoreDao extends BaseDao<StoreDo> {

    List<StoreAlertDo> queryInventory();

}
