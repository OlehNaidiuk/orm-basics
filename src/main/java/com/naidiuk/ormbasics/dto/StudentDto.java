package com.naidiuk.ormbasics.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentDto {
    private Long studentId;
    private String studentName;
    private String studentSurname;
}
