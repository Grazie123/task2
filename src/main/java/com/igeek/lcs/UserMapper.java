package com.igeek.lcs;

import com.igeek.pojo.OrdersCustom;
import com.igeek.pojo.User;
import com.igeek.pojo.UserCustom;
import com.igeek.pojo.UserQueryVO;

import java.util.List;

/**
 * Mapper接口与Mapper映射文件遵循规范，才可以为Mapper接口生成代理对象
 * 1.Mapper接口与Mapper映射文件，同名且在同包下
 * 2.Mapper映射文件中namespace的属性，必须是Mapper接口的全类名
 * 3.Mapper接口中的方法名必须与Mapper映射文件中的statement语句的id一致
 * 4.Mapper接口中的方法的形式参数类型必须与Mapper映射文件中的statement语句的parameterType指定的类型一致
 * 5.Mapper接口中的方法的返回值类型必须与Mapper映射文件中的statement语句的resultType指定的类型一致
 * 6.在mybatis的核心配置文件中，加载Mapper映射文件
 */
public interface UserMapper {

    //修改用户
    public void update(User user);

    public void delete(Integer id);

    public List<UserCustom>  findByNameAndSex(UserQueryVO vo);

    //查询订单及订单明细的信息
    public List<OrdersCustom> find();

    //查询订单信息，关联查询创建订单的用户信息
    public List<OrdersCustom> find1();
}
