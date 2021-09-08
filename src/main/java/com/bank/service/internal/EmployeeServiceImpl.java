package com.bank.service.internal;

import com.bank.domain.Employee;
import com.bank.exception.EmployeeNotFoundException;
import com.bank.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl {

    private static final int PAGE_SIZE = 10;
    private static final String DEFAULT_SORT_PARAMETER = "id";
    private static final String DEFAULT_SORT_DIRECTION = Sort.Direction.ASC.toString();

    @Autowired
    private EmployeeRepository repository;

    public void create(Employee employee) {
        repository.save(employee);
    }

    public Employee findById(Long id) throws EmployeeNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));
    }

    public Page<Employee> findAllMatchingAndSort(Employee employee, String direction, Set<String> sortParams) throws EmployeeNotFoundException {

        if (direction == null){
            direction = DEFAULT_SORT_DIRECTION;
        }
        if(sortParams == null){
            sortParams = Collections.singleton(DEFAULT_SORT_PARAMETER);
        }
        String[] params = sortParams.toArray(new String[0]);

        Sort sort = Sort.by(Sort.Direction.valueOf(direction), params);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE).withSort(sort);
        Page<Employee> employees = repository.findAll(Example.of(employee), pageable);
        if (employees.isEmpty()){
            throw new EmployeeNotFoundException("ma matching results");
        }

        return employees;
    }

    public Employee update(Long id, Employee source) throws EmployeeNotFoundException {
        Optional<Employee> optional = repository.findById(id);
        Employee target = optional.orElseThrow(() -> new EmployeeNotFoundException("employee with id " + id + " not found"));
        return update(target, source);
    }

    private Employee update(Employee source, Employee target){
        target.setName(source.getName());
        target.setLastName(source.getLastName());
        target.setAge(source.getAge());
        target.setBirthDay(source.getBirthDay());
        target.setSalary(source.getSalary());
        return target;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}