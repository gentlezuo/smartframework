package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用器
 */
public interface HandlerInvoker {

    void invokeHandler(HttpServletRequest request, HttpServletResponse response,Handler handler) throws Exception;
}
