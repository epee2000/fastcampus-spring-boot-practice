package com.fastcampus.springbootpractice.repository;

import com.fastcampus.springbootpractice.domain.Student;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StudentRepository {
  private final Map<String, Student> storage;

  // .computePrefixWith(name -> name + ":") // "student::cassie" --> "student:cassie"
  @Cacheable("student") // student:cassie
  public Student getStudent(String name) { // 병목 현상 예상, cassie
    System.out.println("[repo] 나의 통행료는 무척 비싸다!");
    return storage.get(name); // cassie
  }

  public Student enroll(String name, Integer age, Student.Grade grade) {
    return storage.put(name, Student.of(name, age, grade));
  }

}
