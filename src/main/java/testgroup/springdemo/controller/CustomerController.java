package testgroup.springdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testgroup.springdemo.model.Customer;
import testgroup.springdemo.service.CustomerService;

/**
 *
 * @author smallad
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
    
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        return customerService.getCustomer(customerId);
    }

}
