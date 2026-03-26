package jp.co.sss.crud.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/** employeeテーブルのenitityクラス */
@Entity
@Table(name = "employee")
public class Employee {
	/** 社員ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = 
	"seq_emp_gen") 
	 @SequenceGenerator(name = "seq_emp_gen", sequenceName = 
	"seq_emp", allocationSize = 1) 
	private Integer empId;
	/** パスワード */
	@Column
	private String empPass;
	/** 社員名 */
	@Column
	private String empName;
	/** 性別 */
	@Column
	private Integer gender;
	/** 住所 */
	@Column
	private String address;
	/** 誕生日 */
	@Column
	private Date birthday;
	/** 権限 */
	@Column
	private Integer authority;
	/** 部署ID（外部参照） */
	@ManyToOne
	@JoinColumn(name="dept_id", referencedColumnName = "deptId")
	private Department department;
	/**
	 * 社員IDを取得
	 * @return 社員ID
	 */
	public Integer getEmpId() {
		return empId;
	}
	/**
	 * 社員IDをセット
	 * @param 社員ID
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	/**
	 * パスワードを取得
	 * @return パスワード
	 */
	public String getEmpPass() {
		return empPass;
	}
	/**
	 * パスワードをセット
	 * @param empPass
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
	/**
	 * 社員名を取得
	 * @return 社員名
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * 社員名をセット
	 * @param 社員名
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * 性別を取得
	 * @return 性別
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 性別をセット
	 * @param 性別
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 住所を取得
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 住所をセット
	 * @param 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 生年月日を取得
	 * @return 生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 生年月日をセット
	 * @param 生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 権限を取得
	 * @return 権限
	 */
	public Integer getAuthority() {
		return authority;
	}
	/**
	 * 権限をセット
	 * @param 権限
	 */
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	/**
	 * 部署を所得
	 * @return 部署
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * 部署をセット
	 * @param 部署
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
