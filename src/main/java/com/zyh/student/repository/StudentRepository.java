package com.zyh.student.repository;

import com.zyh.student.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository <Student,Integer>{

    public Student findByName(String name);


    public Student findByScore(Integer score);

    Page<Student> findByName(String s, Pageable pageable);
}
