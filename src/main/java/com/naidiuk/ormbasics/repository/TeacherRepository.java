package com.naidiuk.ormbasics.repository;

import com.naidiuk.ormbasics.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Query("select t from Teacher t left join fetch t.groups where t.teacherId=:teacherId")
    Optional<Teacher> findByIdWithGroups(Long teacherId);
    @Query("select count (g) from Teacher t join t.groups g where t.teacherId=:teacherId")
    Long countTeacherGroups(Long teacherId);
}
