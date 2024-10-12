package com.example.learning.Service;

import com.example.learning.Entity.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    public List<Student> getStudent(Long id, Pageable pageable);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public Student deleteStudent(Long id);
}
