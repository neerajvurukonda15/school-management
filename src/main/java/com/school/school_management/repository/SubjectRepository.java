package com.school.school_management.repository;
//package com.school.schoolmanagement.repository;

import com.school.school_management.model.Student;
import com.school.school_management.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
    //Optional<Subject> findByName(String name);
}
