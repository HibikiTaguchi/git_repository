package jp.co.sss.advanced.bean;

import java.io.Serializable;
import java.util.Date;

public class MenuitemBean implements Serializable {
	private Integer menuitemId;
	private String menuitemName;
	private Integer price;
	private Date modifiedDate;
	private Integer status;
	private Integer kindId;
	public Integer getMenuitemId() {
		return menuitemId;
	}
	public void setMenuitemId(Integer menuitemId) {
		this.menuitemId = menuitemId;
	}
	public String getMenuitemName() {
		return menuitemName;
	}
	public void setMenuitemName(String menuitemName) {
		this.menuitemName = menuitemName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
}
