package com.studentDirectory.studentInfo.service;

import com.studentDirectory.studentInfo.entity.Student;
import com.studentDirectory.studentInfo.entity.Teacher;
import com.studentDirectory.studentInfo.exception.ResourceNotFoundException;
import com.studentDirectory.studentInfo.repository.StudentRepo;
import com.studentDirectory.studentInfo.repository.TeacherRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentImpl implements StudentService{

    @Autowired
    private final StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;



    public StudentImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
//        Optional<Student> student= studentRepo.findById(id);
//        if(student.isPresent()){
//            return student.get();
//        }else {
//            throw new EntityNotFoundException("Student Not Found with Id: "+id);
//        }
        return studentRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Student with Id: "+id+" Not found"));
    }

    @Override
    public Student saveStudent(Student student) {
        // Retrieve or save teachers first to avoid transient entity issues
        Set<Teacher> teachers = new HashSet<>();
        for (Teacher teacher : student.getTeachers()) {
            Teacher existingTeacher = teacherRepo.findById(teacher.getId()).orElse(null);
            if (existingTeacher == null) {
                existingTeacher = teacherRepo.save(teacher);
            }
            teachers.add(existingTeacher);
        }
        student.setTeachers(teachers);

        return studentRepo.save(student);
    }


    @Override
    public void deleteStudent(Long id) {
            if (studentRepo.existsById(id)) {
                studentRepo.deleteById(id);
            } else {
                throw new IllegalArgumentException("Student with ID " + id + " does not exist.");
            }
    }

    @Override
    public Student updateStudent(Long id, Student updateStudent) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + id));

        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        student.setEmail(updateStudent.getEmail());

        Set<Teacher> teachers = new HashSet<>();
        for (Teacher teacher : updateStudent.getTeachers()) {
            Teacher existingTeacher = teacherRepo.findById(teacher.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacher.getId()));
            teachers.add(existingTeacher);
        }
        student.setTeachers(teachers);

        final Student updatedStudent = studentRepo.save(student);
        return updatedStudent;
    }


}
