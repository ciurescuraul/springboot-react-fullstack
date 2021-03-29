package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService
{

  private final StudentRepository studentRepository;

  public List<Student> getAllStudents()
  {
    return studentRepository.findAll();
  }

  public Optional<Student> findStudentById(Long studentId){
    //TODO: check if student exists
    return studentRepository.findById(studentId);
  }

  public void addStudent(Student student)
  {
    //TODO: check if email is taken
    studentRepository.save(student);
  }

  public void deleteStudentById(Long studentId){
    //TODO: check if student exists
    studentRepository.deleteById(studentId);
  }
}
