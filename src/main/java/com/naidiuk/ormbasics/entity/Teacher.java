package com.naidiuk.ormbasics.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String teacherName;
    private String teacherSurname;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.MERGE)
    @Builder.Default
    private List<Group> groups = new ArrayList<>();

    public void addGroup(Group group) {
        getGroups().add(group);
        group.setTeacher(this);
    }

    public void removeGroup(Group group) {
        getGroups().remove(group);
        group.setTeacher(null);
    }
}
