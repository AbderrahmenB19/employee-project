package com.example.firstapplication.Repository;

import com.example.firstapplication.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Override
    Optional<Employee> findById(Long aLong);

}
