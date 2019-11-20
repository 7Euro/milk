package com.leo.common;

/**
 *
 * @author zll
 * @date 2019/11/20 13:08
 **/
public class Result {
    /**
     * 表明对应请求的返回处理结果"success"或 "fail"
     */
    private boolean status;

    /**
     * 若status=success，则data内返回前台需要的json数据
     * 若status=fail，则data内使用通用的错误码格式
     * @See EmBusinessError
     */
    private Object data;

    public static Result create(Object result) {
        return Result.create(result,true);
    }
    public static Result create(Object result,boolean status) {
        Result rs = new Result();
        rs.setData(result);
        rs.setStatus(status);
        return rs;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
