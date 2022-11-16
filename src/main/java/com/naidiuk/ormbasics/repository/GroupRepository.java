package com.naidiuk.ormbasics.repository;

import com.naidiuk.ormbasics.entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Query("select g from Group g left join fetch g.students where g.groupId=:groupId")
    Optional<Group> findByIdWithStudents(Long groupId);
}
