package com.example.learning.controller;

import com.example.learning.Entity.Student;
import com.example.learning.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping({"/student","/student/{id}"})
    public ResponseEntity<List<Student>> getStudents(@PathVariable(required = false) Long id,
                                                     @RequestParam(required = false, name="size", defaultValue = "10") Integer size) {
        List<Student> listStudent = studentService.getStudent(id);

        if(size!=0 && listStudent.size() > size)
           return new ResponseEntity<>(listStudent.subList(0,size), HttpStatus.OK);
        else
           return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }

    @PostMapping({"/addStudent"})
    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        Student s = studentService.addStudent(student);
        if(s!=null){
            return new ResponseEntity<>(s,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
       return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.OK);
    }

    @PatchMapping("/updateStudentById")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student){
        return new ResponseEntity<>(studentService.updateStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Student s = studentService.deleteStudent(id);

        if(s!=null){
            return new ResponseEntity<>(s,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
