package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler异常解析器
 */
public interface HandlerExceptionResolver {
    void resolverHandlerException(HttpServletRequest request, HttpServletResponse response,Exception e);
}
