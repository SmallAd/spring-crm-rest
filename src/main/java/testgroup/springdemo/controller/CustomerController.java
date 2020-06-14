package testgroup.springdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testgroup.springdemo.model.Customer;
import testgroup.springdemo.model.exception.CustomerNotFoundException;
import testgroup.springdemo.service.CustomerService;

/**
 *
 * @author smallad
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer == null)
            throw new CustomerNotFoundException("Customer id not found - " + customerId);

        return customer;
    }

    @PostMapping()
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }
    
    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }
    
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable int customerId){
        Customer customer = customerService.getCustomer(customerId);
        if(customer == null)
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        
        customerService.deleteCustomer(customerId);
    }
}
