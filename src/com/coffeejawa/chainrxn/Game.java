package com.coffeejawa.chainrxn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set full screen view
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);+

		setContentView(R.layout.main);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			final DrawView dv = (DrawView) findViewById(R.id.drawView1);
			dv.setBallCount(extras.getString("ballCount"));
			dv.setBallSpeed(extras.getString("ballSpeed"));
			dv.reset();
		}

		TextView tv = (TextView) findViewById(R.id.textView1);
		if (tv != null)
			tv.setText("Balls: 0");
		DrawView dv = (DrawView) findViewById(R.id.drawView1);
		dv.SetScoreView(tv);

		Button bReset = (Button) findViewById(R.id.button1);

		bReset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final TextView tv = (TextView) findViewById(R.id.textView1);
				tv.setText("Balls: 0");
				final DrawView dv = (DrawView) findViewById(R.id.drawView1);
				dv.reset();
			}
		});

//		Button bSettings = (Button) findViewById(R.id.button2);
//		bSettings.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				Log.e("HelloAndroid", "Settings button clicked");
//				Intent settingsIntent = new Intent(Game.this,
//						SettingsScreen.class);
//				Game.this.startActivityForResult(settingsIntent, 0);
//			}
//		});

		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		checkBox.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final DrawView dv = (DrawView) findViewById(R.id.drawView1);
				if (((CheckBox) v).isChecked()) {
					dv.setSoundEnabled(true);
				} else {
					dv.setSoundEnabled(false);
				}
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bundle extras = data.getExtras();
		if (extras != null) {
			final DrawView dv = (DrawView) findViewById(R.id.drawView1);
			dv.setBallCount(extras.getString("COUNT"));
			Log.e("HelloAndroid", "Ball Count = " + extras.getString("COUNT"));
			dv.reset();
		}
	}
}
