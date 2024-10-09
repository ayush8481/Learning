package com.example.learning.Repository;

import com.example.learning.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
