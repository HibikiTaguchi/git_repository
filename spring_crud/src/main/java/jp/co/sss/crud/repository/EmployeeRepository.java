package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.util.JPQLConstant;

/** employeeテーブルのレポジトリインターフェース */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/**
	 * 社員名検索をするSQLを実行、DBからの出力を返すQueryメソッド
	 * @param empName 社員名
	 * @return List<Employee> 社員オブジェクトのリスト
 	 */
	@Query(JPQLConstant.FIND_EMPLOYEE_BY_EMP_NAME_ORDER_BY_EMP_ID)
	List<Employee> findByName(@Param("empName") String empName);
	/**
	 * 部署ID検索をするSQLを実行、DBからの出力を返すQueryメソッド
	 * @param deptId 部署ID
	 * @return List<Employee> 社員オブジェクトのリスト
	 */
	@Query(JPQLConstant.FIND_EMPLOYEE_BY_DEPT_ID_ORDER_BY_EMP_ID)
	List<Employee> findByDeptId(@Param("deptId") Integer deptId);
}
