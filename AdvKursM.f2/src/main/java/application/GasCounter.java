package application;

import java.util.concurrent.TimeUnit;

public class GasCounter extends Counter {

	String type = "GasCounter";
	int number;
	int value;

	StartValue startValue;

	public GasCounter() {
		generatorGs();
	}

	public GasCounter(StartValue startValue) {
		this.startValue = startValue;
		this.value = Integer.valueOf(startValue.getGasLast().intValue());
	}

	void generatorGs() {
		boolean start = true;
		while (start) {
			System.out.println("gz" + value);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			value = value + (int) (Math.random() * 2);
			return;
		}
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

	}

	public int getNumber() {

		return number;
	}

	public void setNumber(int number) {
		this.number = number;

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		System.out.println(type + ": " + number + ": " + value);

	}

}
