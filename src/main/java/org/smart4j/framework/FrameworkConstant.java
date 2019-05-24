package org.smart4j.framework;

import org.smart4j.framework.core.ConfigHelper;

/**
 * 提供配置文件的相关常量
 */
public interface FrameworkConstant {


    String UTF_8="UTF-8";
    String CONFIG_PROPS="smart.properties";
    String SQL_PROPS="smart-sql.properties";

    String PLUGIN_PACKAGE="org.smart4j.plugin";




    String JSP_PATH= ConfigHelper.getString("smart.framework.app.jsp_path","/WEB-INF/jsp/");
    String WWW_PATH = ConfigHelper.getString("smart.framework.app.www_path", "/www/");
    String HOME_PAGE = ConfigHelper.getString("smart.framework.app.home_page", "/index.html");
    int UPLOAD_LIMIT = ConfigHelper.getInt("smart.framework.app.upload_limit", 10);

    String PK_NAME = "id";



   /*String JDBC_DRIVER="smart.framework.jdbc.driver";
    String JDBC_URL="smart.framework.jdbc.url";
    String JDBC_USERNAME="smart.framework.jdbc.username";
    String JDBC_PASSWORD="smart.framework.jdbc.password";
    */
    /*//项目基础包名
    String APP_BASE_PACKAGE="smart.framework.app.base_package";
    //jsp路径
    String APP_JSP_PATH="smart.framework.app.jsp_path";
    //静态文件路径
    String APP_ASSET_PATH="smart.framework.app.asset_path";
*/
}
