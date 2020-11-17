package com.hhuc.webdesign.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnPkg implements Serializable {
    /*
     status:
        200：正常
        400：错误
        401：未登录
     */
    private String status;
    private Object data;
    private String reason;

    public static ReturnPkg success(Object data){
        ReturnPkg returnPkg =  new ReturnPkg();
        returnPkg.setStatus("200");
        returnPkg.setData(data);
        returnPkg.setReason("");
        return returnPkg;
    }

    public static ReturnPkg success(){
        ReturnPkg returnPkg =  new ReturnPkg();
        returnPkg.setStatus("200");
        returnPkg.setData("");
        returnPkg.setReason("");
        return returnPkg;
    }

    public static ReturnPkg failed(String reason){
        ReturnPkg returnPkg =  new ReturnPkg();
        returnPkg.setStatus("400");
        returnPkg.setData("");
        returnPkg.setReason(reason);
        return returnPkg;
    }

    public static ReturnPkg notLogin(){
        ReturnPkg returnPkg =  new ReturnPkg();
        returnPkg.setStatus("401");
        returnPkg.setData("null");
        returnPkg.setReason("need login");
        return returnPkg;
    }
}
