package com.naidiuk.ormbasics.mapper;

import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.entity.Student;

public class StudentMapper {
    private StudentMapper() {

    }

    public static StudentDto transformToDto(Student student) {
        return StudentDto.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .studentSurname(student.getStudentSurname())
                .build();
    }

    public static Student transformToDao(StudentDto studentDto) {
        return Student.builder()
                .studentId(studentDto.getStudentId())
                .studentName(studentDto.getStudentName())
                .studentSurname(studentDto.getStudentSurname())
                .build();
    }
}
