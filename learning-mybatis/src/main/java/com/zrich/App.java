package com.zrich;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.security.tools.policytool.Resources_ja;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException {
//        String path="mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(path);
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(inputStream);
//        SqlSession sqlSession = factory.openSession();
//        System.out.println(sqlSession);
//        System.out.println(sqlSession.getConnection().getSchema());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.101:3306/eagle_eye_dev", "root", "123456");
        Statement statement = connection.createStatement();
        System.out.println(statement);

//        sqlSession.getMapper();
    }
}
