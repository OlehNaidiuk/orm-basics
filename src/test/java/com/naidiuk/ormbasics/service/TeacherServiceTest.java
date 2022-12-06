package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.TeacherGroupsDto;
import com.naidiuk.ormbasics.dto.TeacherStudentsDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;
import com.naidiuk.ormbasics.entity.Teacher;
import com.naidiuk.ormbasics.error.GroupNotFoundException;
import com.naidiuk.ormbasics.error.TeacherNotFoundException;
import com.naidiuk.ormbasics.mapper.GroupMapper;
import com.naidiuk.ormbasics.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepositoryMock;
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        teacherService = new TeacherServiceImpl(teacherRepositoryMock);
    }

    @Test
    void whenAddGroupToTeacher_thenReturnTeacherWithAddedGroup() {
        //prepare
        Teacher teacher = getTeacher();
        Group group = getGroup();
        Long teacherId = teacher.getTeacherId();
        GroupDto expectedGroupDto = GroupMapper.transformToDto(group);

        Mockito.doReturn(Optional.of(teacher)).when(teacherRepositoryMock).findByIdWithGroups(teacherId);
        Mockito.doReturn(teacher).when(teacherRepositoryMock).save(teacher);

        //when
        TeacherGroupsDto teacherGroupsDto = teacherService.addGroupToTeacher(teacherId, expectedGroupDto);
        GroupDto actualGroupDto = teacherGroupsDto.getGroups().get(0);

        //then
        assertEquals(1, teacherGroupsDto.getGroups().size());
        assertEquals(expectedGroupDto.getGroupId(), actualGroupDto.getGroupId());
        Mockito.verify(teacherRepositoryMock).save(teacher);
    }

    @Test
    void whenAddGroupToTeacherWithWrongTeacherId_thenThrowTeacherNotFoundException() {
        //prepare
        Long wrongTeacherId = 11L;
        GroupDto groupDto = mock(GroupDto.class);

        //then
        TeacherNotFoundException e =
                assertThrows(TeacherNotFoundException.class,
                            () -> teacherService.addGroupToTeacher(wrongTeacherId, groupDto));
        assertEquals("Teacher with id=11 not found!", e.getMessage());
    }

    @Test
    void whenDeleteGroupFromTeacher_thenReturnTeacherWithoutDeletedGroup() {
        //prepare
        Teacher teacher = getTeacher();
        Group group = getGroup();
        Long teacherId = teacher.getTeacherId();
        Long groupId = group.getGroupId();
        teacher.getGroups().add(group);

        Mockito.doReturn(Optional.of(teacher)).when(teacherRepositoryMock).findByIdWithGroups(teacherId);
        Mockito.doReturn(teacher).when(teacherRepositoryMock).save(teacher);

        //when
        TeacherGroupsDto teacherGroupsDto = teacherService.deleteGroupFromTeacher(teacherId, groupId);

        //then
        assertEquals(0, teacherGroupsDto.getGroups().size());
        Mockito.verify(teacherRepositoryMock).save(teacher);
    }

    @Test
    void whenDeleteGroupFromTeacherWithWrongGroupId_thenThrowGroupNotFoundException() {
        //prepare
        Teacher teacher = getTeacher();
        Long teacherId = teacher.getTeacherId();
        Long wrongGroupId = 11L;

        Mockito.doReturn(Optional.of(teacher)).when(teacherRepositoryMock).findByIdWithGroups(teacherId);

        //then
        GroupNotFoundException e =
                assertThrows(GroupNotFoundException.class,
                            () -> teacherService.deleteGroupFromTeacher(teacherId, wrongGroupId));
        assertEquals("Group with id=11 not found!", e.getMessage());
    }

    @Test
    void whenFindAllTeacherStudents_thenReturnTeacherWithStudents() {
        //prepare
        Teacher teacher = getTeacher();
        Long teacherId = teacher.getTeacherId();
        Group group = getGroup();
        List<Student> students = getStudents();
        group.setStudents(students);
        teacher.getGroups().add(group);

        Mockito.doReturn(Optional.of(teacher)).when(teacherRepositoryMock).findByIdWithGroups(teacherId);

        //when
        TeacherStudentsDto teacherStudentsDto = teacherService.findAllTeacherStudents(teacherId);

        //then
        assertEquals(teacherId, teacherStudentsDto.getTeacherId());
        assertThat(teacherStudentsDto.getStudents()).hasSize(2);
    }

    @Test
    void whenCountTeacherGroups_thenReturnNumberOfTeacherGroups() {
        //prepare
        Teacher teacher = getTeacher();
        Long teacherId = teacher.getTeacherId();

        Mockito.doReturn(Optional.of(teacher)).when(teacherRepositoryMock).findById(teacherId);
        Mockito.doReturn(4L).when(teacherRepositoryMock).countTeacherGroups(teacherId);

        //when
        Long numberOfTeacherGroups = teacherService.countTeacherGroups(teacherId);

        //then
        assertEquals(4L, numberOfTeacherGroups);
    }

    private Teacher getTeacher() {
        return Teacher.builder()
                    .teacherId(1L)
                    .build();
    }

    private Group getGroup() {
        return Group.builder()
                    .groupId(1L)
                    .build();
    }

    private List<Student> getStudents() {
        Student firstStudent = Student.builder()
                                    .studentId(1L)
                                    .build();
        Student secondStudent = Student.builder()
                                    .studentId(2L)
                                    .build();
        return List.of(firstStudent, secondStudent);
    }
}