package com.ywx.erp.service;

import com.ywx.erp.entity.OrdersDo;

public interface OrdersService extends BaseService<OrdersDo> {

    void addDo(OrdersDo ordersDo);

    void doCheck(long id, long empDoId);

    void doStart(long id, long empDoId);


}
