package com.naidiuk.ormbasics.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class TeacherGroupsDto {
    private Long teacherId;
    private String teacherName;
    private String teacherSurname;
    @Builder.Default
    private List<GroupDto> groups = new ArrayList<>();
}
