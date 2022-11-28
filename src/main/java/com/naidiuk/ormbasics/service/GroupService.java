package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupStudentsDto;
import com.naidiuk.ormbasics.dto.StudentDto;

public interface GroupService {
    GroupStudentsDto addStudentToGroup(Long groupId, StudentDto studentDto);
    GroupStudentsDto deleteStudentFromGroup(Long groupId, Long studentId);
}
