package com.erillamhc.whm.services.vo.commons;

/**
 * VO da formato de respuesta hacia el cliente.
 * @author Ivo Danic G
 * @company Erillam Health Care
 * @version 1.0
 * @param <T>
 * @since 1.0
 */
public class ResponseVO<T> {

    private Integer code;
    private String status;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}