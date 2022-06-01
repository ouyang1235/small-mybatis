package cn.bugstack.mybatis.session;

import cn.bugstack.mybatis.builder.xml.XMLConfigBuilder;
import cn.bugstack.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 构建sqlSessionFactory的建造者
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new DefaultSqlSessionFactory(config);
    }

}
