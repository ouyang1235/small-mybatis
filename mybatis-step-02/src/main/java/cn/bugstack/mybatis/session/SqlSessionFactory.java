package cn.bugstack.mybatis.session;

/**
 * 提供外界访问获取sqlSession的通道
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
