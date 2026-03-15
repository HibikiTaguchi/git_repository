package jp.co.sss.practice.p09.q01.form;

import java.util.Date;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class BodyTempForm {
	@NotNull
	@DecimalMin(value = "30.0")
	@DecimalMax(value = "45.0")
	private Double bodyTemp;
	
	@NotNull
	private Date inputDate;

	public Double getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Double bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
}
