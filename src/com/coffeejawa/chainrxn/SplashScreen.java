package com.coffeejawa.chainrxn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends Activity {
	String count = "";
	String speed = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);
		
		// Set up click listeners
		Button bPlay = (Button) findViewById(R.id.playButton);
		bPlay.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				Intent gameIntent = new Intent(SplashScreen.this, Game.class);
				//ChainRxn.this.startActivityForResult(gameIntent, 0);
				if(!count.isEmpty()){
					gameIntent.putExtra("ballCount", count);
				}
				if(!speed.isEmpty()){
					gameIntent.putExtra("ballSpeed", speed);
				}
				SplashScreen.this.startActivity(gameIntent);
			}
		});
		// Set up click listeners
		Button bSettings = (Button) findViewById(R.id.settingsButton);
		bSettings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent settingsIntent = new Intent(SplashScreen.this, SettingsScreen.class);
				//ChainRxn.this.startActivityForResult(gameIntent, 0);
				SplashScreen.this.startActivityForResult(settingsIntent, 0);
			}
		});
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == RESULT_OK){
			Bundle extras;
			if (data != null && (extras = data.getExtras()) != null) {
				count = extras.getString("ballCount");
				Log.d("SplashScreen", "Ball Count = " + count);
				speed =  extras.getString("ballSpeed");
				Log.d("SplashScreen", "Ball Speed = " + speed);
			}
			
		}
	}

}
