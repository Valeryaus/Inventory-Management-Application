package inventoryManagementProject.service;

import inventoryManagementProject.entity.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Integer employeeId);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);


}
