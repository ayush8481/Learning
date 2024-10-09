package com.example.learning.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Student {

    @Id
    Long id;
    String name;
    int age;
    String gender;



}
