package com.naidiuk.ormbasics.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "grp")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;
    @OneToMany(mappedBy = "group", cascade = CascadeType.MERGE)
    @Builder.Default
    private List<Student> students = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public void addStudent(Student student) {
        getStudents().add(student);
        student.setGroup(this);
    }

    public void removeStudent(Student student) {
        getStudents().remove(student);
        student.setGroup(null);
    }
}
