package jp.co.sss.advanced.entity;

import java.util.Date;

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
@Table(name = "menuitems")
public class Menuitem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menuitems_gen")
	@SequenceGenerator(name = "seq_menuitems_gen", sequenceName = "seq_menuitems", allocationSize = 1)
	private Integer menuitemId;
	@Column(nullable =false)
	private String menuitemName;
	@Column(nullable =false)
	private Integer price;
	@Column(nullable =false)
	private Date modifiedDate;
	@Column(columnDefinition = "integer default 0")
	private Integer status;
	@ManyToOne
	@JoinColumn(name = "kind_id", columnDefinition = "kindName")
	private Kind kind;
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
	public Kind getKind() {
		return kind;
	}
	public void setKind(Kind kind) {
		this.kind = kind;
	}
}
