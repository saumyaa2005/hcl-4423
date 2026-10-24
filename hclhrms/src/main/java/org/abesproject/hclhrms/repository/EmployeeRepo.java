package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo {
    String addEmp(Employee emp);
    List<Employee> getAllEmp();
    //Employee getEmp();
    String addEmpBulk(List<Employee> employeeList);
    Employee getEmpById(long emp_id);
    String deleteById(long emp_id);
    String updatedById(long emp_id);
    List<Employee> getEmp();
}
