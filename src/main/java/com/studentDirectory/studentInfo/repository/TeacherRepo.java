package com.studentDirectory.studentInfo.repository;

import com.studentDirectory.studentInfo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
