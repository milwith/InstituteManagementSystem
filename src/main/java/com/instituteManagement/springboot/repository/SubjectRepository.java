package com.instituteManagement.springboot.repository;

import com.instituteManagement.springboot.model.Subject;
import com.instituteManagement.springboot.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
