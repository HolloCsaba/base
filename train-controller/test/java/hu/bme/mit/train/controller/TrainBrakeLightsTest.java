package hu.bme.mit.train.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainBrakeLights;

public class TrainBrakeLightsTest {

    TrainBrakeLights brakeLights;

    @Before
    public void before() {
        brakeLights = new TrainBrakeLights();
    }

    @Test
    public void TestBrakeLightStates() {
        brakeLights.setState(true);
        Assert.assertEquals(true, brakeLights.getState());

        brakeLights.setState(false);
        Assert.assertEquals(false, brakeLights.getState());
    }
}
