package com.example.demo.student;

import java.util.List;
import java.util.Optional;

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

  @GetMapping(path = "/{studentId}")
  public Optional<Student> findStudentById(@PathVariable(value = "studentId") Long studentId){
    return studentService.findStudentById(studentId);
  }

  @PostMapping
  public void addStudent(@RequestBody Student student)
  {
    studentService.addStudent(student);
  }

  @DeleteMapping(path = "/{studentId}")
  public void deleteStudentById(@PathVariable(value = "studentId") Long studentId){
      studentService.deleteStudentById(studentId);
    }
}
