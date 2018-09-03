package com.ywx.erp.service;

import com.ywx.erp.entity.StoreDo;

import javax.mail.MessagingException;

public interface StoreService extends BaseService<StoreDo> {

    void sendWarnStoreMail() throws MessagingException;
}
