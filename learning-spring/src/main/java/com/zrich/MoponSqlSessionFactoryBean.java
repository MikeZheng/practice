package com.zrich;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;

/**
 * Created by Administrator on 2017/8/15.
 */
public class MoponSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        try {
            return super.buildSqlSessionFactory();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace(); // XML 有错误时打印异常。
            throw new NestedIOException("Failed to parse mapping resource: " + e.getMessage() , e.getCause());
        } finally {
            ErrorContext.instance().reset();
        }
    }
}
