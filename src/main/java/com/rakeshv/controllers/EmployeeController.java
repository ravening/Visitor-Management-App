package com.rakeshv.controllers;

import com.rakeshv.models.Employee;
import com.rakeshv.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final Javers javers;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Fetching all the employees");
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/badge/{id}")
    public ResponseEntity<Employee> getEmployyeByBadge(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeByBadgeNumber(id).orElse(null);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/snapshot")
    public ResponseEntity<String> getEmployeeSnapshot() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class);
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
        return ResponseEntity.ok(javers.getJsonConverter().toJson(snapshots));
    }

    @GetMapping("/edit")
    public ResponseEntity<Employee> editEmployee() {
        Employee employee = employeeService.findAllEmployees().get(0);
        employee.setCheckinTime(new Date());
        employee.setPhone("4321");
        employeeService.saveEmployee(employee);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/changes")
    public ResponseEntity<String> getChanges() {
        Employee employee = employeeService.findAllEmployees().get(0);
        QueryBuilder jqlQuery = QueryBuilder.byInstance(employee);
        Changes changes = javers.findChanges(jqlQuery.build());
        return ResponseEntity.ok(javers.getJsonConverter().toJson(changes));
    }

    @GetMapping("/shadows")
    public ResponseEntity<String> getShadows() {
        Employee employee = employeeService.findAllEmployees().get(0);
        JqlQuery jqlQuery = QueryBuilder.byInstance(employee)
                .withChildValueObjects().build();
        List<Shadow<Employee>> shadows = javers.findShadows(jqlQuery);
        return ResponseEntity.ok(javers.getJsonConverter().toJson(shadows));
    }
}
