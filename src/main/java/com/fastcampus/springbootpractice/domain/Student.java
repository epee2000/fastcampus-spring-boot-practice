package com.fastcampus.springbootpractice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // redis cache 에서 Jackson2Json 를 사용해서 class를 읽으려면 기본 생성자가 필요하다!
@AllArgsConstructor(staticName = "of") // 전체 생성자에 staticName 를 사용하면 생성자가 private 으로 바뀐다!
public class Student {
  private String name;
  private Integer age;
  private Grade grade;
  public enum Grade {
    A, B, C, D, F
  }

}
