package com.StarkIndustries.SpringRedis.withDatabase.service;

import com.StarkIndustries.SpringRedis.withDatabase.model.Student;
import com.StarkIndustries.SpringRedis.withDatabase.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    /* To integrate redis with Database:
     *   1) add @EnableCaching annotation to SpringBootApplication class
     *   2) in Service Layer:
     *           1) add  @Cacheable(value="name_of_object",key="#id/on the basis of value will be fetched" to the method which fetches a single object.
     *           2) add  @CachePut(value="name_of_object",key="#id/on the basis of value will be fetched" to the method which adds object to the database.
     *           3) add  @CacheEvict(value="name_of_object",key="#id/on the basis of value will be fetched" to the method which deletes the object from database.
     *
     *           Once perform all operation than the data fetched by it is automatically saved in redis.
     *           and is it is deleted from database simultaniously it is deleted from redis as well.
     *
     *           <Most Important>: the accessing of key attribute in the above annotation's is very important.
     *           1) if single String or any value foreg:studentId than key="#studentID"
     *           2) if an Object that contains the key for eg student--->studentId than key="#student.studentId"
     * */

    @Autowired
    public StudentRepository studentRepository;

    @CachePut(value = "student",key = "#student.studentId")
    public Student addStudent(Student student){
        student.setLocalDateTime(LocalDateTime.now());
        return this.studentRepository.save(student);
    }

    @Cacheable(value = "student",key = "#studentId")
    public Student getStudentById(Long studentId){
        return this.studentRepository.findById(studentId).get();
    }

    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }

    @CacheEvict(value = "student",key = "#studentId")
    public boolean deleteStudent(Long studentId){
        if(this.studentRepository.existsById(studentId)){
            this.studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }

}
