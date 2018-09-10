package com.ywx.erp.service;

import com.ywx.erp.entity.OrderdetailDo;

public interface OrderdetailService extends BaseService<OrderdetailDo> {

    void doInStore(int uuid, int storeId, int empId);

    void doOutStore(int uuid, int storeId, int empId);
}
