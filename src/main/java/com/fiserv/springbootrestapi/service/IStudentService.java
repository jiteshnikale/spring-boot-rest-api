package com.fiserv.springbootrestapi.service;

import com.fiserv.springbootrestapi.bean.Student;

import java.util.List;

public interface IStudentService {

    List<Student> getStudents();
    Student getStudentById(int id);
    Student saveStudent(Student student);
    Student updateStudent(Student student, int id);
    Student deleteStudent(int id);
}
