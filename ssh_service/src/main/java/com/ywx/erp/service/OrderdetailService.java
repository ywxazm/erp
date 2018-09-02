package com.ywx.erp.service;

import com.ywx.erp.entity.OrderdetailDo;

public interface OrderdetailService extends BaseService<OrderdetailDo> {

    void doInStore(long uuid, long storeId, long empId);

    void doOutStore(long uuid, long storeId, long empId);


}
