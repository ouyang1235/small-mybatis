package cn.bugstack.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

public class MapperProxyFactory<T> {
    private  final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 获取实例时，就传入了sqlSession的map，并创建了一个代理对象
     * @param sqlSession
     * @return
     */
    public T newInstance(Map<String,String> sqlSession){
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession,mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
