package jp.co.sss.advanced.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/** DBのstaffテーブルに対応するentityクラス */
@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_staff_gen")
	@SequenceGenerator(name = "seq_staff_gen", sequenceName = "seq_staff", allocationSize = 1)
	/** スタッフ番号 */
	private Integer staffId;
	/** スタッフ名 */
	@Column(nullable = false)
	private String staffName;
	/** アクセスID */
	@Column(nullable = false, unique = true)
	private String accessId;
	/** パスワード */
	@Column(nullable = false)
	private String password;
	/** 削除フラグ */
	@Column(columnDefinition = "integer default 0")
	private Integer deleteFlag = 0;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
