package com.school.school_management.repository;
//package com.school.schoolmanagement.repository;

import com.school.school_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
