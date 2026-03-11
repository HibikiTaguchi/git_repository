package jp.co.sss.practice.p06.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "fruits_season")
public class FruitsSeason {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sfs_gen")
	@SequenceGenerator(name = "sfs_gen", sequenceName = "seq_fruits_season", allocationSize = 1)
	private Integer fruitId;
	@Column
	private String fruitName;
	@Column
	private Integer seasonMonth;
	
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
	public Integer getSeasonMonth() {
		return seasonMonth;
	}
	public void setSeasonMonth(Integer seasonMonth) {
		this.seasonMonth = seasonMonth;
	}
}
