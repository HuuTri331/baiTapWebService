package com.codegym.c0324h1_spring_boot_2.services.impl;

import com.codegym.c0324h1_spring_boot_2.models.Classroom;
import com.codegym.c0324h1_spring_boot_2.repositories.IClassroomRepository;
import com.codegym.c0324h1_spring_boot_2.services.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService implements IClassroomService {

    @Autowired
    private IClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }
}
