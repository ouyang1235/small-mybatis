package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.session.Configuration;
import cn.bugstack.mybatis.session.SqlSession;
/**
 * 作用:给一个id标识，和参数，输出查询结果
 * 包含一个配置类，通过根据id获取配置类中的映射语句，拼接真实执行的sql，进行查询
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }


}
