package jp.co.sss.practice.p07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "area")
public class Area {
	@Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_area_gen") 
    @SequenceGenerator(name = "seq_area_gen", sequenceName = "seq_area",
      allocationSize = 1)
	private Integer areaId;
	@Column
	private String areaName;
	@Column
	private String detail;
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
