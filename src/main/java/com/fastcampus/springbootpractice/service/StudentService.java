package com.fastcampus.springbootpractice.service;


import com.fastcampus.springbootpractice.domain.Student;
import com.fastcampus.springbootpractice.repository.StudentRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentService {
  private final StudentRepository studentRepository;

   public void printStudent(String name) {
    Student student = studentRepository.getStudent(name); // cassie
    System.out.println("찾는 학생: " + student);
  }

  @PostConstruct
  public void init() {
    studentRepository.enroll("jack", 15, Student.Grade.B);
    studentRepository.enroll("cassie", 18, Student.Grade.A);
    studentRepository.enroll("fred", 14, Student.Grade.C);
  }

}
