package com.naidiuk.ormbasics.mapper;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {
    private GroupMapper() {

    }

    public static Group transformToDao(GroupDto groupDto) {
        return Group.builder()
                .groupId(groupDto.getGroupId())
                .groupName(groupDto.getGroupName())
                .build();
    }

    public static GroupDto transformToDto(Group group) {
        return GroupDto.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .build();
    }

    public static GroupStudentsDto transformToDtoWithStudents(Group group) {
        List<Student> students = group.getStudents();
        List<StudentDto> studentsDto = students.stream()
                                            .map(StudentMapper::transformToDto)
                                            .collect(Collectors.toList());

        return GroupStudentsDto.builder()
                .groupId(group.getGroupId())
                .groupName(group.getGroupName())
                .students(studentsDto)
                .build();
    }
}
