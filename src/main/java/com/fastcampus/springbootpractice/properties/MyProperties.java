package com.fastcampus.springbootpractice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties("my")
//@Configuration
public class MyProperties {
  private Integer height;

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getHeight() {
    return height;
  }
}



/**
@ConstructorBinding
@ConfigurationProperties("my")
public class MyProperties {
  private final Integer height;

  public MyProperties(Integer height) {
    this.height = height;
  }

  public Integer getHeight() {
    return height;
  }
}
*/