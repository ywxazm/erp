package com.ywx.erp.service;

import com.ywx.erp.entity.OrdersDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public interface OrdersService extends BaseService<OrdersDo> {

    void addDo(OrdersDo ordersDo);

    void doCheck(long id, long empDoId);

    void doStart(long id, long empDoId);

    void exportById(OutputStream os, Long uuid) throws IOException;
}
