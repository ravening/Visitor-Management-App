package com.rakeshv.utils;

import com.rakeshv.models.Employee;
import com.rakeshv.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbSeeder {
    private final EmployeeRepository employeeRepository;

    @EventListener
    public void saveEmployees(ApplicationReadyEvent event) {
        employeeRepository.deleteAll();
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .badgeNumber(1L)
                .department("Congress")
                .phone("1234").build();

        log.info("Saving the employee");
        employeeRepository.save(employee);
    }
}
