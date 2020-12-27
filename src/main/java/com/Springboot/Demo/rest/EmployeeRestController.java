package com.Springboot.Demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Springboot.Demo.Dao.EmployeeDAO;
import com.Springboot.Demo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeDAO employeeDAO ;
	
	@GetMapping("/employees")
	public List<Employee> getemployee()
	{
		return employeeDAO.getEmployee();
		
	}
	@GetMapping("/employees/{idEmployee}")
	public Employee getEmployeeById(@PathVariable int idEmployee) {
		return employeeDAO.getEmployeeById(idEmployee);
		
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeDAO.saveEmployee(employee);
		return employee ;
		
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee )
	{
		employeeDAO.saveEmployee(employee);
		return employee ;
	}
	
	@DeleteMapping("/employees/{idEmpolyee}")
	public void deleteEmployee(@PathVariable int idEmpolyee ) {
		
		Employee theEmployee = employeeDAO.getEmployeeById(idEmpolyee);
		if (theEmployee == null )
		{
			System.out.println("employee not found ");
		}
		
		employeeDAO.deleteEmployee(idEmpolyee);
	}	
}
