package com.ywx.erp.service.impl;

import com.ywx.erp.common.MailUtil;
import com.ywx.erp.dao.StoreDao;
import com.ywx.erp.entity.StoreAlertDo;
import com.ywx.erp.entity.StoreDo;
import com.ywx.erp.service.StoreService;

import javax.mail.MessagingException;
import java.util.List;

public class StoreServiceImpl extends BaseServiceImpl<StoreDo> implements StoreService {

    private StoreDao storeDao;
    private MailUtil mailUtil;
    public void setStoreDao(StoreDao storeDao) {
        super.setBaseDao(storeDao);
        this.storeDao = storeDao;
    }
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    @Override
    public void sendWarnStoreMail() throws MessagingException {
        List<StoreAlertDo> storeAlertDoList = storeDao.queryInventory();
        if (null != storeAlertDoList && storeAlertDoList.size() > 0) {
            StringBuffer msg = new StringBuffer("如下商品库存不足：\n");
            for (StoreAlertDo storeAlertDo : storeAlertDoList) {
                msg.append("商品ID: ").append(storeAlertDo.getGuuid());
                msg.append("商品名称: ").append(storeAlertDo.getGname());
                msg.append("仓库名称: ").append(storeAlertDo.getSname());
                msg.append("库存数量: ").append(storeAlertDo.getSdnum());
                msg.append("订单数量: ").append(storeAlertDo.getOdnum()).append("\n");
            }
            mailUtil.sendMail("18824278018@139.com", "库存报警", msg.toString());
        }
    }
}
