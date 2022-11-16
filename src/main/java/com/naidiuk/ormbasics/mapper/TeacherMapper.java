package com.naidiuk.ormbasics.mapper;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.dto.TeacherGroupsDto;
import com.naidiuk.ormbasics.dto.TeacherStudentsDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;
import com.naidiuk.ormbasics.entity.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {
    private TeacherMapper() {

    }

    public static TeacherGroupsDto transformToDtoWithGroups(Teacher teacher) {
        List<Group> teacherGroups = teacher.getGroups();
        List<GroupDto> teacherGroupsDto = teacherGroups.stream()
                                                .map(GroupMapper::transformToDto)
                                                .collect(Collectors.toList());

        return TeacherGroupsDto.builder()
                .teacherId(teacher.getTeacherId())
                .teacherName(teacher.getTeacherName())
                .teacherSurname(teacher.getTeacherSurname())
                .groups(teacherGroupsDto)
                .build();
    }

    public static TeacherStudentsDto transformToDtoWithStudents(Teacher teacher, List<Student> students) {
        List<StudentDto> studentsDto = students.stream()
                                            .map(StudentMapper::transformToDto)
                                            .collect(Collectors.toList());

        return TeacherStudentsDto.builder()
                .teacherId(teacher.getTeacherId())
                .teacherName(teacher.getTeacherName())
                .teacherSurname(teacher.getTeacherSurname())
                .students(studentsDto)
                .build();
    }
}
