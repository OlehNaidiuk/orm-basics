package com.naidiuk.ormbasics.controller;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.TeacherGroupsDto;
import com.naidiuk.ormbasics.dto.TeacherStudentsDto;
import com.naidiuk.ormbasics.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/{teacherId}/groups")
    public ResponseEntity<?> addStudentToGroup(
            @PathVariable Long teacherId,
            @RequestBody GroupDto groupDto
    ) {
        TeacherGroupsDto teacherGroupsDto = teacherService.addGroupToTeacher(teacherId, groupDto);
        return ResponseEntity.status(HttpStatus.OK).body(teacherGroupsDto);
    }

    @DeleteMapping("/{teacherId}/groups/{groupId}")
    public ResponseEntity<?> deleteStudentFromGroup(
            @PathVariable Long teacherId,
            @PathVariable Long groupId
    ) {
        TeacherGroupsDto teacherGroupsDto = teacherService.deleteGroupFromTeacher(teacherId, groupId);
        return ResponseEntity.status(HttpStatus.OK).body(teacherGroupsDto);
    }

    @GetMapping("/{teacherId}/students")
    public ResponseEntity<?> findAllStudentsOfTeacher(@PathVariable Long teacherId) {
        TeacherStudentsDto teacherStudentsDto = teacherService.findAllTeacherStudents(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(teacherStudentsDto);
    }

    @GetMapping("/{teacherId}/groups-number")
    public ResponseEntity<?> countTeacherGroups(@PathVariable Long teacherId) {
        Long numberOfTeacherGroups = teacherService.countTeacherGroups(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(numberOfTeacherGroups);
    }
}
