package com.cntomorrow.modules.untils;


import java.io.Serializable;
import java.util.Optional;

/**
 * REST API 返回结果
 *
 * @author hubin
 * @since 2018-06-05
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long code;
    private T data;
    private String msg;

    public R() {
    }

    public R(IErrorCode errorCode) {
        errorCode = (IErrorCode)Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> R<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }

        return restResult(data, aec);
    }

    public static <T> R<T> failed(String msg) {
        return (R<T>) restResult((Object)null, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> failed(long code, String msg) {
        return (R<T>) restResult((Object)null, code, msg);
    }

    public static <T> R<T> failed(IErrorCode errorCode) {
        return (R<T>) restResult((Object)null, errorCode);
    }

    public static <T> R<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> apiResult = new R();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean getOk() {
        return ApiErrorCode.SUCCESS.getCode() == this.code;
    }

    public long getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public R<T> setCode(long code) {
        this.code = code;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof R)) {
            return false;
        } else {
            R<?> other = (R)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getCode() != other.getCode()) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof R;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int resulta = 1;
        long $code = this.getCode();
        int result = resulta * 59 + (int)($code >>> 32 ^ $code);
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        return result;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }
}