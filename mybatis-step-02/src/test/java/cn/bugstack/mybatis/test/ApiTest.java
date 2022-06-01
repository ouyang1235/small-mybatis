package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.bugstack.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 小傅哥，微信：fustack
 * @description 单元测试
 * @date 2022/3/26
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    /**
     * 整体流程：
     * 先创建注册机，通过扫描包将所有类注册进去
     * 注册机作为参数创建sqlSession工厂
     * 工厂获取sqlSession
     * sqlSession这时不属于任何类
     * sqlSession获取的mapper已经是代理对象（）
     * 代理mapper的方法会走sqlSession的selectOne方法进行查询
     * 猜测还缺少的东西：将xml文件读取进入sqlSession中？
     */
    @Test
    public void test_MapperProxyFactory() {
        //1.创建注册机，注册mappers
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("cn.bugstack.mybatis.test.dao");
        //2.工厂获取sqlSession
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.sqlSession获取被代理的接口(实际交给内置注册机处理)
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //4.调用代理类的查询方法
        String str = userDao.queryUserName("1001");
        logger.info(str);
    }

}
