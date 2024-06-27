package com.example.demo.studentController;

import com.example.demo.entity.Student;
import com.example.demo.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/allStudent")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();

    }
    @GetMapping("/{id}")
    public Optional<Student> getAStudent(@PathVariable Long id){
        return studentService.getStudentById(id);
    }
    @PostMapping("/addStudent")
    public List<Student> createStudent(@RequestBody List<Student> student){
        return studentService.saveStudent(student);
    }
    @DeleteMapping("/{id}")
    public void  deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
    @PutMapping("/{id}")
    public Student updateAStudent(@PathVariable Long id, @RequestBody Student s){
        return studentService.updateStudent(id,s);
    }
}
