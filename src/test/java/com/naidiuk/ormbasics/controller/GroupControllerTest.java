package com.naidiuk.ormbasics.controller;

import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.service.GroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GroupControllerTest {
    @Mock
    private GroupService groupServiceMock;
    @InjectMocks
    private GroupController groupController;

    @Test
    void whenAddStudentToGroup_thenReturnGroupStudentsDto() {
        //prepare
        GroupStudentsDto groupStudentsDto = mock(GroupStudentsDto.class);
        StudentDto studentDto = mock(StudentDto.class);
        Long groupId = 1L;

        Mockito.doReturn(groupStudentsDto).when(groupServiceMock).addStudentToGroup(groupId, studentDto);

        //when
        ResponseEntity<?> responseEntity = groupController.addStudentToGroup(groupId, studentDto);

        //then
        assertTrue(responseEntity.hasBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groupStudentsDto, responseEntity.getBody());
    }

    @Test
    void whenDeleteStudentFromGroup_thenReturnGroupStudentsDto() {
        //prepare
        GroupStudentsDto groupStudentsDto = mock(GroupStudentsDto.class);
        Long studentId = 1L;
        Long groupId = 1L;

        Mockito.doReturn(groupStudentsDto).when(groupServiceMock).deleteStudentFromGroup(groupId, studentId);

        //when
        ResponseEntity<?> responseEntity = groupController.deleteStudentFromGroup(groupId, studentId);

        //then
        assertTrue(responseEntity.hasBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(groupStudentsDto, responseEntity.getBody());
    }
}