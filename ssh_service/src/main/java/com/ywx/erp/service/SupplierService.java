package com.ywx.erp.service;

import com.ywx.erp.entity.SupplierDo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SupplierService extends BaseService<SupplierDo> {

//    void export(OutputStream os, SupplierDo t);

    void doImport(InputStream is) throws IOException;

}
