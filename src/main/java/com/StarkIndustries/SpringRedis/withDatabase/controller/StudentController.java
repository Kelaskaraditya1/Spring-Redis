package com.StarkIndustries.SpringRedis.withDatabase.controller;

import com.StarkIndustries.SpringRedis.withDatabase.model.Student;
import com.StarkIndustries.SpringRedis.withDatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    @Autowired
    public StudentService studentService;

    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        Student student1 = this.studentService.addStudent(student);
        if(student1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(student1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Student!!");
    }

    @GetMapping("/get-student/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable("studentId") Long studentId){
        Student student = this.studentService.getStudentById(studentId);
        if(student!=null)
            return ResponseEntity.status(HttpStatus.OK).body(student);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student doesn't exist");

    }

    @GetMapping("/get-all-students")
    public ResponseEntity<?> getAllStudents(){
        List<Student> studentList = this.studentService.getAllStudents();
        if(!studentList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter Student first!!");
    }

    @DeleteMapping("/delete-student/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId){
        if(this.studentService.deleteStudent(studentId))
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student doesn't exist!!");

    }
}
