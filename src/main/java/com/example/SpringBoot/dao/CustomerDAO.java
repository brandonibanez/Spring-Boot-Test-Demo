package com.example.SpringBoot.dao;

import com.example.SpringBoot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

}
