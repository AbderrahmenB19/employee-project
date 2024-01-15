package com.example.firstapplication.Service;

import com.example.firstapplication.Exception.UserNotFoundexception;
import com.example.firstapplication.Model.Employee;
import com.example.firstapplication.Repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }
    public List<Employee> findAllEmployee(){
        return employeeRepo.findAll();
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

   public Employee findEmployeeByid(long id ){
        return employeeRepo.findById(id).orElseThrow(()->
                new UserNotFoundexception("Employee not found by id = "+id));

   }

    public void deleteEmployeeById(long id){
        employeeRepo.deleteById(id);
    }


}
