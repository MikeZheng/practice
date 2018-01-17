package com.zrich;

import java.io.IOException;
import java.util.List;

import com.eg.egsc.egc.mdm.component.devicecategory.mapper.DmDeviceCategoryMapper;
import com.eg.egsc.egc.mdm.component.devicecategory.mapper.entity.DmDeviceCategory;
import com.eg.egsc.egc.mdm.component.devicecategory.mapper.entity.DmDeviceCategoryCriteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 */
public class GetJsonFromDB {
    public static void main(String[] args) throws IOException {

        String path = "mybatis-config.xml";
        SqlSessionFactory factory =
                new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(path));

        try (SqlSession session = factory.openSession()) {
            ObjectMapper mapper = new ObjectMapper();

            DmDeviceCategoryCriteria categoryCriteria = new DmDeviceCategoryCriteria();
            DmDeviceCategoryCriteria.Criteria criteria = categoryCriteria.createCriteria();
            criteria.andTypeCodeEqualTo("1001");

            List<DmDeviceCategory> categoryList = session.getMapper(DmDeviceCategoryMapper.class).selectByExample(categoryCriteria);
            System.out.println(mapper.writeValueAsString(categoryList));

//            List<DmDeviceAttr> dmDeviceAttrs = session.getMapper(DmDeviceAttrMapper.class).selectByExample(null);
//            System.out.println(mapper.writeValueAsString(dmDeviceAttrs));
//
//            List<DmAttrDomain> dmAttrDomains = session.getMapper(DmAttrDomainMapper.class).selectByExample(null);
//            System.out.println(mapper.writeValueAsString(dmAttrDomains));
//
//            List<DmDeviceAttrMapping> dmDeviceAttrMappings = session.getMapper(DmDeviceAttrMappingMapper.class).selectByExample(null);
//            System.out.println(mapper.writeValueAsString(dmDeviceAttrMappings));
//
//            List<DmProvider> dmProviders = session.getMapper(DmProviderMapper.class).selectByExample(null);
//            System.out.println(mapper.writeValueAsString(dmProviders));
        }

//        for (int i = 0; i < 1000; i++) {
//            System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        }



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

    /*for (int i = 0; i < 50000; i++) {
      logger.trace("trace level");
      logger.debug("debug level");
      logger.info("info level");
      logger.warn("warn level");
      logger.error("error level");
      logger.fatal("fatal level");
    }
    try {
      Thread.sleep(1000 * 61);
    } catch (InterruptedException e) {
    }
    logger.trace("trace level");
    logger.debug("debug level");
    logger.info("info level");
    logger.warn("warn level");
    logger.error("error level");
    logger.fatal("fatal level");*/

    }


}
