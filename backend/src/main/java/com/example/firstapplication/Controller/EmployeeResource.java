package com.example.firstapplication.Controller;

import com.example.firstapplication.Model.Employee;
import com.example.firstapplication.Service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService ;

   @GetMapping("/all")
    public ResponseEntity<List<Employee>> getQllEmployee(){
       List<Employee> employees = employeeService.findAllEmployee();
       return  new ResponseEntity<>(employees, HttpStatus.OK);

   }
   @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployessById(@PathVariable("id") long id){
       Employee employee= employeeService.findEmployeeByid(id) ;
       return new ResponseEntity<>(employee,HttpStatus.OK);

   }
   @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
       Employee newEmployee= employeeService.addEmployee(employee);
       return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

   }
    @PutMapping("/update")
    public ResponseEntity<Employee> upadateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee= employeeService.addEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED);

    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id ){
       employeeService.deleteEmployeeById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

}
