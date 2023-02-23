package com.fastcampus.springbootpractice.config;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
  /**
   * public class Student implements Serializable {...}
   * --> java 직렬화, 모양이 이쁘지않다.
   * --> json 직렬화로 변경할 필요가 있다. 그 설정은 Config 추가로 수동 설정으로 해야된다. 아래와 같이
   */

  @Bean
  public RedisCacheConfiguration redisCacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .computePrefixWith(name -> name + ":") // "student::cassie" --> "student:cassie"
        .entryTtl(Duration.ofSeconds(10))
        .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // new StringRedisSerializer()
  }

  //String keyPrefix = String.format("%s:%s:","identity", "sellerfront");
  //.computePrefixWith(cacheName -> keyPrefix + cacheName)

}


/**
 * spring:
 *   cache:
 *     type: redis
 *   redis:
 *     cluster:
 *       nodes[0]: apitkndbs.gmarket.co.kr:6379
 *       nodes[1]: apitkndbm.gmarket.co.kr:6379
 *     host: apitkndbm.gmarket.co.kr # apitkndbm.gmarket.co.kr
 *     port: 6379
 *     database: 0
 */

/** RedisConfig.java

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
    import org.springframework.cache.CacheManager;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Primary;
    import org.springframework.data.redis.cache.RedisCacheConfiguration;
    import org.springframework.data.redis.cache.RedisCacheManager;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.serializer.RedisSerializationContext;
    import org.springframework.data.redis.serializer.StringRedisSerializer;

    import java.time.Duration;
    import java.util.HashMap;
    import java.util.Map;

@Configuration
public class CacheConfig {

  @Autowired
  RedisConnectionFactory redisConnectionFactory;

  //리모트 캐시
  @Bean
  @Primary
  @ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis", matchIfMissing = false)
  public CacheManager redisCacheManager() {
    return RedisCacheManager.builder(redisConnectionFactory)
        .cacheDefaults(defaultRedisCacheConfiguration())
        .withInitialCacheConfigurations(redisConfigurationMap())
        .build();
  }

  private RedisCacheConfiguration defaultRedisCacheConfiguration() {
    //TODO : 업데이트
    String keyPrefix = String.format("%s:%s:","identity", "sellerfront");
    return RedisCacheConfiguration.defaultCacheConfig()
        .computePrefixWith(cacheName -> keyPrefix + cacheName)
        .serializeValuesWith(RedisSerializationContext.SerializationPair
            .fromSerializer(new StringRedisSerializer()));
  }

  private Map<String, RedisCacheConfiguration> redisConfigurationMap() {
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
    cacheConfigurations.put("form", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(1)));
    cacheConfigurations.put("bankAuth", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(1)));
    cacheConfigurations.put("nation", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(7)));
    cacheConfigurations.put("shutterman", defaultRedisCacheConfiguration().entryTtl(Duration.ofHours(1)));
    return cacheConfigurations;
  }
}
*/

/** CacheConfig.java

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
    import org.springframework.cache.CacheManager;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Primary;
    import org.springframework.data.redis.cache.RedisCacheConfiguration;
    import org.springframework.data.redis.cache.RedisCacheManager;
    import org.springframework.data.redis.connection.RedisConnectionFactory;
    import org.springframework.data.redis.serializer.RedisSerializationContext;
    import org.springframework.data.redis.serializer.StringRedisSerializer;

    import java.time.Duration;
    import java.util.HashMap;
    import java.util.Map;

@Configuration
public class CacheConfig {

  @Autowired
  RedisConnectionFactory redisConnectionFactory;

  // 리모트 캐시 //
  @Bean
  @Primary
  @ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis", matchIfMissing = false)
  public CacheManager redisCacheManager() {
    return RedisCacheManager.builder(redisConnectionFactory)
        .cacheDefaults(defaultRedisCacheConfiguration())
        .withInitialCacheConfigurations(redisConfigurationMap())
        .build();
  }

  private RedisCacheConfiguration defaultRedisCacheConfiguration() {
    //TODO : 업데이트
    String keyPrefix = String.format("%s:%s:","identity", "sellerfront");
    return RedisCacheConfiguration.defaultCacheConfig()
        .computePrefixWith(cacheName -> keyPrefix + cacheName)
        .serializeValuesWith(RedisSerializationContext.SerializationPair
            .fromSerializer(new StringRedisSerializer()));
  }

  private Map<String, RedisCacheConfiguration> redisConfigurationMap() {
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
    cacheConfigurations.put("form", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(1)));
    cacheConfigurations.put("bankAuth", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(1)));
    cacheConfigurations.put("nation", defaultRedisCacheConfiguration().entryTtl(Duration.ofDays(7)));
    cacheConfigurations.put("shutterman", defaultRedisCacheConfiguration().entryTtl(Duration.ofHours(1)));
    return cacheConfigurations;
  }

}

*/




