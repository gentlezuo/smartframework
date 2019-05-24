package org.smart4j.framework.core.fault;


/**
 * 初始化错误
 */
public class InitializationError extends Error{

    private static final long serialVersionUID=7389625399701944950L;

    public InitializationError(){
        super();
    }

    public InitializationError(String message){
        super(message);
    }

    public InitializationError(String message,Throwable cause){
        super(message,cause);
    }

    public InitializationError(Throwable cause){
        super(cause);
    }

}
