package com.studentDirectory.studentInfo.repository;

import com.studentDirectory.studentInfo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
