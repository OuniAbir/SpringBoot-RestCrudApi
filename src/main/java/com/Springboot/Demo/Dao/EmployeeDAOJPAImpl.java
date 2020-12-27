package com.Springboot.Demo.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Springboot.Demo.entity.Employee;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	public EmployeeDAOJPAImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Employee> getEmployee() {

		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		List<Employee> employees = theQuery.getResultList();

		return employees;

	}

	@Transactional
	public Employee getEmployeeById(int id) {

		Employee theEmployee = entityManager.find(Employee.class, id);
		return theEmployee;

	}

	@Transactional
	public void saveEmployee(Employee employee) {

		Employee dbEmployee = entityManager.merge(employee);
		// we should update with id from db... so we can get generated id for save/insert
		//useful in our REST API to return this generated id 
		employee.setId(dbEmployee.getId());

	}

	@Transactional
	public void deleteEmployee(int id) {

		// delete the Employee
		Query theQuery = entityManager.createQuery("delete from Employee where id=:id");
		theQuery.setParameter("id", id);

		// excute the query
		theQuery.executeUpdate();

	}

}
