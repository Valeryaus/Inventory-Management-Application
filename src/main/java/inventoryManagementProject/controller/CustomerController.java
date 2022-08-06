package inventoryManagementProject.controller;

import inventoryManagementProject.entity.Customer;
import inventoryManagementProject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    // handler method to handle list customers and return mode and view
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customers/new")
    public String createCustomerForm(Model model) {

        // create customer object to hold customer form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create_customer";
    }

    @PostMapping("/customers")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        //pavaliduoti produkta pries ji saugant - sukuri nauja beansa-customer validation
        // service ir jis galetu tureti metoda validate customer, arba/ir paziureti validacijas Thymeleafe...
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String editCustomerForm(@PathVariable(value = "id") Integer customerId, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        return "edit_customer";

    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable(value = "id") Integer customerId,
                                 @ModelAttribute("customer") Customer customer,
                                 Model model) {
        // get customer from database by id
        Customer existingCustomer = customerService.getCustomerById(customerId);
        // iskelti iki 68 eilute i customer service nauja metoda
        existingCustomer.setCustomerId(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setCustomerEmail(customer.getCustomerEmail());

        // save updated customer object
        customerService.updateCustomer(existingCustomer);
        return "redirect:/customers";
    }

    // handler method to handle delete customer request
    @GetMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable(value = "id") Integer customerId) {
        customerService.deleteCustomerById(customerId);
        return "redirect:/customers";
    }
}



