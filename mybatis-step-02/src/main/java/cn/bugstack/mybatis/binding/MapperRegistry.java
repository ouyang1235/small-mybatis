package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 主要作用是将需要的mapper类注册到final map里，此时还没有传入sqlSession，通过getMapper(type,sqlSession)方法来获取mapper实例
 */
public class MapperRegistry {

    private final Map<Class<?>,MapperProxyFactory<?>> knownMapper = new HashMap<>();

    /**
     *  注册器里获取的每一个mapper都是代理对象了
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMapper.get(type);
        if (mapperProxyFactory == null){
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try{
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type){
        /* Mapper必须是接口才会注册 */
        if(type.isInterface()){
            if(hasMapper(type)){
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMapper.put(type,new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMapper.containsKey(type);
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
