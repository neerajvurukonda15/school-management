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
        if (student.getStudentProfile() != null) {
            student.getStudentProfile().setStudent(student);
        }

        if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
            List<Long> subjectIds = new ArrayList<>();

            for (Subject subject : student.getSubjects()) {
                if (subject.getId() != null) {
                    subjectIds.add(subject.getId());
                }
            }

            List<Subject> subjectList = subjectRepository.findAllById(subjectIds);

            if (!subjectList.isEmpty()) {
                for (Subject subject : subjectList) {
                    subject.getStudents().add(student);
                }
                student.setSubjects(subjectList);
            }
        }

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
