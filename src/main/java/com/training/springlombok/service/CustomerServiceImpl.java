package com.training.springlombok.service;

import com.training.springlombok.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap = new HashMap<>();
    public CustomerServiceImpl() {
        CustomerDTO cust1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Dharshini")
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        CustomerDTO cust2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Nandhu")
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        CustomerDTO cust3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Srilaya")
                .version(1)
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        customerMap.put(cust1.getId(), cust1);
        customerMap.put(cust2.getId(), cust2);
        customerMap.put(cust3.getId(), cust3);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName(customer.getCustomerName())
                .createdDateTime(LocalDateTime.now())
                .lastModifiedDateTime(LocalDateTime.now())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }
}
