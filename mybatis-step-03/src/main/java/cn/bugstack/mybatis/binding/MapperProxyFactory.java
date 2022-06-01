package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 以类（表）为区分单位
 * @param <T>
 */
public class MapperProxyFactory<T> {

    private  final Class<T> mapperInterface;

    /**
     * 以方法为单位缓存SqlCommand
     * 作用:复用MapperMethod，不用每次都创建
     */
    private Map<Method,MapperMethod> methodCache = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Map<Method,MapperMethod> getMethodCache(){
        return methodCache;
    }

    public T newInstance(SqlSession sqlSession){
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession,mapperInterface,methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
