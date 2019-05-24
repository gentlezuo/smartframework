package org.smart4j.framework.ds;

import javax.sql.DataSource;

public interface DataSourceFactory {

    /**
     * 获取数据源
     */
    DataSource getDataSource();
}
