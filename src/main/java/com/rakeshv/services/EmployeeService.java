package com.rakeshv.services;

import com.rakeshv.models.Employee;
import com.rakeshv.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployeeByBadgeNumber(Long badgeNumber) {
        return employeeRepository.findEmployeeByBadgeNumber(badgeNumber);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }
}
