package com.jsp.CloneApiBookMyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneApiBookMyShow.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
