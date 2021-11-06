package com.bridgelabz.employeepayrollapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollServices implements IEmployeePayrollService{

	@Autowired
	private EmployeePayrollRepository employeeRepository;
	
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeeRepository
				.findById(empId)
				.orElseThrow(() ->  new EmployeePayrollException("Employee with id "+ 
							empId + " does not exist..!"));
	}

	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = null;
		employeePayrollData = new EmployeePayrollData(empPayrollDTO);
		employeePayrollList.add(employeePayrollData);
		log.debug("Emp Data: " + employeePayrollData.toString());
		return employeeRepository.save(employeePayrollData);
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
		employeePayrollData.updateEmployeePayrollData(empPayrollDTO);
		return employeeRepository.save(employeePayrollData);
	}

	@Override
	public void deleteEmployeePayrollData(int empId) {
		employeePayrollList.remove(empId-1);
	}

}
