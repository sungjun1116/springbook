package com.springbook.user.sqlservice.updatable;

public class ConCurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{
    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConCurrentHashMapSqlRegistry();
    }
}
