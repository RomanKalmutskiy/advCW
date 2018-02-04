package application;

import java.util.concurrent.TimeUnit;

public class ColdWaterCounter extends Counter {

	String type = "ColdWaterCounter";
	int number;
	int value;
	StartValue startValue;

	public ColdWaterCounter() {
		generatorCW();
	}

	public ColdWaterCounter(StartValue startValue) {
		this.startValue = startValue;
		this.value = Integer.valueOf(startValue.getcWaterLast().intValue());

	}

	void generatorCW() {
		boolean start = true;
		while (start) {
			System.out.println("HW" + value);
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
		return null;
	}

	public void setType(String type) {

	}

	public int getNumber() {
		return 0;
	}

	public void setNumber(int number) {

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		System.out.println(type + ": " + number + ": " + value);

	}

}
