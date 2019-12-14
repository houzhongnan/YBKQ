package com.kqxt.springboot.util;

import com.google.gson.Gson;
import com.kqxt.springboot.model.base.Constant;
import com.kqxt.springboot.model.base.JsonCode;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class LayuiRe<T> {
    private int code;
    private String msg;
    private int count;
    private List<T> data;
    public LayuiRe(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public static <T> String toJson(int count, List<T> data) {
        LayuiRe<T> replay = new LayuiRe<T>(JsonCode.SUCCESS.getValue(), JsonCode.SUCCESS.getMessage(), count, data);
        return replay.toJson();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
