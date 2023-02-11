package com.fiserv.springbootrestapi.service.impl;

import com.fiserv.springbootrestapi.bean.Student;
import com.fiserv.springbootrestapi.service.IStudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private static List<Student> students = new ArrayList<Student>();

    static {
        students.add(new Student(1, "Jitesh", "Nikale"));
        students.add(new Student(2, "Vishal", "Borade"));
        students.add(new Student(3, "Pratik", "Dedhia"));
    }
    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> student = students.stream().filter(s -> s.getId() == id).findFirst();
        if (student.isPresent()) {
            return student.get();
        }

        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        student.setId(students.size() + 1);
        students.add(student);
        return student;
    }

    @Override
    public Student updateStudent(Student student, int id) {
        Optional<Student> optionalStudent = students.stream().filter(x -> x.getId() == id).findFirst();

        if (!optionalStudent.isPresent()) {
            return null;
        }

        Student foundStudent = optionalStudent.get();
        foundStudent.setFirstName(student.getFirstName());
        foundStudent.setLastName(student.getLastName());

        return foundStudent;
    }

    @Override
    public Student deleteStudent(int id) {
        Optional<Student> optionalStudent = students.stream().filter(x -> x.getId() == id).findFirst();

        if (!optionalStudent.isPresent()) {
            return null;
        }

        students.remove(optionalStudent.get());
        return optionalStudent.get();
    }
}
