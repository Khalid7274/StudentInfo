package com.studentDirectory.studentInfo.service;

import com.studentDirectory.studentInfo.entity.Student;
import com.studentDirectory.studentInfo.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student= studentRepo.findById(id);
        if(student.isPresent()){
            return student.get();
        }else {
            throw new EntityNotFoundException("Student Not Found with Id: "+id);
        }
    }
}
