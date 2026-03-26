package jp.co.sss.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/** departmentテーブルのentityクラス */
@Entity
@Table(name = "department")
public class Department {
	/** 部署ID */
	@Id
	private Integer deptId;
	/** 部署名 */
	@Column
	private String deptName;
	/**
	 * 部署IDを取得
	 * @return 部署ID
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 部署IDをセット
	 * @param 部署ID
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 部署名を取得
	 * @return 部署名
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 部署名をセット
	 * @param 部署名
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
