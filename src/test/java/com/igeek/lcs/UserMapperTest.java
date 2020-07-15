package com.igeek.lcs;

import com.igeek.pojo.OrdersCustom;
import com.igeek.pojo.User;
import com.igeek.pojo.UserCustom;
import com.igeek.pojo.UserQueryVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class UserMapperTest {

    //关联会话工厂对象
    private SqlSessionFactory factory;

    //在启动测试方法之前做一些准备工作
    @Before
    public void setUp() throws Exception {
        //加载mybatis的核心配置文件，产生流对象
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建会话工厂对象
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void update() throws ParseException {
        //创建会话对象
        SqlSession sqlSession = factory.openSession();
        //获得代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //封装User对象
        String str = "2019-09-09";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        User user = new User(27,"马汉","1",date,"江苏");

        //执行插入操作
        userMapper.update(user);
        System.out.println(user);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }

    @Test
    public void delete() throws ParseException {
        //创建会话对象
        SqlSession sqlSession = factory.openSession();
        //获得代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //执行插入操作
        userMapper.delete(27);
        //提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }

    @Test
    public void findByNameAndSex() throws ParseException {
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //封装QueryVO对象
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("马汉");  //当前username为""空串或者null，则sql语句不会拼接username条件查询

        List<Integer> ids = Arrays.asList(15,20,25);

        UserQueryVO vo = new UserQueryVO();
        vo.setUserCustom(userCustom);
        vo.setIds(ids);

        //执行
        List<UserCustom> list = userMapper.findByNameAndSex(vo);
        for(UserCustom user : list){
            System.out.println(user);
        }

        //关闭资源
        sqlSession.close();
    }

    @Test
    public void find() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<OrdersCustom> list = mapper.find();
        for (OrdersCustom o : list) {
            System.out.println(o);
        }
        sqlSession.close();
    }


    @Test
    public void find1() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<OrdersCustom> list = mapper.find1();
        for (OrdersCustom o : list) {
            System.out.println(o);
        }
        sqlSession.close();
    }
}
