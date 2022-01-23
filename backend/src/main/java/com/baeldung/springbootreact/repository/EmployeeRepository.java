package com.baeldung.springbootreact.repository;

import com.baeldung.springbootreact.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}