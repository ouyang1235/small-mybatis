package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.SqlSessionFactoryBuilder;
import cn.bugstack.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserInfoById("10001");
        logger.info("result:{}",res);


    }

}
