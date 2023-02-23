package com.fastcampus.springbootpractice;


import static org.assertj.core.api.Assertions.assertThat;

import com.fastcampus.springbootpractice.domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class FastcampusSpringBootPracticeApplicationTests {

/**

  private static final Logger logger = LoggerFactory.getLogger(FastcampusSpringBootPracticeApplicationTests.class);

  @Container
  private static final GenericContainer<?> redisContainer
      = new GenericContainer<>(DockerImageName.parse("redis:latest"));

  @BeforeAll
  static void setup() {
    redisContainer.followOutput(new Slf4jLogConsumer(logger)); // Logger 주입하여 컨테이너 내부 관찰
  }


  //  #spring.cache.type=redis
  //  #spring.redis.host=localhost
  //  #spring.redis.port=6379

  @DynamicPropertySource // 컨테이너로 등록하여 동적으로 변하는 설정
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.cache.type", () -> "redis");
    registry.add("spring.redis.port", () -> redisContainer.getMappedPort(6379));
  }

  @Autowired
  private ObjectMapper mapper;

  @Test
  void contextLoads() throws IOException, InterruptedException {
    // Given

    // When
    GenericContainer.ExecResult execResult1 = redisContainer.execInContainer("redis-cli", "get", "student:cassie");
    Student actual = mapper.readValue(execResult1.getStdout(), Student.class);
    System.out.println("Result >> " + execResult1.getStdout());

    // Then
    assertThat(redisContainer.isRunning()).isTrue();
    assertThat(actual).isEqualTo(Student.of("cassie", 18, Student.Grade.A));
  }

  */

}
