package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		return employeeRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Employee", "Id", id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}
	
}



//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//	@Autowired
//	private CourseDao courseDao;
////	List<Course> list;
//
//	public EmployeeServiceImpl() {
////		list = new ArrayList<>();
////		list.add(new Course(123, "Java Basics", "For Java Beginners"));
////		list.add(new Course(345, "Docker", "For Advanced Programmers"));
//	}
//
//	@Override
//	public List<Course> getCourses() {
//		// CourseService interface vitra function ko body hudaina,so run time
//		// polymorphism or getCourse() ko method overriding yaha vayo, jasle lsit of
//		// course return garxa.
////		return list;
//		return courseDao.findAll();
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public Course getCourse(long courseId) {
////		Course c = null;
////		for(Course course:list) {
////			if(course.getId()==courseId) {
////				c=course;
////				break;
////			}
////		}
////		return c;
//		return courseDao.getOne(courseId);
//	}
//
//	@Override
//	public Course addCourse(Course course) {
////		list.add(course);
//		courseDao.save(course);
//		return course;
//	}
//
//	@Override
//	public Course updateCourse(Course course) {
////		list.forEach(e -> {
////			if(e.getId() == course.getId()) {
////				e.setTitle(course.getTitle());
////				e.setDescription(course.getDescription());
////				
////			}
////		});
//		courseDao.save(course);
//		
//		return course;
//	}
//
//	@Override
//	public void deleteCourse(long parseLong) {
////		list = this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
//		Course entity = courseDao.getOne(parseLong);
//		courseDao.delete(entity);
//	}
//
//}
