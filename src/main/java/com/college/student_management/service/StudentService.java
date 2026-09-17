/*package com.college.student_management.service;

import org.springframework.stereotype.Service;

@Service 
public class StudentService {
        public Student createStudent(Student student) {
          return "Student created successfully";
        }
      }*/


package com.college.student_management.service;
import java.util.List;
import com.college.student_management.entity.Student;
import com.college.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service 
public class StudentService {
        private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

        public List<Student> createStudent(List<Student> student) {
          return studentRepository.saveAll(student);
        }
        public List<Student> getAllStudents(){
          return studentRepository.findAll();
        }

        public Student getStudentById(Long id){
          return studentRepository.findById(id).orElse(null);
        }

        public Student updateStudent(Long id,Student student){
          Student existingStudent=studentRepository.findById(id).orElse(null);
          if(existingStudent==null){
            return null;
          }
          existingStudent.setName(student.getName());
          existingStudent.setEmail(student.getEmail());
          existingStudent.setDepartment(student.getDepartment());
          return studentRepository.save(existingStudent);
        }
        public void deleteStudent(Long id){
          studentRepository.deleteById(id);
        }
}


