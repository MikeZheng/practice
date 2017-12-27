package com.zrich;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zrich.mapper.UserMapper;
import com.zrich.model.User;
import com.zrich.model.UserExample;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException {

    String path = "mybatis-config.xml";
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(path));

    try (SqlSession session = factory.openSession()) {
      UserMapper userMapper = session.getMapper(UserMapper.class);
//      User user = new User();
//      user.setId(3);
//      user.setName("David");
//      user.setAddress("广东省深圳市");
//      user.setEmail("123@123.com");
//      user.setGender("M");
//      userMapper.insert(user);
      UserExample example = new UserExample();
      System.out.println(userMapper.countByExample(example ));
      session.commit();
    }

    /*
     * Connection connection=null; try { Class.forName("org.postgresql.Driver"); connection =
     * DriverManager.getConnection("jdbc:postgresql://localhost:5432/test"); } catch (Exception e) {
     * e.printStackTrace(); }
     */

    Logger logger = LogManager.getLogger("mylog");
    logger.debug("debug");
    logger.error("error");
    logger.info("info");
    logger.trace("trace");
    logger.warn("warn");
    logger.fatal("fatal");
    
    for(int i = 0; i < 50000; i++) {  
      logger.trace("trace level");  
      logger.debug("debug level");  
      logger.info("info level");  
      logger.warn("warn level");  
      logger.error("error level");  
      logger.fatal("fatal level");  
  }  
  try {  
      Thread.sleep(1000 * 61);  
  } catch (InterruptedException e) {}  
  logger.trace("trace level");  
  logger.debug("debug level");  
  logger.info("info level");  
  logger.warn("warn level");  
  logger.error("error level");  
  logger.fatal("fatal level");  
    
  }
  


}
