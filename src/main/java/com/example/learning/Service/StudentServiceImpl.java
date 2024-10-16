package com.example.learning.Service;

import com.example.learning.Repository.StudentRepo;
import com.example.learning.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    //List<Student> students;

    public StudentServiceImpl() {
       /* students = new ArrayList<>();
        students.add(new Student(1L, "Ayush", 30, "M"));*/
    }

    @Override
    public List<Student> getStudent(Long id, Pageable pageable) {

        //Adding this block for @AfterThrowing Advice.
        /*try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw e;
        }*/
        if(id!=null) {
           Optional<Student> s =  studentRepo.findById(id);
           if(s.isPresent()){
               return s.stream().toList();
           }
        }
        return studentRepo.findAll(pageable).stream().toList();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentRepo.existsById(student.getId())) {
            return null;
        }
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student deleteStudent(Long id) {
        if (studentRepo.existsById(id)) {
            Student s = studentRepo.findById(id).get();
            studentRepo.deleteById(id);
            return s;
        } else {
            return null;
        }
    }
}
