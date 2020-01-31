package global.coda.hms.controller;

import global.coda.hms.model.Customer;
import global.coda.hms.model.Patient;
import global.coda.hms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class JavaJpa {
   @Autowired
   private CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Customer>> sortCustomer(){
        List<Customer> customers= customerService.sortCustomer();
        return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
    }

}
