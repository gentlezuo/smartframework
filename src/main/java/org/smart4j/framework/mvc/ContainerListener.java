package org.smart4j.framework.mvc;


import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.HelperLoader;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

/**
 * 容器监听器
 */
public class ContainerListener implements ServletContextListener {

    /**
     * 等容器初始化时使用
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext
        ServletContext servletContext=servletContextEvent.getServletContext();
        //初始化Helper类
        HelperLoader.init();
        //添加Servlet映射
        // 添加 Servlet 映射
        addServletMapping(servletContext);

    }

    /**
     * 容器销毁时调用
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void addServletMapping(ServletContext context) {
        // 用 DefaultServlet 映射所有静态资源
        registerDefaultServlet(context);
        // 用 JspServlet 映射所有 JSP 请求
        registerJspServlet(context);
    }

    private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        String wwwPath = FrameworkConstant.WWW_PATH;
        if (StringUtil.isNotEmpty(wwwPath)) {
            defaultServlet.addMapping(wwwPath + "*");
        }
    }

    private void registerJspServlet(ServletContext context) {
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        String jspPath = FrameworkConstant.JSP_PATH;
        if (StringUtil.isNotEmpty(jspPath)) {
            jspServlet.addMapping(jspPath + "*");
        }
    }

}
