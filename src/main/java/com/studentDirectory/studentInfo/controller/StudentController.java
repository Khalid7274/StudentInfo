package com.studentDirectory.studentInfo.controller;

import com.studentDirectory.studentInfo.entity.Student;
import com.studentDirectory.studentInfo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students=studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student=studentService.getStudentById(id);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeStudent(@PathVariable Long id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        //return new ResponseEntity<>(studentService.updateStudent(id,student),HttpStatus.ACCEPTED);
        Student updatedStudent = studentService.updateStudent(id, student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

}
