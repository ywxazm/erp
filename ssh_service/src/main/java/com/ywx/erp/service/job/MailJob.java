package com.ywx.erp.service.job;

import com.ywx.erp.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;

public class MailJob {

    static final Logger logger = LoggerFactory.getLogger(MailJob.class);

    private StoreService storeService;
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    public void sendStoreWarnMail() {
        logger.debug("operaObj is = {}, sendStoreWarnMail doing, currentTime = {}", this, System.currentTimeMillis());
        try {
            storeService.sendWarnStoreMail();
        } catch (MessagingException e) {
            logger.error("operaObj is = {}, sendStoreWarnMail is error, msg = {}", this, e.getMessage());
        }
    }

}
