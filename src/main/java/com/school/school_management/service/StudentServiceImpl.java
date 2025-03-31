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

//    @Override
//    public Student saveStudent(Student student) {
//        return studentRepository.save(student);
//    }


    @Override
    public Student saveStudent(Student student) {

        Classroom classroom = classroomRepository.findByName(student.getClassroom().getName())
                .orElseGet(() -> classroomRepository.save(student.getClassroom()));
        student.setClassroom(classroom);


        List<Subject> subjects = new ArrayList<>();
        for (Subject subject : student.getSubjects()) {
            Subject existingSubject = subjectRepository.findByName(subject.getName())
                    .orElseGet(() -> subjectRepository.save(subject));
            subjects.add(existingSubject);
        }
        student.setSubjects(subjects);

        // Save Student with Relationships
        return studentRepository.save(student);
    }



    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }




}
