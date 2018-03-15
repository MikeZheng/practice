package com.zrich;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zrich.mapper.AccountMapper;
import com.zrich.mapper.AuthorityMapper;
import com.zrich.mapper.CarMapper;
import com.zrich.mapper.DmAttrDomainMapper;
import com.zrich.mapper.DmDeviceAttrMapper;
import com.zrich.mapper.DmDeviceAttrMappingMapper;
import com.zrich.mapper.DmDeviceCategoryMapper;
import com.zrich.mapper.HouseMapper;
import com.zrich.mapper.PushMapper;
import com.zrich.mapper.ResidentMapper;
import com.zrich.mapper.ResourceMapper;
import com.zrich.mapper.RoleMapper;
import com.zrich.mapper.VisitorMapper;
import com.zrich.model.Account;
import com.zrich.model.AccountCriteria;
import com.zrich.model.Authority;
import com.zrich.model.AuthorityCriteria;
import com.zrich.model.Car;
import com.zrich.model.CarCriteria;
import com.zrich.model.DmAttrDomain;
import com.zrich.model.DmAttrDomainCriteria;
import com.zrich.model.DmDeviceAttr;
import com.zrich.model.DmDeviceAttrCriteria;
import com.zrich.model.DmDeviceAttrMapping;
import com.zrich.model.DmDeviceAttrMappingCriteria;
import com.zrich.model.DmDeviceCategory;
import com.zrich.model.DmDeviceCategoryCriteria;
import com.zrich.model.House;
import com.zrich.model.HouseCriteria;
import com.zrich.model.Push;
import com.zrich.model.PushCriteria;
import com.zrich.model.Resident;
import com.zrich.model.ResidentCriteria;
import com.zrich.model.Resource;
import com.zrich.model.ResourceCriteria;
import com.zrich.model.Role;
import com.zrich.model.RoleCriteria;
import com.zrich.model.Visitor;
import com.zrich.model.VisitorCriteria;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetJsonFromDB {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) throws IOException {

    String path = "mybatis-config.xml";
    SqlSessionFactory factory =
            new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(path));

    try (SqlSession session = factory.openSession()) {
      printData(session, AccountMapper.class, Account.class, AccountCriteria.class);
      printData(session, AuthorityMapper.class, Authority.class, AuthorityCriteria.class);
      printData(session, RoleMapper.class, Role.class, RoleCriteria.class);
      printData(session, ResourceMapper.class, Resource.class, ResourceCriteria.class);
      printData(session, HouseMapper.class, House.class, HouseCriteria.class);
      printData(session, CarMapper.class, Car.class, CarCriteria.class);
      printData(session, PushMapper.class, Push.class, PushCriteria.class);
      printData(session, VisitorMapper.class, Visitor.class, VisitorCriteria.class);
      printData(session, ResidentMapper.class, Resident.class, ResidentCriteria.class);
      printData(session, DmDeviceCategoryMapper.class, DmDeviceCategory.class, DmDeviceCategoryCriteria.class);
      printData(session, DmDeviceAttrMapper.class, DmDeviceAttr.class, DmDeviceAttrCriteria.class);
      printData(session, DmAttrDomainMapper.class, DmAttrDomain.class, DmAttrDomainCriteria.class);
      printData(session, DmDeviceAttrMappingMapper.class, DmDeviceAttrMapping.class, DmDeviceAttrMappingCriteria.class);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private static <M, E, C> void printData(SqlSession session, Class<M> mapperClass, Class<E> entityClass, Class<C> criteriaClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, JsonProcessingException {
    M mapper = session.getMapper(mapperClass);
    Method method = mapperClass.getDeclaredMethod("selectByExample", criteriaClass);
    method.setAccessible(true);

    DmDeviceAttrMappingCriteria example = getDmDeviceAttrMappingCriteria();

    List<E> list = (List<E>) method.invoke(mapper, new Object[]{example});
    System.out.println(objectMapper.writeValueAsString(list));
  }

  private static DmDeviceAttrMappingCriteria getDmDeviceAttrMappingCriteria() {
    DmDeviceAttrMappingCriteria example = new DmDeviceAttrMappingCriteria();
    DmDeviceAttrMappingCriteria.Criteria criteria = example.createCriteria();

    criteria.andDeleteFlagEqualTo((short) 1);
    List<String> typeUUIDList = new ArrayList<>();
    typeUUIDList.add("18bd1ee652804e8aa8014325c41106d8");
    typeUUIDList.add("89bc3df3dccd4b0f89bb664bdb8cfaf7");
    typeUUIDList.add("d070f89648dc45698e69a2210d3c65f3");
    typeUUIDList.add("0b6ac6723cd240e5a7cf907b20366276");
    typeUUIDList.add("5cd1538a305646d39849decedfc56581");
    typeUUIDList.add("35b9d717c838424ba4a0e66382235d80");

    criteria.andTypeUuidIn(typeUUIDList);
    return example;
  }

}
