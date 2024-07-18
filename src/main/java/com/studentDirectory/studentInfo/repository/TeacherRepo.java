package com.studentDirectory.studentInfo.repository;

import com.studentDirectory.studentInfo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
