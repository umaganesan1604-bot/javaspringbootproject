/*package com.college.student_management.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController  
public class StudentController {
@GetMapping("/students")
       public String getStudents() {
           return "List of Students";
       }
       
        @GetMapping("/add")
        public int getParameters() {
                    int a=9;
                    int b=1;
                    return a+b;
        }

                @PostMapping("/hello")
                public String hello(){
                    return "Hello World!";
                }
                 @PostMapping("/sub")
                public int sub(){
                    int a=7;
                    int b=1;
                    return a-b;
                }*/
/*package com.college.student_management.controller;
import org.springframework.web.bind.annotation.RestController;
import com.college.student_management.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController  
public class StudentController {
        private final StudentService studentService;
         //dependency injection 
        public StudentController(StudentService studentService){
        this.studentService=studentService;
        }
        @GetMapping("/hello")
        public String hello() {
            return "Successful";
        }
        @PostMapping("/students")
        public String createStudent(){
            return studentService.createStudent();
        }
                
                }
 */


//DAY-2
package com.college.student_management.controller;
import com.college.student_management.entity.Student;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.college.student_management.service.StudentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController  
public class StudentController {
        private final StudentService studentService;
         //dependency injection 
        public StudentController(StudentService studentService){
        this.studentService=studentService;
        }
        @PostMapping("/students")
        public List<Student> createStudent(@RequestBody List<Student> student){
            return studentService.createStudent(student);
        }

        @GetMapping("/students")
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }
        
        @GetMapping("/students/{id}")
        public Student getStudentById(@PathVariable Long id){
            return studentService.getStudentById(id);
        }
        
        @PutMapping("students/{id}")
        public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
            
            
            return studentService.updateStudent(id,student);
        }
        
        @DeleteMapping("/students/{id}") 
        public void deleteStudent(@PathVariable Long id){
            studentService.deleteStudent(id);
        }

        @GetMapping("/")
        public String home() {
            return "index";
        }
        @GetMapping("/add-student")
            public String addStudentPage(){
                return "add-student";
        }
        @GetMapping("/students-page")
        public String studentPage(){
            return "students";
        }
        
                
}
                

