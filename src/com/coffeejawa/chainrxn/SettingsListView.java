package com.coffeejawa.chainrxn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsListView extends Activity {
	final String[] counts = {"10","15","25","50"};
	
	private SeekBar ballCountSeekBar = (SeekBar) findViewById(R.id.ballCountSeekBar);
	private SeekBar ballSpeedSeekBar = (SeekBar) findViewById(R.id.ballSpeedSeekBar);
	
	private TextView ballCountTextView = (TextView) findViewById(R.id.ballCountSeekBarText);
	private TextView ballSpeedTextView = (TextView) findViewById(R.id.ballSpeedSeekBarText);
	
	private int seekCount,seekSpeed;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ballCountSeekBar.setOnSeekBarChangeListener(seekBarChange);
		ballSpeedSeekBar.setOnSeekBarChangeListener(seekBarChange);
	}
	
	private SeekBar.OnSeekBarChangeListener seekBarChange = new SeekBar.OnSeekBarChangeListener(){
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// Update text views
			updateTextViews();
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub	
		}
	};	
	
	private void updateTextViews(){
		seekCount = ballCountSeekBar.getProgress();
		seekSpeed = ballSpeedSeekBar.getProgress();
		   
		ballCountTextView.setText(""+seekCount);
		ballSpeedTextView.setText(""+seekSpeed);
	}
}