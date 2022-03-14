package com.springbook.user.sqlservice;

public class DefaultSqlService extends BaseSqlService {
    public DefaultSqlService() {
        setSqlReader(new JaxbXmlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
