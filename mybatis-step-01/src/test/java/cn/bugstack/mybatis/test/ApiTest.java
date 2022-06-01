package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.test.dao.IUserDao;
import cn.bugstack.mybatis.binding.MapperProxyFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void  test_MapperProxyFactory(){
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        HashMap<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.bugstack.mybatis.test.dao.IUserDao.queryUserName","模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.bugstack.mybatis.test.dao.IUserDao.queryUserAge","模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10001");
        logger.info("测试结果：{}",res);
    }
}
