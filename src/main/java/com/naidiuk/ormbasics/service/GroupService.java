package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.StudentDto;
import com.naidiuk.ormbasics.dto.GroupStudentsDto;

public interface GroupService {
    GroupStudentsDto addStudentToGroup(Long groupId, StudentDto studentDto);
    GroupStudentsDto deleteStudentFromGroup(Long groupId, Long studentId);
}
