package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import com.example.demo.student.exception.BadRequestException;
import com.example.demo.student.exception.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService
{

  private final StudentRepository studentRepository;

  private static Logger log = LoggerFactory.getLogger(StudentService.class);

  public List<Student> getAllStudents()
  {
    return studentRepository.findAll();
  }

  public Optional<Student> findStudentById(Long studentId){
    ifStudentExists(studentId);
    return studentRepository.findById(studentId);
  }

  public void addStudent(Student student)
  {
    boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
    if (existsEmail){
      throw new BadRequestException(
              "Email " + student.getEmail() + " is already taken"
      );
    }
    studentRepository.save(student);
  }

  public void deleteStudentById(Long studentId){
    ifStudentExists(studentId);
    studentRepository.deleteById(studentId);
  }

  private void ifStudentExists(Long studentId) {
    if (!studentRepository.existsById(studentId)){
      throw new StudentNotFoundException(
              "Student with id: " + studentId + " does not exists"
      );
    }
  }
}
