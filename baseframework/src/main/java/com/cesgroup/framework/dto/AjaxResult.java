package com.cesgroup.framework.dto;
/**
 * Description: ajax返回结果DTO
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public class AjaxResult {

    /**
     * Description: 状态码
     */
    private int code;
    /**
     * Description: 提示信息
     */
    private String message;
    /**
     * Description: 具体的内容
     */
    private Object data;

    /**
     * Description: 状态码枚举类
     */
    public enum AjaxResultCode {
        /**
         * Description: 成功
         */
        SUCCESS(200),
        /**
         * Description: 失败
         */
        ERROR(500);
        int code;

        AjaxResultCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public AjaxResult() {
    }

    public AjaxResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Description: 构造器
     *
     * @param code    状态码
     * @param message 消息
     * @param data    对象信息
     */
    public AjaxResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Description: 默认消息的成功信息
     *
     * @param message 提示信息
     * @return 异步封装对象
     */
    public static AjaxResult success(String message) {
        AjaxResult result = new AjaxResult();
        result.setCode(AjaxResultCode.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * Description: 默认成功无消息
     *
     * @return 异步封装对象
     */
    public static AjaxResult success() {
        return success("");
    }

    /**
     * Description: 默认成功带数据
     *
     * @param data 数据
     * @return 异步封装对象
     */
    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(AjaxResultCode.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    /**
     * Description: 默认错误带消息
     *
     * @param message 消息
     * @return 异步封装对象
     */
    public static AjaxResult error(String message) {
        AjaxResult result = new AjaxResult();
        result.setCode(AjaxResultCode.ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * Description: 默认错误无消息
     *
     * @return 异步封装对象
     */
    public static AjaxResult error() {
        return error("");
    }

    /**
     * Description: 默认错误带数据
     *
     * @param data 数据
     * @return 异步封装对象
     */
    public static AjaxResult error(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(AjaxResultCode.ERROR.getCode());
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}