package com.Springboot.Demo.Dao;

import java.util.List;

import com.Springboot.Demo.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployee();
	public Employee getEmployeeById(int id ) ;
	public void saveEmployee(Employee employee);
	public void deleteEmployee(int id );
	

}
