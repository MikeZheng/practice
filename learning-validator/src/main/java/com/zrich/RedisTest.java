package com.zrich;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * Hello world!
 */
public class RedisTest {

  static JedisCluster jedisCluster;
  static JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

  public static final String HOST = "192.168.0.170";

  static {
    HostAndPort hostAndPort = new HostAndPort(HOST, 7000);
    HostAndPort hostAndPort1 = new HostAndPort(HOST, 7001);
    HostAndPort hostAndPort2 = new HostAndPort(HOST, 7002);
    HostAndPort hostAndPort3 = new HostAndPort(HOST, 7003);
    HostAndPort hostAndPort4 = new HostAndPort(HOST, 7004);
    HostAndPort hostAndPort5 = new HostAndPort(HOST, 7005);
    Set<HostAndPort> hostAndPorts = new HashSet<>(6);
    hostAndPorts.add(hostAndPort);
    hostAndPorts.add(hostAndPort1);
    hostAndPorts.add(hostAndPort2);
    hostAndPorts.add(hostAndPort3);
    hostAndPorts.add(hostAndPort4);
    hostAndPorts.add(hostAndPort5);

    jedisCluster = new JedisCluster(hostAndPorts);
  }

  public static void main(String[] args) throws UnsupportedEncodingException {
    String token = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiIxMzY0NDA4ODQ4MCIsImV4cCI6MTUxNzYyODkzMH0.derUs5FdwlF8qfuQlwS_hMdJAKdmVdUr7StMXXBaa7dLSkyRCgJ-NUCzbAQMCBR2V8Cyoi4mHns1CmijlcakIuJsOXoOuahbX8rQ2kmGiSRhohPSxOsUHSjktaVB2nsOFcQ850jFe3wBKIy3y6L51SB3NjuAq1SunmICwYMPhug";

    String realKey = "egsc_scp_key_sso_user_" + token;
    byte[] key = serializer.serialize(realKey);
    Object object = jedisCluster.get(key);

    long time = jedisCluster.ttl(key);

    System.out.println("realKey has " + time);
    byte[] bytes = (byte[]) object;
    Object object1 = serializer.deserialize(bytes);
    System.out.println("realKey value is " + object1);

    String yyPhone = "15502646838";

    String verifyCodeKey = getLoginVerifyCodeCountKey(yyPhone);
//        get(verifyCodeKey);
    expire(verifyCodeKey);

    get("AUTH:sso_user_eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiIxMzgxMDMwOTk4NCIsImV4cCI6MTUyMDkxMzQxN30.qLnZuPVTEYlK9nfVK-p9Wxrl3vculqvevMQztcE6zQkylTXeJ-jN_RJwOPyMm-SuHYhVv4lBtWoe9jnlsyFTt3UoncoiksAHvrj0Ps6LNvWIHloT2hz3XUqrPS3IOgEI-rQ9eJYUgSKVlcDyHattRji8gtpWDszfKsDXA02Nhfs");
//        delete("*15502646838*");
//        System.out.println(delete);
//        delete(returnKey);

  }

  private static void delete(Set<String> key) throws UnsupportedEncodingException {
    for (String s : key) {
      jedisCluster.del(s);
      jedisCluster.del(s.getBytes("UTF-8"));
    }
  }


  private static String getLoginVerifyCodeCountKey(String phone) {
    return "OMA:LOGIN.VERIFYCODE.COUNT." + phone;
  }

  private static void expire(String key) {
    jedisCluster.expire(key, 1);
  }

  private static byte[] serializeKey(String realKey) {
    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
    return serializer.serialize(realKey);
  }

  private static Object deserializeKey(byte[] serializedKey) {
    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
    return serializer.deserialize(serializedKey);
  }

  private static void get(String key) {
    Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
    for (String k : clusterNodes.keySet()) {
      System.out.println("Getting value from: " + k);
      JedisPool jp = clusterNodes.get(k);
      try (Jedis connection = jp.getResource()) {
        String value = connection.get(key);
        System.out.println(value);
      } catch (Exception e) {
        System.out.println("Getting value error: " + e);
      } finally {
        System.out.println("Connection closed.");
      }
    }
  }


  private static void delete(String pattern) {
    Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
    for (String k : clusterNodes.keySet()) {
      System.out.println("Getting delete from: " + k);
      JedisPool jp = clusterNodes.get(k);
      try (Jedis connection = jp.getResource()) {
        Set<String> keySet = connection.keys(pattern);
        System.out.println(keySet);
        for (String s : keySet) {
          jedisCluster.del(s);
        }
      } catch (Exception e) {
        System.out.println("Getting delete error: " + e);
      } finally {
        System.out.println("Connection closed.");
      }
    }
  }

  interface Callable {
    Object run(Jedis jedis);
  }
}
