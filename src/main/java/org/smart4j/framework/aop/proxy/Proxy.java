package org.smart4j.framework.aop.proxy;

/**
 * 代理接口
 */
public interface Proxy {
    /**
     * 指向链式代理
     * @param proxyChain
     * @return 目标方法返回值
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
