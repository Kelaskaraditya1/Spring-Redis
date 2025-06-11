package com.StarkIndustries.SpringRedis.withDatabase.repository;

import com.StarkIndustries.SpringRedis.withDatabase.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
