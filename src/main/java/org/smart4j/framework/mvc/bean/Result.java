package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.core.bean.BaseBean;

/**
 * 封装返回参数
 */
public class Result  extends BaseBean {
    private static final long serialVersionUID = 5317444799424229600L;

    private boolean success;
    private int error;
    private Object data;

    public Result(boolean success){
        this.success=success;
    }

    public Result error(int err){
        this.error=err;
        return this;
    }

    public Result data(Object data){
        this.data=data;
        return this;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
