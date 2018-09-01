package com.ywx.erp.action;

import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.StoreDo;
import com.ywx.erp.service.StoreService;
import org.apache.struts2.ServletActionContext;

public class StoreAction extends BaseAction<StoreDo> {

    private StoreService storeService;
    public void setStoreService(StoreService storeService) {
        super.setBaseService(storeService);
        this.storeService = storeService;
    }

    /**
     * 查看当前用户名下仓库列表
     */
    public void myListStore() {
        logger.debug("operaObj is = {}, myListStore doing, Do = {}", this, t);
        try {
            if (null == getT()) {
                setT(new StoreDo());
            }

            EmpDo user = getLoginUser();

            getT().setEmpuuid(user.getUuid());
            super.list();
        }catch (Exception ex) {
            logger.error("operaObj is = {}, myListStore is error, msg = {}", this, ex.getMessage());
        }
    }

}
