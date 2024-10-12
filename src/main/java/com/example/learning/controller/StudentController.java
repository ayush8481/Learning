package com.example.learning.controller;

import com.example.learning.Entity.Student;
import com.example.learning.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping({"/getStudent","/getStudent/{id}"})
    public ResponseEntity<List<Student>> getStudents(@PathVariable(required = false) Long id,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "0") int direction){

        Pageable pageable = getPageable(page, size, sortBy, direction);
        try{
            return new ResponseEntity<>(studentService.getStudent(id,pageable), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static Pageable getPageable(int page, int size, String sortBy, int direction) {
        Sort sort = direction == 0 ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return pageable;
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
