package com.example.login.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {
    private static int SUCCESS = 1;
    private static int FAIL = 0;
    private Integer status;
    private Object data;

    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(SUCCESS);
        return jsonResult;
    }

    public static JsonResult success(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult fail(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(FAIL);
        return jsonResult;
    }

    public static JsonResult fail(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(FAIL);
        jsonResult.setData(data);
        return jsonResult;
    }
}
