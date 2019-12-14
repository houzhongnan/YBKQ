package com.kqxt.springboot.model.base;

import sun.plugin2.message.Serializer;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 4633451373316892528L;

    // 响应业务状态
    public int status = 200;

    // 响应消息
    public String msg = "操作成功";

    // 响应数据
    public Object data = null;

    /**
     *
     * <p>
     * Title: 响应失败
     * </p>
     *
     * @return ResponseResult
     */
    public static ResponseResult fail() {
        return fail("操作失败");
    }

    /**
     *
     * <p>
     * Title: 响应失败，但是自定义响应消息l
     * </p>
     *
     * @param msg 需要自定义的响应消息
     * @return ResponseResult
     */
    public static ResponseResult fail(String msg) {
        return bulid(500, msg, null);
    }

    /**
     *
     * <p>
     * Title: 成功并且传递数据，并且自定义响应消息内容
     * </p>
     *
     * @param msg  响应消息内容
     * @param data 响应数据
     * @return ResponseResult
     */
    public static ResponseResult ok(String msg, Object data) {
        return bulid(200, msg, data);
    }

    /**
     *
     * <p>
     * Title: 成功并且传递数据，但是不自定义消息
     * </p>
     *
     * @param data 需要传递的数据
     * @return ResponseResult
     */
    public static ResponseResult ok(Object data) {
        return ok("操作成功", data);
    }

    /**
     *
     * <p>
     * Title: 成功，不传递信息，也不自定义信息
     * </p>
     *
     * @return ResponseResult
     */
    public static ResponseResult ok() {
        return ok("操作成功", null);
    }

    /**
     * '
     *
     * <p>
     * Title: 成功，不传递数据，但是需要自定响应消息
     * </p>
     *
     * @param msg 需要自定义的响应消息
     * @return ResponseResult
     */
    public static ResponseResult ok(String msg) {
        return ok(msg, null);
    }

    /**
     *
     * <p>
     * Title: 自定义响应结构
     * </p>
     *
     * @param status 响应状态
     * @param msg    响应消息
     * @param data   响应数据
     * @return ResponseResult
     */
    public static ResponseResult bulid(int status, String msg, Object data) {
        return new ResponseResult(status, msg, data);
    }


    public ResponseResult(int status, boolean b, String data) {
        super();
    }

    /**
     *
     * @param status
     * @param msg
     * @param data
     */
    public ResponseResult(int status, String msg, Object data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /*
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
    }
}

