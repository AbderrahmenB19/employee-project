import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { response } from 'express';
import { error } from 'console';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public employees: Employee[] = this.employeeService.em;
  public editEmployee: Employee | null = null;
  public deleteEmployee: Employee | null = null;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
     {
        next: response=>this.employees=response,
        error:e=>console.info(e)
     }
    );
  }
  

  onAddEmployee(addForm: NgForm): void {
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log('Employee added:', response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.error('Error adding employee:', error);
        // Provide user-friendly error message or handle accordingly
        addForm.reset();
      }
    );
  }

  onUpdateEmployee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe({
        next: (v) => {console.log(v)},
        error: (e) => console.error(e),
        complete: () => console.info('complete') 
    });
  }

  onDeleteEmployee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      () => {
        console.log('Employee deleted successfully');
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        console.error('Error deleting employee:', error);
        // Provide user-friendly error message or handle accordingly
      }
    );
  }

  searchEmployees(key: string): void {
    const results: Employee[] = this.employees.filter((employee) =>
      employee.name.toLowerCase().includes(key.toLowerCase()) ||
      employee.email.toLowerCase().includes(key.toLowerCase()) ||
      employee.phone.toLowerCase().includes(key.toLowerCase()) ||
      employee.jobTitle.toLowerCase().includes(key.toLowerCase())
    );

    this.employees = results.length === 0 || !key ? this.employees : results;
  }

  onOpenModal(employee: Employee | null, mode: string): void {
        const container = document.getElementById('main-container');
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal');
        if (mode === 'add') {
          button.setAttribute('data-target', '#addEmployeeModal');
        }
        if (mode === 'edit') {
          this.editEmployee = employee;
          button.setAttribute('data-target', '#updateEmployeeModal');
        }
        if (mode === 'delete') {
          this.deleteEmployee = employee;
          button.setAttribute('data-target', '#deleteEmployeeModal');
        }
        container?.appendChild(button);
        button.click();
      }
    
    
}
