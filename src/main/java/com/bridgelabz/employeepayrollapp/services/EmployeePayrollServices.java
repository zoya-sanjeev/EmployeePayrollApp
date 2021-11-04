package com.bridgelabz.employeepayrollapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;

@Service
public class EmployeePayrollServices implements IEmployeePayrollService{

	private List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
	@Override
	public List<EmployeePayrollData> getEmployeePayrollData() {
		return employeePayrollList;
	}

	@Override
	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		return employeePayrollList.stream()
				.filter(empData -> empData.getEmployeeId() == empId)
				.findFirst()
				.orElseThrow(() ->  new EmployeePayrollException("Employee Not Found"));
	}

	@Override
	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = null;
		employeePayrollData = new EmployeePayrollData(1, empPayrollDTO);
		employeePayrollList.add(employeePayrollData);
		return employeePayrollData;
	}

	@Override
	public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
		employeePayrollData.setName(empPayrollDTO.name);
		employeePayrollData.setSalary(empPayrollDTO.salary);
		employeePayrollList.set(empId -1, employeePayrollData);
		return employeePayrollData;
	}

	@Override
	public void deleteEmployeePayrollData(int empId) {
		employeePayrollList.remove(empId-1);
	}

}
