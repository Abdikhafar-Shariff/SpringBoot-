package com.example.demo.studentService;

import com.example.demo.entity.Student;
import com.example.demo.studentRepository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Student entities
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Save a student
     * @param students the entity to save
     * @return the persisted entity
     */

    public  List<Student> saveStudent(List<Student> students){
        for(Student student: students) {
            Optional<Student> findIfEmailExist = studentRepository.
                    findStudentByEmail(student.getEmail());
            if (findIfEmailExist.isPresent()) {
                throw new IllegalArgumentException("Email taken");
            }
        }

        return studentRepository.saveAll(students);
    }

    /**
     * Get all product
     * @return the list of entities
     */

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     * Get one student by id
     * @param id id of the entity
     * @return entity
     */

    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    /**
     * update a product
     * @param id id of the entity
     * @param updatedStudent the updated entity
     * @return the updated entity
     */
    @Transactional
    public Student updateStudent(Long id , Student updatedStudent){
        Optional<Student> existingStudent = studentRepository.findById(id);
        if(existingStudent.isPresent()){
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setDob(updatedStudent.getDob());
            student.setAge(updatedStudent.getAge());
            return studentRepository.save(student);
        }else {
            throw new RuntimeException("Student not found");

        }
    }

    /**
     * Delete a student by id
     * @param id the id of the entity to be deleted
     */

    public void  deleteStudent(Long id){
        boolean exist = studentRepository.existsById(id);
        if(!exist){
            throw new IllegalArgumentException("Student with id "+ id + "does nto exist");
        }
        studentRepository.deleteById(id);
    }
}
