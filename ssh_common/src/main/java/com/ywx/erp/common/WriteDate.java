package com.ywx.erp.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public interface WriteDate {

    Logger logger = LoggerFactory.getLogger(WriteDate.class);

    /**
     * 写数据到前台
     * @param jsonString
     */
    default void write(String jsonString) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType(BaseConstants.CONTENTTYPE_JSON);
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            logger.error("operaObj is = {},  write is error, info = {}", this, e.getMessage());
        }
    }

    /**
     * 写结果数据到前台
     * @param success
     * @param msg
     */
    default String ajaxReturn(boolean success, String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(BaseConstants.SUCCESS, success);
        map.put(BaseConstants.MSG, msg);
        return JSONObject.toJSONString(map);
    }
}
