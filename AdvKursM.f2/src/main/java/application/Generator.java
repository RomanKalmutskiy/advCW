package application;

import java.util.concurrent.TimeUnit;

public class Generator implements Runnable {

	boolean state = true;
	int genValue = 0;

	ElectricityCounter elCount;
	ElectricityCounter elCount2;
	GasCounter gasCoun;
	Counter count;
	HotWaterCounter hotWaterCount;
	ColdWaterCounter coldWaterCount;

	public Generator(boolean state, int genValue) {
		this.state = state;
		this.genValue = genValue;
		new Thread(this).start();
	}

	public Generator(boolean state, int genValue, ElectricityCounter elCount, GasCounter gasCoun,
			HotWaterCounter hotWaterCount, ColdWaterCounter coldWaterCount) {
		this.state = state;
		this.genValue = genValue;
		this.elCount = elCount;
		this.gasCoun = gasCoun;
		this.hotWaterCount = hotWaterCount;
		this.coldWaterCount = coldWaterCount;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (genValue < 100) {

			genValue++;
			elCount.setValue(genValue);
			gasCoun.setValue(genValue);
			hotWaterCount.setValue(genValue);
			coldWaterCount.setValue(genValue);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
