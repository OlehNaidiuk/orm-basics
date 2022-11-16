package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.TeacherStudentsDto;
import com.naidiuk.ormbasics.dto.TeacherGroupsDto;

public interface TeacherService {
    TeacherGroupsDto addGroupToTeacher(Long teacherId, GroupDto groupDto);
    TeacherGroupsDto deleteGroupFromTeacher(Long teacherId, Long groupId);
    TeacherStudentsDto findAllTeacherStudents(Long teacherId);
    Long countTeacherGroups(Long teacherId);
}
