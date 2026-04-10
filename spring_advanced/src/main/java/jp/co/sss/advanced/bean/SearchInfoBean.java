package jp.co.sss.advanced.bean;

import java.io.Serializable;

public class SearchInfoBean implements Serializable {
	private Integer searchMethod;
	private String searchKey;
	private Integer kindAvgPrice;
	public Integer getSearchMethod() {
		return searchMethod;
	}
	public void setSearchMethod(Integer searchMethod) {
		this.searchMethod = searchMethod;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public Integer getKindAvgPrice() {
		return kindAvgPrice;
	}
	public void setKindAvgPrice(Integer kindAvgPrice) {
		this.kindAvgPrice = kindAvgPrice;
	}
}
