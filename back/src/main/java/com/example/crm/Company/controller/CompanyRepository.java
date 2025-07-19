package com.example.crm.Company.controller;

import com.example.crm.Company.entity.Company;
import com.example.crm.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByName(String name);
}
