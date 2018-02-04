package application;

import java.util.concurrent.TimeUnit;

public class ElectricityCounter extends Counter {

	String type = "ElectricityCounter";
	int number;
	int value = 0;

	StartValue startValue;

	public ElectricityCounter() {

		generatorEl();

	}

	void generatorEl() {
		boolean start = true;
		while (start) {
			System.out.println("el" + value);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			value++;
			value = value + (int) (Math.random() * 1);
			getValue();
			return;
		}
	}

	ElectricityCounter(int number) {
		this.number = number;
	}

	public ElectricityCounter(StartValue startValue) {
		this.startValue = startValue;
		this.value = Integer.valueOf(startValue.getElLast().intValue());
	}

	public String getType() {

		return null;
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
