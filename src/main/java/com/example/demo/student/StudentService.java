package com.example.demo.student;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService
{

  private final StudentRepository studentRepository;

  public List<Student> getAllStudents()
  {
    return studentRepository.findAll();
  }

  public void addStudent(Student student)
  {
    //TODO: validation checking
    studentRepository.save(student);
  }
}
