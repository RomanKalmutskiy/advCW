package application;

public class StartValue {
	Double elLast;
	Double gasLast;
	Double hWaterLast;
	Double cWaterLast;

	public StartValue() {

	}

	public StartValue(Double elLast, Double gasLast, Double hWaterLast, Double cWaterLast) {
		this.elLast = elLast;
		this.gasLast = gasLast;
		this.hWaterLast = hWaterLast;
		this.cWaterLast = cWaterLast;
	}

	public Double getElLast() {
		return elLast;
	}

	public void setElLast(Double elLast) {
		this.elLast = elLast;
	}

	public Double getGasLast() {
		return gasLast;
	}

	public void setGasLast(Double gasLast) {
		this.gasLast = gasLast;
	}

	public Double gethWaterLast() {
		return hWaterLast;
	}

	public void sethWaterLast(Double hWaterLast) {
		this.hWaterLast = hWaterLast;
	}

	public Double getcWaterLast() {
		return cWaterLast;
	}

	public void setcWaterLast(Double cWaterLast) {
		this.cWaterLast = cWaterLast;
	}

}
