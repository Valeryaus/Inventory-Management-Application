package inventoryManagementProject.service;

import inventoryManagementProject.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Integer customerId);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Integer customerId);

}
