package cn.alucardlockon.codebox.entity;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;

public class ApiResponse {
    private boolean success;

    private String message;

    private EasyMap data;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, EasyMap data) {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EasyMap getData() {
        return data;
    }

    public void setData(EasyMap data) {
        this.data = data;
    }

    public static ApiResponse ok() {
        return new ApiResponse(true, "", null);
    }

    public static ApiResponse ok(EasyMap map) {
        return new ApiResponse(true, "", map);
    }

    public static ApiResponse list(List<EasyMap> list) {
        return new ApiResponse(true, "", new EasyMap().pute("list", list));
    }

    public static ApiResponse error() {
        return new ApiResponse(false, "", null);
    }

    public static ApiResponse error(String message) {
        return new ApiResponse(false, message, null);
    }

    public static ApiResponse error(Throwable throwable) {
        return new ApiResponse(false, throwable.getMessage(), null);
    }

}
