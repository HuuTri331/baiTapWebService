package com.codegym.c0324h1_spring_boot_2.dtos;

import com.codegym.c0324h1_spring_boot_2.models.Classroom;
import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentDto implements Validator {


    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[A-Za-z ]{3,100}$", message = "Tên không đúng định dạng")
    private String name;

//    @Size(min = 10, max = 255, message = "Địa chỉ không được vượt quá 255 ký tự và ít hơn 10 ký tự")
    private String address;
    @Min(value = 0, message = "Điểm không được nhỏ hơn 0")
    @Max(value = 10, message = "Điểm không được lớn hơn 10")
    @NotNull(message = "Điểm không được để trống")
    private Float score;
    @NotNull(message = "Lớp không được để trống")
    private Classroom classroom;

    public StudentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentDto studentDto = (StudentDto) target;
        if(studentDto.address.length() < 10) {
            errors.rejectValue("address", "", "Địa chỉ không được nhỏ hơn 10 ký tự");
        } else if (studentDto.address.length() >255) {
            errors.rejectValue("address", "", "Địa chỉ không được lớn hơn 255 ký tự");
        }


    }
}
