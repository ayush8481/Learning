package com.example.learning.Service;

import com.example.learning.Entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudent(Long id);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public Student deleteStudent(Long id);
}
