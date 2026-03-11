package jp.co.sss.practice.p06.bean;

import java.io.Serializable;

public class FruitsSeasonBean implements Serializable {
	private Integer fruitId;
	private String fruitName; 
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
