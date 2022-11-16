package com.naidiuk.ormbasics.controller;

import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/{groupId}/students")
    public ResponseEntity<?> addStudentToGroup(
            @PathVariable Long groupId,
            @RequestBody StudentDto studentDto
    ) {
        GroupStudentsDto groupStudentsDto = groupService.addStudentToGroup(groupId, studentDto);
        return ResponseEntity.status(HttpStatus.OK).body(groupStudentsDto);
    }

    @DeleteMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<?> deleteStudentFromGroup(
            @PathVariable Long groupId,
            @PathVariable Long studentId
    ) {
        GroupStudentsDto groupStudentsDto = groupService.deleteStudentFromGroup(groupId, studentId);
        return ResponseEntity.status(HttpStatus.OK).body(groupStudentsDto);
    }
}
