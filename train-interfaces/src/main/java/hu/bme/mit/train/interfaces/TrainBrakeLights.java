package hu.bme.mit.train.interfaces;


/**
 * State: On = true | Off = false
 */
public interface TrainBrakeLights {
    
	boolean getState();

	void setState(boolean nextstate);

}
