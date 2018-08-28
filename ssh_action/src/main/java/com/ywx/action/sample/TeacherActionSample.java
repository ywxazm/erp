package com.ywx.action.sample;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.ywx.entity.sample.TeacherDo;
import com.ywx.service.sample.TeacherServiceSample;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TeacherActionSample extends ActionSupport {

    private static final Logger logger = LoggerFactory.getLogger(TeacherActionSample.class);

    private TeacherServiceSample teacherServiceSample;

    public void setTeacherServiceSample(TeacherServiceSample teacherServiceSample) {
        this.teacherServiceSample = teacherServiceSample;
    }

    public void list(){
        List<TeacherDo> list = teacherServiceSample.list();
        String str = JSONObject.toJSONString(list);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(str);
        } catch (IOException e) {
            logger.error("{},  writer error", e.getMessage());
        }finally {
            writer.close();
        }
    }
}
