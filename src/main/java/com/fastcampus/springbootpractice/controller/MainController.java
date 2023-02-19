package com.fastcampus.springbootpractice.controller;


import com.fastcampus.springbootpractice.service.SortService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  private final SortService sortService;

  public MainController(SortService sortService) {
    this.sortService = sortService;
  }

  @GetMapping("/")
  public String hello(@RequestParam List<String> list) {
    return sortService.doSort(list).toString();
  }


}
