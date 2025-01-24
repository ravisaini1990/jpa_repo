package com.ravi.jpa_repo.controller;

import com.ravi.jpa_repo.api.entity.Department;
import com.ravi.jpa_repo.api.entity.Student;
import com.ravi.jpa_repo.repository.DepartmentRepository;
import com.ravi.jpa_repo.repository.StudentRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    //get all the students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //get student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@Valid @PathVariable int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.orElse(null);
    }


    @PostMapping(value = "/students/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addNewStudent(@Valid @RequestBody Student student) {
        Student student1 = new Student();
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setCity(student.getCity());
        student1.setDepartment(student.getDepartment());
        studentRepository.save(student1);
    }

    @PutMapping(value = "/students/update/{id}")
    public Student updateStudentWithObject(@PathVariable int id, @RequestBody Student newStudent) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student != null) {
            //update this name and age in param
            student.setName(newStudent.getName());
            student.setAge(newStudent.getAge());
            student.setCity(newStudent.getCity());
            student.setDepartment(newStudent.getDepartment());
            return studentRepository.save(student);
        }

        return null;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    /// Pagination coming from PagingAndSortingRepository
    @GetMapping("/getStudentWithPagination")
    public ResponseEntity<List<Student>> getPaginatedStudents(@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "1") Integer pageSize) {
        //create page request object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Student> pagingUser = studentRepository.findAll(pageRequest);
        return pagingUser.hasContent() ? ResponseEntity.ok(pagingUser.getContent()) : ResponseEntity.ofNullable(null);
    }
}
