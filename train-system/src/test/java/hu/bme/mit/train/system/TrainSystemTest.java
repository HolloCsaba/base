package hu.bme.mit.train.system;

import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

import hu.bme.mit.train.interfaces.TrainBrakeLights;
import hu.bme.mit.train.controller.TrainBrakeLightsImpl;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;

	TrainBrakeLights brakeLights;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50);

		brakeLights = new TrainBrakeLightsImpl();
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10);

		Assert.assertEquals(0, controller.getReferenceSpeed());
		
		user.overrideJoystickPosition(5);

		controller.followSpeed();
		Assert.assertEquals(5, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
    public void TestBrakeLightStates() {
        brakeLights.setState(true);
        Assert.assertEquals(true, brakeLights.getState());

        brakeLights.setState(false);
        Assert.assertEquals(false, brakeLights.getState());
    }

	@Test
    public void TestTableData() {
        sensor.Save();
		int n = sensor.size();
		sensor.Save();
		
        Assert.assertEquals(1, sensor.size() - n);
    }

	@Test
	public void TestThread() throws Exception
	{
		TrainSystem system = new TrainSystem();
		system.Init();
		Thread.sleep(1000);
		Assert.assertTrue(system.van);
	}
	
}
