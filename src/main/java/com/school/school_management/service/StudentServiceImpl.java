package com.school.school_management.service;

import com.school.school_management.model.Classroom;
import com.school.school_management.model.Student;
import com.school.school_management.model.Subject;
import com.school.school_management.repository.ClassroomRepository;
import com.school.school_management.repository.StudentProfileRepository;
import com.school.school_management.repository.StudentRepository;
import com.school.school_management.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private StudentProfileRepository studentProfileRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student saveStudent(Student student) {
        // Ensure StudentProfile is linked correctly
        if (student.getStudentProfile() != null) {
            student.getStudentProfile().setStudent(student);
        }

        // Handle Classroom logic (Insert New Classroom from JSON)
        if (student.getClassroom() != null) {
            Classroom classroom = student.getClassroom();

            // If no ID is provided, insert a new classroom
            if (classroom.getId() == null) {
                classroom = classroomRepository.save(classroom);
            }

            student.setClassroom(classroom);
            classroom.getStudents().add(student); //  Ensure bidirectional mapping
        }

        // Handle Subjects (Insert New Subjects from JSON)
        if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
            List<Subject> subjectList = new ArrayList<>();

            for (Subject subject : student.getSubjects()) {
                if (subject.getId() == null) {
                    // If subject is new, save it
                    subject = subjectRepository.save(subject);
                } else {
                    // If subject exists, fetch it
                    subject = subjectRepository.findById(subject.getId()).orElse(subject);
                }
                subject.getStudents().add(student); //  Ensure bidirectional mapping
                subjectList.add(subject);
            }

            student.setSubjects(subjectList);
        }

        return studentRepository.save(student);
    }
    

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
