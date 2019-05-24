package org.smart4j.framework.ds.impl;

import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.ds.DataSourceFactory;

import javax.sql.DataSource;

public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {
    protected final String driver= ConfigHelper.getString("smart.framework.jdbc.driver");
    protected final String url=ConfigHelper.getString("smart.framework.jdbc.url");
    protected final String username=ConfigHelper.getString("smart.framework.jdbc.username");
    protected final String password=ConfigHelper.getString("smart.framework.jdbc.password");

    @Override
    public final T  getDataSource() {
        T ds=createDadaSource();
        //设置基础属性
        setDriver(ds,driver);
        setUrl(ds,url);
        setUsername(ds,username);
        setPassword(ds,password);
        //设置高级属性
        setAdvancedConfig(ds);
        return ds;
    }


    public abstract T createDadaSource();
    public abstract void setDriver(T ds,String driver);
    public abstract void setUrl(T ds,String url);
    public abstract void setUsername(T ds,String username);
    public abstract void setPassword(T ds,String password);

    public abstract void setAdvancedConfig(T ds);
}
