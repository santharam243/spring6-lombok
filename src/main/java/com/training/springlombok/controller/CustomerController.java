package com.training.springlombok.controller;

import com.training.springlombok.model.CustomerDTO;
import com.training.springlombok.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    @RequestMapping(method = RequestMethod.GET)
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public ResponseEntity saveNewCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

}
