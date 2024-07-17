package com.studentDirectory.studentInfo.service;

import com.studentDirectory.studentInfo.entity.Student;
import com.studentDirectory.studentInfo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements StudentService{
    private final StudentRepo studentRepo;

    @Autowired
    public StudentImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
