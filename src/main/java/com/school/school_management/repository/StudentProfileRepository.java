package com.school.school_management.repository;
//package com.school.school_management.repository;

import com.school.school_management.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> { }
