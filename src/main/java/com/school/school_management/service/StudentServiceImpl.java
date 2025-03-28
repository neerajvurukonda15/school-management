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

    private final StudentRepository studentRepository;

    private final StudentProfileRepository studentProfileRepository;

    private final ClassroomRepository classroomRepository;

    private final SubjectRepository subjectRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentProfileRepository studentProfileRepository, ClassroomRepository classroomRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
    }

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
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }



    //3 jsonproperties()
//    @Override
//    public Student saveStudent(Student student) {
//        if (student.getStudentProfile() != null) {
//            student.getStudentProfile().setStudent(student);
//        }
//
//        // Handle Classroom
//        if (student.getClassroom() != null) {
//            Classroom classroom = student.getClassroom();
//            if (classroom.getId() == null) {
//                classroom = classroomRepository.save(classroom);
//            }
//            student.setClassroom(classroom);
//            classroom.getStudents().add(student);
//        }
//
//        // Handle Subjects
//        if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
//            List<Subject> subjectList = new ArrayList<>();
//
//            for (Subject subject : student.getSubjects()) {
//                if (subject.getId() == null) {
//                    subject = subjectRepository.save(subject);
//                } else {
//                    subject = subjectRepository.findById(subject.getId()).orElse(subject);
//                }
//
//                // Ensure bidirectional mapping
//                if (!subject.getStudents().contains(student)) {
//                    subject.getStudents().add(student);
//                }
//
//                subjectList.add(subject);
//            }
//
//            student.setSubjects(subjectList);
//        }
//
//        return studentRepository.save(student);
//    }
//
//

//2
//    @Override
//    public Student saveStudent(Student student) {
//        // Ensure StudentProfile is linked correctly
//        if (student.getStudentProfile() != null) {
//            student.getStudentProfile().setStudent(student);
//        }
//
//        // Handle Classroom logic (Insert New Classroom from JSON)
//        if (student.getClassroom() != null) {
//            Classroom classroom = student.getClassroom();
//
//            // If no ID is provided, insert a new classroom
//            if (classroom.getId() == null) {
//                classroom = classroomRepository.save(classroom);
//            }
//
//            student.setClassroom(classroom);
//            classroom.getStudents().add(student); //  Ensure bidirectional mapping
//        }
//
//        // Handle Subjects (Insert New Subjects from JSON)
//        if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
//            List<Subject> subjectList = new ArrayList<>();
//
//            for (Subject subject : student.getSubjects()) {
//                if (subject.getId() == null) {
//                    // If subject is new, save it
//                    subject = subjectRepository.save(subject);
//                } else {
//                    // If subject exists, fetch it
//                    subject = subjectRepository.findById(subject.getId()).orElse(subject);
//                }
//                subject.getStudents().add(student); //  Ensure bidirectional mapping
//                subjectList.add(subject);
//            }
//
//            student.setSubjects(subjectList);
//        }
//
//        return studentRepository.save(student);
//    }


}
