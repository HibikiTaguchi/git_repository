package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.util.JPQLConstant;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(JPQLConstant.FIND_EMPLOYEE_BY_EMP_NAME_ORDER_BY_EMP_ID)
	List<Employee> findByName(@Param("empName") String empName);
	
	@Query(JPQLConstant.FIND_EMPLOYEE_BY_DEPT_ID_ORDER_BY_EMP_ID)
	List<Employee> findByDeptId(@Param("deptId") Integer deptId);
}
