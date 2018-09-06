package com.ywx.erp.service.impl;

import com.ywx.erp.dao.SupplierDao;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.service.SupplierService;

public class SupplierServiceImpl extends BaseServiceImpl<SupplierDo> implements SupplierService {

    private SupplierDao supplierDao;
    public void setSupplierDao(SupplierDao supplierDao) {
        super.setBaseDao(supplierDao);
        this.supplierDao = supplierDao;
    }

}
