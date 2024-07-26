package com.codegym.c0324h1_spring_boot_2.repositories;

import com.codegym.c0324h1_spring_boot_2.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {
}
