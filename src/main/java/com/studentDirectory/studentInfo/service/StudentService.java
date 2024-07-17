package com.studentDirectory.studentInfo.service;

import com.studentDirectory.studentInfo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
}
