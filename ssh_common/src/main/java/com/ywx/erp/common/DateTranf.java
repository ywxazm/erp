package com.ywx.erp.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: 要改成线程安全的格式
 */
public class DateTranf {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String Date2String(Date date) {
        return dateFormat.format(date);
    }
}
