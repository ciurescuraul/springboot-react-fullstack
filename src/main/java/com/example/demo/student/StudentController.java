package com.example.demo.student;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/students")
public class StudentController
{

  private final StudentService studentService;

  @GetMapping
  public List<Student> getAllStudents()
  {
    return studentService.getAllStudents();
  }

  @PostMapping
  public void addStudent(@RequestBody Student student)
  {
    studentService.addStudent(student);
  }
}
