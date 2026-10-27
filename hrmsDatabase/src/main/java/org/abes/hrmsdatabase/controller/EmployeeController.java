package org.abes.hrmsdatabase.controller;

import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/v1/user/")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String msg() {
        return "program run sccessfulyy";
    }

    @PostMapping("/addemp")
    public String addNemlpoyee(@RequestBody Employee emp) {
        return employeeService.creatNewEmployee(emp);
    }

    @GetMapping({ "/findById/{emp_id}", "/findyId/{emp_id}", "/{emp_id}" })
    public Employee getEmpById(@PathVariable long emp_id) {

        System.out.println("Employee id : " + emp_id);
        return employeeService.getEmpById(emp_id);
    }

    @DeleteMapping("/deleteById/{emp_id}")
    public String deleteById(@PathVariable long emp_id){
        return employeeService.deleteById(emp_id);
    }

    @PutMapping("/updateById/{emp_id}")
    public String updateById(@PathVariable long emp_id) {
        return employeeService.updateById(emp_id);
    }
}
