package application;

public class NewTariffes {

	Double elecTarif;
	Double gasTarif;
	Double hWaterTarif;
	Double cWaterTarif;

	public NewTariffes(Double elecTarif, Double gasTarif, Double hWaterTarif, Double cWaterTarif) {
		this.elecTarif = elecTarif;
		this.gasTarif = gasTarif;
		this.hWaterTarif = hWaterTarif;
		this.cWaterTarif = cWaterTarif;
	}

	public NewTariffes() {

	}

	public Double getElecTarif() {
		return elecTarif;
	}

	public void setElecTarif(Double elecTarif) {
		this.elecTarif = elecTarif;
	}

	public Double getGasTarif() {
		return gasTarif;
	}

	public void setGasTarif(Double gasTarif) {
		this.gasTarif = gasTarif;
	}

	public Double gethWaterTarif() {
		return hWaterTarif;
	}

	public void sethWaterTarif(Double hWaterTarif) {
		this.hWaterTarif = hWaterTarif;
	}

	public Double getcWaterTarif() {
		return cWaterTarif;
	}

	public void setcWaterTarif(Double cWaterTarif) {
		this.cWaterTarif = cWaterTarif;
	}

}
