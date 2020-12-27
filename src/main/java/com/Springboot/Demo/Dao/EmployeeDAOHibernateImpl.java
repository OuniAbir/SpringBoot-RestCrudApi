package com.Springboot.Demo.Dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Springboot.Demo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public List<Employee> getEmployee() {

		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		List<Employee> employees = theQuery.getResultList();

		return employees;

	}

	@Transactional
	public Employee getEmployeeById(int id) {
		Session currentSession = entityManager.unwrap(Session.class) ;
		Employee theEmployee = currentSession.get(Employee.class, id);
		return theEmployee ;
		
    	}

	@Transactional
	public void saveEmployee(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class) ;
		currentSession.saveOrUpdate(employee);
		
	}

	@Transactional
	public void deleteEmployee(int id ) {
		Session currentSession = entityManager.unwrap(Session.class) ;
		
		// now delete the Employee
		Query<Employee> theQuery = currentSession.createQuery("delete from Employee where id=:id");
		theQuery.setParameter("id", id);

		// excute the query
		theQuery.executeUpdate();
		
	}


}
