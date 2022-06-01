package cn.bugstack.mybatis.session;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置类，
 * 包含注册器、和存放映射语句的map
 * 提供注册器的方法、获取映射语句（xml解析出来的属性）的方法
 */
public class Configuration {

    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 存放映射语句的map
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }
}
