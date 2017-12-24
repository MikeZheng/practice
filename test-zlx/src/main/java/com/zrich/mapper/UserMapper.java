/**
 * @Probject Name: test-zlx
 * @Path: com.zrich.mapperUserMapper.java
 * @Create By zhengzhenfu
 * @Create In 2017年12月24日 下午4:37:24
 * User Mapper to handle orm 
 */
package com.zrich.mapper;

import com.zrich.model.UserDO;
import com.zrich.model.User;
import com.zrich.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Class Name UserMapper
 * @Author zhengzhenfu
 * @Create In 2017年12月24日 下午4:37:24
 */
public interface UserMapper {
  
  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int countByExample(UserExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int deleteByExample(UserExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int insert(User record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int insertSelective(User record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  List<User> selectByExample(UserExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  User selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int updateByPrimaryKeySelective(User record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table public.user
   * @mbggenerated  Sun Dec 24 17:17:15 CST 2017
   */
  int updateByPrimaryKey(User record);

 
  

}
