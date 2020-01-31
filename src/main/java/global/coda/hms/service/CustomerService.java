package global.coda.hms.service;

import global.coda.hms.jp.CustomerCrud;
import global.coda.hms.model.Customer;
import global.coda.hms.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerCrud customerCrud;
    public Customer createCustomer(Customer customer) {
       return customerCrud.save(customer);
    }

    public List<Customer> sortCustomer(){
        return (List<Customer>) customerCrud.findAll();
    }

}
