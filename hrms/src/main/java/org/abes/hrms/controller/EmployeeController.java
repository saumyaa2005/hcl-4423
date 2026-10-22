package org.abes.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.abes.hrms.model.DemoEmployee;


@RestController
public class EmployeeController {
    @Autowired
    private DemoEmployee demoEmployee;
    @GetMapping(value = "/", produces = "text/html")
    public String welcome(){
        return "<h2 style='color : pink'> Welcome to HRMS App</h2>";
    }

    @GetMapping("/add")
    public int sum(){
        int result= demoEmployee.add(20, 30);
        return result;
    }
    @GetMapping("/sqrt")
    public double getsqrroot(){
        return demoEmployee.sqaureroot();
    }
    @GetMapping("/gtn")
    public int gtn(){
        return demoEmployee.gtn();
    }
}


