package jp.co.sss.practice.p07.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "fruits_area")
public class FruitsArea {
	@Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fruits_area_gen") 
    @SequenceGenerator(name = "seq_fruits_area_gen", sequenceName = "seq_fruits_area", 
      allocationSize = 1)
	private Integer fruitId;
	@Column
	private String fruitName;
	@ManyToOne
	@JoinColumn(name = "area_id", referencedColumnName="areaId")
	private Area area;
	
	public Integer getFruitId() {
		return fruitId;
	}
	public void setFruitId(Integer fruitId) {
		this.fruitId = fruitId;
	}
	public String getFruitName() {
		return fruitName;
	}
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
}
