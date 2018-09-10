package com.ywx.erp.service;

import com.ywx.erp.entity.OrdersDo;

public interface OrdersService extends BaseService<OrdersDo> {

    void addDo(OrdersDo ordersDo);

    void doCheck(int id, int empDoId);

    void doStart(int id, int empDoId);
}
