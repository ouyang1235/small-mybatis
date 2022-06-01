package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
