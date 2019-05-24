package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图解析器
 */
public interface ViewResolver {
    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);
}
