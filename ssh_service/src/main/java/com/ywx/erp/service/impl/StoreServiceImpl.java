package com.ywx.erp.service.impl;

import com.ywx.erp.common.MailUtil;
import com.ywx.erp.dao.StoreDao;
import com.ywx.erp.entity.StoreAlertDo;
import com.ywx.erp.entity.StoreDo;
import com.ywx.erp.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.List;

public class StoreServiceImpl extends BaseServiceImpl<StoreDo> implements StoreService {

    protected static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    private StoreDao storeDao;
    private MailUtil mailUtil;
    public void setStoreDao(StoreDao storeDao) {
        super.setBaseDao(storeDao);
        this.storeDao = storeDao;
    }
    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    //常量定义
    private static final String RECEIVEMAIL = "18824278018@139.com";
    private static final String MAILSUBJECT = "库存报警";

    @Override
    public void sendWarnStoreMail() throws MessagingException {
        logger.debug("库存定时任务,检测库存,发送预警邮件");
        List<StoreAlertDo> storeAlertDoList = storeDao.queryInventory();
        if (null != storeAlertDoList && storeAlertDoList.size() > 0) {
            logger.debug("库存不足,发送预警邮件");
            StringBuffer msg = new StringBuffer("如下商品库存不足：\n");
            for (StoreAlertDo storeAlertDo : storeAlertDoList) {
                msg.append("商品ID: ").append(storeAlertDo.getGuuid()).append(",      ");
                msg.append("商品名称: ").append(storeAlertDo.getGname()).append(",      ");
                msg.append("仓库名称: ").append(storeAlertDo.getSname()).append(",      ");
                msg.append("库存数量: ").append(storeAlertDo.getSdnum()).append(",      ");
                msg.append("订单数量: ").append(storeAlertDo.getOdnum()).append("       ").append("\n");
            }
            mailUtil.sendMail(RECEIVEMAIL, MAILSUBJECT, msg.toString());
        }else{
            logger.debug("库存充足, 不发送库存预警邮件");
        }
    }

}
