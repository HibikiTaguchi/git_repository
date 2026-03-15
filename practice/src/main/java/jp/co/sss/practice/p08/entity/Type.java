package jp.co.sss.practice.p08.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "type")
public class Type {
	@Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_type_gen") 
    @SequenceGenerator(name = "seq_type_gen", sequenceName = "seq_type",
      allocationSize = 1)
	private Integer typeId;
	
	@Column
	private String typeName;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
