package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.session.Configuration;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
