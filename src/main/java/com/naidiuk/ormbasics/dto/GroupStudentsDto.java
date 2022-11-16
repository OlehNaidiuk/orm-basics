package com.naidiuk.ormbasics.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GroupStudentsDto {
    private Long groupId;
    private String groupName;
    @Builder.Default
    private List<StudentDto> students = new ArrayList<>();
}
