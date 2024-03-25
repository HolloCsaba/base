package hu.bme.mit.train.controller;

import java.util.concurrent.TimeUnit;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainBrakeLights;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {
	private TrainBrakeLights brakeLights;

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
		this.brakeLights.setState(joystickPosition < 0);
	}

	public TrainControllerImpl(TrainBrakeLights brakeLights) {
		this.brakeLights = brakeLights;
	}

	@Override
	public void start() {
		new Thread(() -> {

			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tick();				
			}
		}).start();
	}

	private void tick() {
		followSpeed();
	}
}
