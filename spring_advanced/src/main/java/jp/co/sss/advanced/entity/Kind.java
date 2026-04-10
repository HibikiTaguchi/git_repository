package jp.co.sss.advanced.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "kinds")
public class Kind {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kinds_gen")
	@SequenceGenerator(name = "seq_kinds_gen", sequenceName = "seq_kinds", allocationSize = 1)
	private Integer kindId;
	@Column(nullable =false)
	private String kindName;
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
}
