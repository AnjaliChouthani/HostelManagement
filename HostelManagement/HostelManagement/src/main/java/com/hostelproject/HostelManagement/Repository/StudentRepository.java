package com.hostelproject.HostelManagement.Repository;

import com.hostelproject.HostelManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Object> findByName(Student name);
}
