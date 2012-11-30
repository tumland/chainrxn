package com.coffeejawa.chainrxn;

public class Timer {
	long lastCheck = System.currentTimeMillis();
	
	public float GetTimeElapsed(){
		float ret = (float) (System.currentTimeMillis() - lastCheck);
		lastCheck = System.currentTimeMillis();
		return ret;
	}
}
