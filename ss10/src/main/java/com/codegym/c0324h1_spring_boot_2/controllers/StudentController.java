package com.codegym.c0324h1_spring_boot_2.controllers;

import com.codegym.c0324h1_spring_boot_2.dtos.StudentDto;
import com.codegym.c0324h1_spring_boot_2.models.Classroom;
import com.codegym.c0324h1_spring_boot_2.models.Student;
import com.codegym.c0324h1_spring_boot_2.services.IClassroomService;
import com.codegym.c0324h1_spring_boot_2.services.IStudentService;
import com.codegym.c0324h1_spring_boot_2.services.impl.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Đánh dấu đây là 1 controller (1 bean)
//Annotation Controller
//Bài tập về nhà: phân biệt @Component, @Controller, @Service, @Repository (đều sử dụng để tạo bean)
@Controller
@RequestMapping(value = "student")
@SessionAttributes("studentTalkList")
public class StudentController {

//    Cơ chế DI (Dependence injection): Tiêm phụ thuộc để giảm sự phụ thuộc
//    DI: Field/Interface
//    DI: Setter
//    DI: Constructor

//    Scope và vòng đời của Bean
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassroomService classroomService;

    @ModelAttribute("studentTalkList")
    private List<Student> students() {
        return new ArrayList<>();
    }

    @GetMapping("")
    public String displayAllStudents(Model model,
                                     @RequestParam(value = "nameStudent", defaultValue = "") String nameStudent,
                                     @RequestParam(value = "page", defaultValue = "0")int page) {
        Sort sort = Sort.by("name").descending();
        Page<Student> students = studentService.findAllByName(nameStudent, PageRequest.of(page, 20, sort));
        model.addAttribute("students", students);
        model.addAttribute("nameStudent", nameStudent);
        return "student/list";
    }

    @GetMapping("/create")
    public String viewCreate(Model model) {
        model.addAttribute("studentDto", new StudentDto());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        return "student/create";
    }

    @PostMapping("/create")
    public String newStudent(@Validated @ModelAttribute("studentDto") StudentDto studentDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirect,
                             Model model
                          ) {
        new StudentDto().validate(studentDto, bindingResult);
        if(bindingResult.hasFieldErrors()) {
            model.addAttribute("classrooms", classroomService.getAllClassrooms());
            return "student/create";
        }
        Student student = new Student();
//        student1.setName(student.getName());
//        student1.setAddress(student.getAddress());
//        student1.setScore(student.getScore());
//        student1.setClassroom(student.getClassroom());
//        Muôn copy: trùng kiểu dữ liệu + trùng tên
        BeanUtils.copyProperties(studentDto, student);
        studentService.save(student);
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable Long id) {
        System.out.println(id);
//....
        return "student/update";
    }

    @GetMapping("talk/{id}")
    public String addTalk(@PathVariable("id")Long id,
                          @ModelAttribute("studentTalkList")List<Student> students,
                          RedirectAttributes redirect) {
        Student student = studentService.findById(id);
        students.add(student);
        redirect.addFlashAttribute("message", "Thêm vào room thanh công");
        return "redirect:/student";
    }

    @GetMapping("/talk")
    public String viewTalkList(@ModelAttribute("studentTalkList")List<Student> students,
                               Model model) {
        model.addAttribute("students", students);
        return "student/list_talk";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception exception) {
        return "error/400";
    }




}
