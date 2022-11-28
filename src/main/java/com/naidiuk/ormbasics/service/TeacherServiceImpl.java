package com.naidiuk.ormbasics.service;

import com.naidiuk.ormbasics.dto.GroupDto;
import com.naidiuk.ormbasics.dto.TeacherGroupsDto;
import com.naidiuk.ormbasics.dto.TeacherStudentsDto;
import com.naidiuk.ormbasics.entity.Group;
import com.naidiuk.ormbasics.entity.Student;
import com.naidiuk.ormbasics.entity.Teacher;
import com.naidiuk.ormbasics.error.GroupNotFoundException;
import com.naidiuk.ormbasics.error.TeacherNotFoundException;
import com.naidiuk.ormbasics.mapper.GroupMapper;
import com.naidiuk.ormbasics.mapper.TeacherMapper;
import com.naidiuk.ormbasics.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public TeacherGroupsDto addGroupToTeacher(Long teacherId, GroupDto groupDto) {
        Teacher teacher = findByIdWithGroups(teacherId);
        Group group = GroupMapper.transformToDao(groupDto);
        teacher.addGroup(group);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.transformToDtoWithGroups(savedTeacher);
    }

    @Override
    public TeacherGroupsDto deleteGroupFromTeacher(Long teacherId, Long groupId) {
        Teacher teacher = findByIdWithGroups(teacherId);
        Group groupForRemove = teacher.getGroups().stream()
                                            .filter(group -> (group.getGroupId()).equals(groupId))
                                            .findFirst()
                                            .orElseThrow(() ->
                                                    new GroupNotFoundException(
                                                            String.format("Group with id=%d not found!", groupId)));
        teacher.removeGroup(groupForRemove);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.transformToDtoWithGroups(savedTeacher);
    }

    @Override
    @Transactional
    public TeacherStudentsDto findAllTeacherStudents(Long teacherId) {
        Teacher teacher = findByIdWithGroups(teacherId);
        List<Group> teacherGroups = teacher.getGroups();
        List<Student> teacherStudents = new ArrayList<>();
        for (Group teacherGroup : teacherGroups) {
            List<Student> students = teacherGroup.getStudents();
            teacherStudents.addAll(students);
        }
        return TeacherMapper.transformToDtoWithStudents(teacher, teacherStudents);
    }

    @Override
    public Long countTeacherGroups(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                                        .orElseThrow(() ->
                                                new TeacherNotFoundException(
                                                        String.format("Teacher with id=%d not found!", teacherId)));
        return teacherRepository.countTeacherGroups(teacherId);
    }

    private Teacher findByIdWithGroups(Long teacherId) {
        return teacherRepository.findByIdWithGroups(teacherId)
                            .orElseThrow(() ->
                                    new TeacherNotFoundException(
                                            String.format("Teacher with id=%d not found!", teacherId)));
    }
}
