package org.smart4j.framework.mvc;

import org.smart4j.framework.mvc.Handler;

/**
 * 处理器映射
 */
public interface HandlerMapping {

    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
