package org.abesproject.hclhrms.controller;

import org.abesproject.hclhrms.EmpServiceImp;
import org.abesproject.hclhrms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmpServiceImp empServiceImp;

    @GetMapping("/")
    public String welcomeMsg() {
        return "Welcome to ABES HRMs";
    }

    // Both /add and /address supported so either URL works
    @GetMapping({ "/add", "/address" })
    public String addNewEmp() {
        return empServiceImp.addEmp();
    }

    @GetMapping("/getEmp")
    public List<Employee> getEmp() {
        return empServiceImp.getEmp();
    }

    @GetMapping("/addBulk")
    public String addEmpBulk() {
        return empServiceImp.addEmpBulk();
    }

    @GetMapping("/getEmpById")
    public Employee getEmpById(@RequestParam(required = false, defaultValue = "1001") long id) {
        return empServiceImp.getEmpById(id);
    }

    @GetMapping("/getEmpById/{id}")
    public Employee getEmpByIdPath(@PathVariable long id) {
        return empServiceImp.getEmpById(id);
    }
}
