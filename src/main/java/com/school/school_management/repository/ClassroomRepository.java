package com.school.school_management.repository;

//package com.school.schoolmanagement.repository;

import com.school.school_management.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
