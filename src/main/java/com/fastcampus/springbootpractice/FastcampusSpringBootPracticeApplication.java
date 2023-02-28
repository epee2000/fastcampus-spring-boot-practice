package com.fastcampus.springbootpractice;

import com.fastcampus.springbootpractice.properties.MyProperties;
import com.fastcampus.springbootpractice.service.StudentService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;


// ### https://github.com/djkeh/fastcampus-spring-boot-practice ### ///


@EnableConfigServer // spring.cloud.config.server.git.uri, http://localhost:8888/config/test/develop
//@EnableCaching // Cache 적용은 모든 bean 들이 로딩이 된 뒤에 활성화가 된다.
@ConfigurationPropertiesScan // @Configuration 생략 대신 선언
@SpringBootApplication
public class FastcampusSpringBootPracticeApplication {
  private final Integer height;
  private final Environment environment;
  private final ApplicationContext applicationContext;
  private final MyProperties myProperties; // @Bean 참조
  private final StudentService studentService;

  public FastcampusSpringBootPracticeApplication(
      @Value("${my.height}") Integer height,
      Environment environment,
      ApplicationContext applicationContext,
      MyProperties myProperties, StudentService studentService) {
    this.height = height;
    this.environment = environment;
    this.applicationContext = applicationContext;
    this.myProperties = myProperties;
    this.studentService = studentService;
  }

  public static void main(String[] args) {
    SpringApplication.run(FastcampusSpringBootPracticeApplication.class, args);
  }

  /**
   * @PostConstruct
   * [호출 순서] > 해당 어노테이션은 다른 리소스에서 호출되지 않아도 수행
   *
   * 생성자 호출
   * 의존성 주입 완료 (@Autowired || @RequiredArgsConstructor )
   * @PostConstruct
   */

  //@PostConstruct // 해당 class 내 모든 의존성(주입)이 완성된 후, --> Cache 적용은 모든 bean 들이 로딩이 된 뒤에 활성화가 된다.
  @EventListener(ApplicationReadyEvent.class) // 모든 bean 이 다 로드되고 스프링 컨테이너 준비 완료 된 후, 즉 Application 준비가 끝났을때... 이벤트
  public void init() { // 3번 호출...
    studentService.printStudent("jack");
    studentService.printStudent("jack");
    studentService.printStudent("jack");
    studentService.printStudent("fred");
    studentService.printStudent("cassie");
    studentService.printStudent("cassie");
  }

  //@PostConstruct
  //public void init() {
  //  System.out.println("@Value = " + height);
  //  System.out.println("environment = " + environment.getProperty("my.height"));
  //  System.out.println("applicationContext = " + applicationContext.getEnvironment().getProperty("my.height"));
  //  System.out.println("configurationProperties = " + myProperties.getHeight());
  //}

 // @ConfigurationProperties("my")
 // @Bean
 // public MyProperties myProperties() {
 //   return new MyProperties();
 // }

}
