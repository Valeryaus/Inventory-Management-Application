package inventoryManagementProject.controller;

import inventoryManagementProject.entity.Employee;
import inventoryManagementProject.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    // handler method to handle list employees and return mode and view
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {

        // create employee object to hold employee form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        //pavaliduoti produkta prie ji saugant - sukuri nauja beansa-employee validation
        // service ir jis galetu tureti metoda validate employee, arba/ir paziureti validacijas Thymeleafe...
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable(value = "id") Integer employeeId, Model model) {
        // kitame projekte buvo panaudota "...@PathVariable(value = "id") long partNumberId..."
        // ,nes kuriant aprasyma padaryta "private long partNumberId;" o cia "private Long partNumberId;";

        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        return "edit_employee";

    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                 @ModelAttribute("employee") Employee employee,
                                 Model model) {
        // get employee from database by id
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        // iskelti iki 68 eilute i employee service nauja metoda
        existingEmployee.setEmployeeId(employeeId);
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setEmployeeEmail(employee.getEmployeeEmail());

        // save updated employee object
        employeeService.updateEmployee(existingEmployee);
        return "redirect:/employees";
    }

    // handler method to handle delete employee request

    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees";
    }
}
