package com.zrich;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zrich.mapper.UserMapper;
import com.zrich.model.User;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException {

    String path = "mybatis-config.xml";
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(path));

    try (SqlSession session = factory.openSession()) {
      UserMapper userMapper = session.getMapper(UserMapper.class);
      User user = new User();
      user.setId(3);
      user.setName("David");
      user.setAddress("广东省深圳市");
      user.setEmail("123@123.com");
      user.setGender("M");
      userMapper.insert(user);
      session.commit();
    }

    /*
     * Connection connection=null; try { Class.forName("org.postgresql.Driver"); connection =
     * DriverManager.getConnection("jdbc:postgresql://localhost:5432/test"); } catch (Exception e) {
     * e.printStackTrace(); }
     */


  }


}
