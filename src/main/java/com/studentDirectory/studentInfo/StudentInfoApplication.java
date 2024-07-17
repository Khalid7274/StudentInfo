package com.studentDirectory.studentInfo;

import com.studentDirectory.studentInfo.entity.Student;
import com.studentDirectory.studentInfo.entity.Teacher;
import com.studentDirectory.studentInfo.repository.StudentRepo;
import com.studentDirectory.studentInfo.repository.TeacherRepo;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class StudentInfoApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(StudentInfoApplication.class);
	private final StudentRepo studentRepo;
	private final TeacherRepo teacherRepo;

	@Autowired
    public StudentInfoApplication(StudentRepo studentRepo, TeacherRepo teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }


    public static void main(String[] args) {
		SpringApplication.run(StudentInfoApplication.class, args);
		logger.info("Student Information Apps has Started");
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Teacher teacher1= new Teacher("Rahman","Ali","Rahman@gmail.com");
		Teacher teacher2= new Teacher("Wali","Khan","walid_khan@yahoo.com");

		teacherRepo.save(teacher1);
		teacherRepo.save(teacher2);


		List<Student> studentList = Arrays.asList(
				new Student("Rashid", "Jan", "rashid@yahoo.com", new HashSet<>(Arrays.asList(teacher1))),
				new Student("Zalmi", "Wazir", "zalmi@yahoo.com", new HashSet<>(Arrays.asList(teacher2))),
				new Student("Shams", "Khan", "shams@yahoo.com", new HashSet<>(Arrays.asList(teacher1)))
		);
		studentRepo.saveAll(studentList);
	}
}
