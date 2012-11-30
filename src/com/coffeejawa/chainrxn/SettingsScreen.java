package com.coffeejawa.chainrxn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsScreen extends Activity {
	final String[] counts = {"10","15","25","50"};
	
	private SeekBar ballCountSeekBar;
	private SeekBar ballSpeedSeekBar;
	
	private TextView ballCountTextView;
	private TextView ballSpeedTextView;
	
	private int seekCount,seekSpeed;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.settings);
		
		ballCountSeekBar = (SeekBar) findViewById(R.id.ballCountSeekBar);
		ballSpeedSeekBar = (SeekBar) findViewById(R.id.ballSpeedSeekBar);
		ballCountTextView = (TextView) findViewById(R.id.ballCountSeekBarText);
		ballSpeedTextView = (TextView) findViewById(R.id.ballSpeedSeekBarText);
		
		ballCountSeekBar.setOnSeekBarChangeListener(seekBarChange);
		ballSpeedSeekBar.setOnSeekBarChangeListener(seekBarChange);

		Button bOK = (Button) findViewById(R.id.okButton);
		final Intent retIntent = this.getIntent();
		bOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// get count
				
				retIntent.putExtra("ballCount", ""+seekCount);
				retIntent.putExtra("ballSpeed", ""+seekSpeed);
				
				setResult(RESULT_OK,retIntent);
				finish();

			}
		});
		Button bCancel = (Button) findViewById(R.id.cancelButton);
		bCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
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