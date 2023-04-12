package com.training.springlombok.service;

import com.training.springlombok.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);
}
