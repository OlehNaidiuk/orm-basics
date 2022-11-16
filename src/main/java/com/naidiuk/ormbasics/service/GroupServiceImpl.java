package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;
import com.naidiuk.ormbasics.error.GroupNotFoundException;
import com.naidiuk.ormbasics.error.StudentNotFoundException;
import com.naidiuk.ormbasics.mapper.GroupMapper;
import com.naidiuk.ormbasics.mapper.StudentMapper;
import com.naidiuk.ormbasics.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public GroupStudentsDto addStudentToGroup(Long groupId, StudentDto studentDto) {
        Group group = findByIdWithStudents(groupId);
        Student student = StudentMapper.transformToDao(studentDto);
        group.addStudent(student);
        Group savedGroup = groupRepository.save(group);
        return GroupMapper.transformToDtoWithStudents(savedGroup);
    }

    @Override
    public GroupStudentsDto deleteStudentFromGroup(Long groupId, Long studentId) {
        Group group = findByIdWithStudents(groupId);
        Student studentForRemove = group.getStudents().stream()
                                            .filter(student -> (student.getStudentId()).equals(studentId))
                                            .findFirst()
                                            .orElseThrow(() -> new StudentNotFoundException(
                                                            String.format("Student with id=%d not found!", studentId)));
        group.removeStudent(studentForRemove);
        Group savedGroup = groupRepository.save(group);
        return GroupMapper.transformToDtoWithStudents(savedGroup);
    }

    private Group findByIdWithStudents(Long groupId) {
        return groupRepository.findByIdWithStudents(groupId)
                            .orElseThrow(() -> new GroupNotFoundException(
                                            String.format("Group with id=%d not found!", groupId)));
    }
}
