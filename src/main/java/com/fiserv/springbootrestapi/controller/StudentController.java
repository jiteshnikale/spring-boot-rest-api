package com.fiserv.springbootrestapi.controller;

import com.fiserv.springbootrestapi.bean.Student;
import com.fiserv.springbootrestapi.exception.ResourceNotFoundException;
import com.fiserv.springbootrestapi.service.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Jitesh",
                "Nikale"
        );
        return ResponseEntity.ok()
                    .header("custom-header", "jitesh")
                    .header("test-header", "test")
                    .body(student);
        //return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    //Spring Boot REST API with Path Variable
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int studentId) {
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            throw new ResourceNotFoundException(String.format("Student for id %d - not found", studentId));
        }

        return ResponseEntity.ok().body(student);
    }

    //Spring Boot REST API with Query Parameter
    @GetMapping("/students/query")
    public ResponseEntity<Student> getStudentByRequestParam(@RequestParam("id") int studentId) {
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            throw new ResourceNotFoundException(String.format("Student for id %d - not found", studentId));
        }

        return ResponseEntity.ok().body(student);
    }

    //Spring Boot REST API that handles POST request
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    //Spring Boot REST API that handles PUT request - update the existing resource
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student, @PathVariable int id) {
        Student updatedStudent = studentService.updateStudent(student, id);

        if (updatedStudent == null) {
            throw new ResourceNotFoundException(String.format("Student not found for id %d", id));
        }
        return ResponseEntity.ok().body(updatedStudent);
    }

    //Spring Boot REST API that handles Deleting request - delete the existing resource
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Student deletedStudent = studentService.deleteStudent(id);

        if (deletedStudent == null) {
            throw new ResourceNotFoundException(String.format("Student not found for id %d", id));
        }
        return ResponseEntity.ok().body(deletedStudent);
    }
}
