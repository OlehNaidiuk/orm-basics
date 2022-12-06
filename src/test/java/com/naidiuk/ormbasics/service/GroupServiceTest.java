package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;
import com.naidiuk.ormbasics.error.GroupNotFoundException;
import com.naidiuk.ormbasics.error.StudentNotFoundException;
import com.naidiuk.ormbasics.mapper.StudentMapper;
import com.naidiuk.ormbasics.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @Mock
    private GroupRepository groupRepositoryMock;
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        groupService = new GroupServiceImpl(groupRepositoryMock);
    }

    @Test
    void whenAddStudentToGroup_thenReturnGroupWithAddedStudent() {
        //prepare
        Group group = getGroup();
        Student student = getStudent();
        Long groupId = group.getGroupId();
        StudentDto expectedStudentDto = StudentMapper.transformToDto(student);

        Mockito.doReturn(Optional.of(group)).when(groupRepositoryMock).findByIdWithStudents(groupId);
        Mockito.doReturn(group).when(groupRepositoryMock).save(group);

        //when
        GroupStudentsDto groupWithStudentsDto = groupService.addStudentToGroup(groupId, expectedStudentDto);
        StudentDto actualStudentDto = groupWithStudentsDto.getStudents().get(0);

        //then
        assertEquals(1, groupWithStudentsDto.getStudents().size());
        assertEquals(expectedStudentDto.getStudentId(), actualStudentDto.getStudentId());
        Mockito.verify(groupRepositoryMock).save(group);
    }

    @Test
    void whenAddStudentToGroupWithWrongGroupId_thenThrowGroupNotFoundException() {
        //prepare
        Long wrongGroupId = 11L;
        StudentDto studentDto = mock(StudentDto.class);

        //then
        GroupNotFoundException e =
                assertThrows(GroupNotFoundException.class,
                        () -> groupService.addStudentToGroup(wrongGroupId, studentDto));
        assertEquals("Group with id=11 not found!", e.getMessage());
    }

    @Test
    void whenDeleteStudentFromGroup_thenReturnGroupWithoutDeletedStudent() {
        //prepare
        Group group = getGroup();
        Student student = getStudent();
        Long groupId = group.getGroupId();
        Long studentId = student.getStudentId();
        group.getStudents().add(student);

        Mockito.doReturn(Optional.of(group)).when(groupRepositoryMock).findByIdWithStudents(groupId);
        Mockito.doReturn(group).when(groupRepositoryMock).save(group);

        //when
        GroupStudentsDto groupStudentsDto = groupService.deleteStudentFromGroup(groupId, studentId);

        //then
        assertEquals(0, groupStudentsDto.getStudents().size());
        Mockito.verify(groupRepositoryMock).save(group);
    }

    @Test
    void whenDeleteStudentFromGroupWithWrongStudentId_thenThrowStudentNotFoundException() {
        //prepare
        Group group = getGroup();
        Long groupId = group.getGroupId();
        Long wrongStudentId = 11L;

        Mockito.doReturn(Optional.of(group)).when(groupRepositoryMock).findByIdWithStudents(groupId);

        //then
        StudentNotFoundException e =
                assertThrows(StudentNotFoundException.class,
                        () -> groupService.deleteStudentFromGroup(groupId, wrongStudentId));
        assertEquals("Student with id=11 not found!", e.getMessage());
    }

    private Group getGroup() {
        return Group.builder()
                .groupId(1L)
                .build();
    }

    private Student getStudent() {
        return Student.builder()
                .studentId(1L)
                .build();
    }
}