package com.igeek.ch04.sql;

import com.igeek.pojo.UserCustom;
import com.igeek.pojo.UserQueryVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class UserMapperTest {

    private SqlSessionFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml"));
    }

    @Test
    public void findList() {
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //封装QueryVO对象
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("");  //当前username为""空串或者null，则sql语句不会拼接username条件查询
        UserQueryVO vo = new UserQueryVO();
        vo.setUserCustom(userCustom);

        //执行
        List<UserCustom> list = userMapper.findList(vo);
        for(UserCustom user : list){
            System.out.println(user);
        }

        //关闭资源
        sqlSession.close();

    }

    @Test
    public void findCount() {
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //封装QueryVO对象
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername(null);  //当前username为""空串或者null，则sql语句不会拼接username条件查询
        UserQueryVO vo = new UserQueryVO();
        vo.setUserCustom(userCustom);

        //执行
        Integer count = userMapper.findCounts(vo);
        System.out.println(count);

        //关闭资源
        sqlSession.close();
    }

    @Test
    public void findListByIds(){
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //封装QueryVO对象
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("明");  //当前username为""空串或者null，则sql语句不会拼接username条件查询

        List<Integer> ids = Arrays.asList(15,20,25);

        UserQueryVO vo = new UserQueryVO();
        vo.setUserCustom(userCustom);
        vo.setIds(ids);

        //执行
        List<UserCustom> list = userMapper.findListByIds(vo);
        for(UserCustom user : list){
            System.out.println(user);
        }

        //关闭资源
        sqlSession.close();
    }
}
