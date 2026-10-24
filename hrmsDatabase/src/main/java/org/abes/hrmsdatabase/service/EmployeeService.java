package org.abes.hrmsdatabase.service;

import org.abes.hrmsdatabase.model.Employee;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String creatNewEmployee() {
        Employee emp1 = new Employee(101, "Saumya", "Java", "Manager");
        employeeRepository.save(emp1);
        return "Employee is added successfully!!";
    }

    public Employee getEmpById(long emp_id) {
        return employeeRepository.findById(emp_id).orElse(null);
    }

    public String deleteById(long emp_id) {
        boolean status = false;
        if (getEmpById(emp_id) != null) {
            employeeRepository.deleteById(emp_id);
            status=true;
        }
        if(status){
            return " Employee deleted successfully!";
        }
        else{
            return "Employee not found " + emp_id + " !!";
        }
    }

    public String updateById(long emp_id) {
        Employee existingEmp = getEmpById(emp_id);
        if (existingEmp != null) {
            existingEmp.setDesignation("Senior Manager"); // koi bhi field update kar sakte hain
            employeeRepository.save(existingEmp); // Database me save/update karta hai
            return "Employee updated successfully!";
        }
        return "Employee not found " + emp_id + " !!";
    }
}
