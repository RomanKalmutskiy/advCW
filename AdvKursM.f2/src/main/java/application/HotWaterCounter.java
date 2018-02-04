package application;

import java.util.concurrent.TimeUnit;

public class HotWaterCounter extends Counter {

	String type = "HotWaterCounter";
	int number;
	int value;

	StartValue startValue;

	public HotWaterCounter() {
		generatorHW();
	}

	public HotWaterCounter(StartValue startValue) {
		this.startValue = startValue;
		this.value = Integer.valueOf(startValue.gethWaterLast().intValue());
	}

	void generatorHW() {
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

		return type;
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
