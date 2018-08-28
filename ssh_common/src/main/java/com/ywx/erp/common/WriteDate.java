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
     * 写部门数据到前台
     * @param jsonString
     */
    default void write(String jsonString) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            logger.error("operaObj is = {},  is write error, info = {}", this, e.getMessage());
        }
    }

    /**
     * 写结果数据到前台
     * @param success
     * @param msg
     * @return
     */
    default String ajaxReturn(boolean success, String msg) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("msg", msg);
        return JSONObject.toJSONString(map);
    }
}
