package application;

import java.sql.Date;

public class Payment {

	Date date1;
	Double elq;
	Double gq;
	Double hvq;
	Double cwq;
	Double ela;
	Double ga;
	Double hwa;
	Double cwa;

	public Payment(Date date1, Double elq, Double gq, Double hvq, Double cwq, Double ela, Double ga, Double hwa,
			Double cwa) {
		this.date1 = date1;
		this.elq = elq;
		this.gq = gq;
		this.hvq = hvq;
		this.cwq = cwq;
		this.ela = ela;
		this.ga = ga;
		this.hwa = hwa;
		this.cwa = cwa;
	}

	public Payment() {
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Double getElq() {
		return elq;
	}

	public void setElq(Double elq) {
		this.elq = elq;
	}

	public Double getGq() {
		return gq;
	}

	public void setGq(Double gq) {
		this.gq = gq;
	}

	public Double getHvq() {
		return hvq;
	}

	public void setHvq(Double hvq) {
		this.hvq = hvq;
	}

	public Double getCwq() {
		return cwq;
	}

	public void setCwq(Double cwq) {
		this.cwq = cwq;
	}

	public Double getEla() {
		return ela;
	}

	public void setEla(Double ela) {
		this.ela = ela;
	}

	public Double getGa() {
		return ga;
	}

	public void setGa(Double ga) {
		this.ga = ga;
	}

	public Double getHwa() {
		return hwa;
	}

	public void setHwa(Double hwa) {
		this.hwa = hwa;
	}

	public Double getCwa() {
		return cwa;
	}

	public void setCwa(Double cwa) {
		this.cwa = cwa;
	}

}
