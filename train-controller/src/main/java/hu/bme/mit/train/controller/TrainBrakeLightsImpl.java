package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainBrakeLights;

public class TrainBrakeLightsImpl implements TrainBrakeLights {

    private boolean state = false;

    @Override
	public boolean getState() { return state; }

    @Override
	public void setState(boolean nextstate) { state = nextstate; }
    
}